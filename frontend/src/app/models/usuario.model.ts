export interface Usuario {
  id?: number;
  uuid?: string;
  nome: string;
  email: string;
  dob: string;
}

export interface PageResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
}
