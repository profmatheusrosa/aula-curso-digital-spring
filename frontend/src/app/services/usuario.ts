import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario, PageResponse } from '../models/usuario.model';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  private readonly apiUrl = 'http://localhost:8080/usuarios';
  private http = inject(HttpClient);

  listarTodos(page: number = 0, size: number = 5): Observable<PageResponse<Usuario>> {
    return this.http.get<PageResponse<Usuario>>(`${this.apiUrl}?page=${page}&size=${size}`);
  }

  buscarPorUuid(uuid: string): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.apiUrl}/${uuid}`);
  }

  criarUsuario(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(this.apiUrl, usuario);
  }

  atualizarUsuario(uuid: string, usuario: Usuario): Observable<Usuario> {
    return this.http.put<Usuario>(`${this.apiUrl}/${uuid}`, usuario);
  }

  deletarUsuario(uuid: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${uuid}`);
  }
}
