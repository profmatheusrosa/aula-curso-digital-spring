import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';
import { UsuarioService } from '../../services/usuario';
import { Usuario } from '../../models/usuario.model';

@Component({
  selector: 'app-usuario-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './usuario-form.html',
  styleUrl: './usuario-form.css'
})
export class UsuarioForm implements OnInit {
  form: FormGroup;
  isEdit = false;
  uuidToEdit: string | null = null;
  loading = signal(false);
  successMsg = signal('');
  errorMsg = signal('');

  private fb = inject(FormBuilder);
  private usuarioService = inject(UsuarioService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);

  constructor() {
    this.form = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      dob: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.uuidToEdit = this.route.snapshot.paramMap.get('uuid');
    if (this.uuidToEdit) {
      this.isEdit = true;
      this.loading.set(true);
      this.usuarioService.buscarPorUuid(this.uuidToEdit).subscribe({
        next: (data) => {
          this.form.patchValue(data);
          this.loading.set(false);
        },
        error: (err) => {
          this.errorMsg.set('Erro ao carregar dados do usuário.');
          this.loading.set(false);
        }
      });
    }
  }

  salvar(): void {
    if (this.form.invalid) return;

    this.loading.set(true);
    this.errorMsg.set('');
    this.successMsg.set('');

    const usuarioData: Usuario = this.form.value;

    if (this.isEdit && this.uuidToEdit) {
      this.usuarioService.atualizarUsuario(this.uuidToEdit, usuarioData).subscribe({
        next: () => {
          this.successMsg.set('Usuário atualizado com sucesso!');
          setTimeout(() => this.router.navigate(['/usuarios']), 1000);
        },
        error: (err) => {
          this.loading.set(false);
          this.tratarErro(err);
        }
      });
    } else {
      this.usuarioService.criarUsuario(usuarioData).subscribe({
        next: () => {
          this.successMsg.set('Usuário criado com sucesso!');
          setTimeout(() => this.router.navigate(['/usuarios']), 1000);
        },
        error: (err) => {
          this.loading.set(false);
          this.tratarErro(err);
        }
      });
    }
  }

  private tratarErro(err: any): void {
    if (err.error && typeof err.error === 'object') {
      // Erros de validação do backend (mapa de campo -> mensagem)
      const msgs = Object.entries(err.error)
        .map(([campo, msg]) => `${campo}: ${msg}`)
        .join('\n');
      this.errorMsg.set('Dados inválidos:\n' + msgs);
    } else {
      this.errorMsg.set('Ocorreu um erro inesperado. Tente novamente.');
    }
  }
}
