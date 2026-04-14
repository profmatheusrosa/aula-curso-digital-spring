import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { UsuarioService } from '../../services/usuario';
import { Usuario } from '../../models/usuario.model';

@Component({
  selector: 'app-usuario-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './usuario-list.html',
  styleUrl: './usuario-list.css'
})
export class UsuarioList implements OnInit {
  usuarios: Usuario[] = [];
  private usuarioService = inject(UsuarioService);

  ngOnInit(): void {
    this.carregarUsuarios();
  }

  carregarUsuarios(): void {
    this.usuarioService.listarTodos().subscribe({
      next: (data) => this.usuarios = data,
      error: (err) => console.error('Erro ao carregar usuários', err)
    });
  }

  deletarUsuario(uuid: string | undefined): void {
    if (!uuid) return;
    if (confirm('Tem certeza que deseja recomeçar a vida acadêmica deste usuário?')) {
      this.usuarioService.deletarUsuario(uuid).subscribe({
        next: () => this.carregarUsuarios(),
        error: (err) => console.error('Erro ao deletar', err)
      });
    }
  }
}
