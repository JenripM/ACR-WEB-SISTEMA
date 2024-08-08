// documentos.component.ts
import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';

export interface Documento {
  idDocumento: number;
  datos: string;
  ruta_archivo: string;
  tipo: string;
  caso: {
    id: number;
    descripcion: string;
    estado: string;
  };
}

@Component({
  selector: 'app-documentos',
  templateUrl: './documentos.component.html',
  styleUrls: ['./documentos.component.scss']
})
export class AppDocumentosComponent implements OnInit {
  displayedColumns: string[] = ['idDocumento', 'tipo', 'caso', 'actions'];
  dataSource: MatTableDataSource<Documento>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private http: HttpClient) {
    this.dataSource = new MatTableDataSource<Documento>([]);
  }

  ngOnInit(): void {
    this.obtenerDocumentos();
  }

  obtenerDocumentos() {
    this.http.get<Documento[]>('http://localhost:8080/api/v1/documento/all')
      .subscribe(documentos => {
        this.dataSource.data = documentos;
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      }, error => {
        console.error('Error al obtener los Documentos:', error);
      });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
