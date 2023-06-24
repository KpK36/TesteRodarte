import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, map, Observable, take } from 'rxjs';

import { Aluno } from '../model/aluno';

@Injectable({
  providedIn: 'root',
})
export class AlunosService {
  private readonly API = 'api/alunos';

  constructor(private httpClient: HttpClient) {}

  listAll(): Observable<Aluno[]> {
    return this.httpClient.get<Aluno[]>(this.API)
    .pipe(
      map((alunos: Aluno[])=> {
        return alunos.sort((a,b) => a.idade - b.idade)
      }),
      first()
    );
  }
}
