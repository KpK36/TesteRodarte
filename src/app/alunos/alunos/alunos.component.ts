import { Component } from '@angular/core';
import { Observable, catchError, of, first, take } from 'rxjs';

import { Aluno } from '../model/aluno';
import { AlunosService } from '../services/alunos.service';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from '../../compartilhado/components/error-dialog/error-dialog.component';

@Component({
  selector: 'app-alunos',
  templateUrl: './alunos.component.html',
  styleUrls: ['./alunos.component.scss'],
})
export class AlunosComponent {
  alunos$: Observable<Aluno[]>;
  displayedColumns = [
    'id',
    'name',
    'idade',
    'media',
  ];

  constructor(
    private alunosService: AlunosService,
    public dialog: MatDialog) {
    this.alunos$ = this.alunosService.listAll()
    .pipe(
      catchError(error => {
        this.dialogError('Erro ao carregar alunos')
        return of([])
      })

    );
  }

  dialogError(mensagemErro: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: mensagemErro
    });
  }



}
