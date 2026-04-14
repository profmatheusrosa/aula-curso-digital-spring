import { Component, OnInit, inject, signal, computed, effect } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { UsuarioService } from '../../services/usuario';
import { Usuario, PageResponse } from '../../models/usuario.model';

@Component({
  selector: 'app-usuario-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './usuario-list.html',
  styleUrl: './usuario-list.css'
})
export class UsuarioList implements OnInit {
  // Signals para gerenciar estado
  usuariosResponse = signal<PageResponse<Usuario> | null>(null);
  currentPage = signal(0);
  pageSize = signal(5);
  loading = signal(false);

  // Computeds para derivar estado
  usuarios = computed(() => this.usuariosResponse()?.content ?? []);
  totalPages = computed(() => this.usuariosResponse()?.totalPages ?? 0);
  isFirstPage = computed(() => this.currentPage() === 0);
  isLastPage = computed(() => this.currentPage() >= this.totalPages() - 1);

  private usuarioService = inject(UsuarioService);

  constructor() {
    // Reage a mudanças na página para recarregar dados automaticamente
    effect(() => {
      this.carregarUsuarios(this.currentPage());
    }, { allowSignalWrites: true });
  }

  ngOnInit(): void {}

  carregarUsuarios(page: number): void {
    this.loading.set(true);
    this.usuarioService.listarTodos(page, this.pageSize()).subscribe({
      next: (data) => {
        this.usuariosResponse.set(data);
        this.loading.set(false);
      },
      error: (err) => {
        console.error('Erro ao carregar usuários', err);
        this.loading.set(false);
      }
    });
  }

  proximaPagina(): void {
    if (!this.isLastPage()) {
      this.currentPage.update(p => p + 1);
    }
  }

  paginaAnterior(): void {
    if (!this.isFirstPage()) {
      this.currentPage.update(p => p - 1);
    }
  }

  deletarUsuario(uuid: string | undefined): void {
    if (!uuid) return;
    if (confirm('Tem certeza que deseja recomeçar a vida acadêmica deste usuário?')) {
      this.usuarioService.deletarUsuario(uuid).subscribe({
        next: () => this.carregarUsuarios(this.currentPage()),
        error: (err) => console.error('Erro ao deletar', err)
      });
    }
  }
}
