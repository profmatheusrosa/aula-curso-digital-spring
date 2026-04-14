import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';
import { UsuarioService } from '../../services/usuario';
import { Usuario } from '../../models/usuario.model';

@Component({
  selector: 'app-usuario-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './usuario-form.html',
  styleUrl: './usuario-form.css'
})
export class UsuarioForm implements OnInit {
  usuario: Usuario = { nome: '', email: '', dob: '' };
  isEdit = false;
  uuidToEdit: string | null = null;
  
  private usuarioService = inject(UsuarioService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);

  ngOnInit(): void {
    this.uuidToEdit = this.route.snapshot.paramMap.get('uuid');
    if (this.uuidToEdit) {
      this.isEdit = true;
      this.usuarioService.buscarPorUuid(this.uuidToEdit).subscribe({
        next: (data) => this.usuario = data,
        error: (err) => console.error('Erro ao buscar usuário', err)
      });
    }
  }

  salvar(): void {
    if (this.isEdit && this.uuidToEdit) {
      this.usuarioService.atualizarUsuario(this.uuidToEdit, this.usuario).subscribe({
        next: () => this.router.navigate(['/usuarios']),
        error: (err) => alert('Erro ao atualizar usuário')
      });
    } else {
      this.usuarioService.criarUsuario(this.usuario).subscribe({
        next: () => this.router.navigate(['/usuarios']),
        error: (err) => alert('Erro ao criar usuário')
      });
    }
  }
}
