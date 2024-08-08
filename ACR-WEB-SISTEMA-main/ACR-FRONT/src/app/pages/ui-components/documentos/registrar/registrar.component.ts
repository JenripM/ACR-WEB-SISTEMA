import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common'; // <-- Importa CommonModule
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-documento-registrar',
  templateUrl: './registrar.component.html',
  imports: [
    CommonModule, // <-- Añade CommonModule aquí
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatCardModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule
  ],
  standalone: true,
  changeDetection: ChangeDetectionStrategy.OnPush,
})

export class RegistrarComponent implements OnInit {
  formularioRegistro: FormGroup;
  casos: any[] = []; // Arreglo para almacenar los casos obtenidos del servidor
  archivoSeleccionado: File | null = null; // Variable para almacenar el archivo seleccionado

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {
    this.formularioRegistro = this.fb.group({
      tipo: ['', Validators.required],
      casoId: ['', Validators.required], // Campo para almacenar el ID del caso seleccionado
      archivo:['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.obtenerCasos();
  }

  obtenerCasos() {
    this.http.get<any[]>('http://localhost:8080/api/v1/caso/all')
      .subscribe(
        casos => {
          this.casos = casos;
          console.log('Casos obtenidos:', this.casos);
        },
        error => {
          console.error('Error al obtener los casos:', error);
        }
      );
  }

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.archivoSeleccionado = input.files[0];
      console.log('Archivo seleccionado:', this.archivoSeleccionado);
    }
  }

  registrarDocumento() {
    if (this.formularioRegistro.valid && this.archivoSeleccionado) {
      const formData = new FormData();
      formData.append('tipo', this.formularioRegistro.get('tipo')?.value);
      formData.append('casoId', this.formularioRegistro.get('casoId')?.value);
      formData.append('archivo', this.archivoSeleccionado);
      const headers = new HttpHeaders({
        'Authorization': 'Bearer YOUR_TOKEN_HERE'
      });
      
      this.http.post<any>('http://localhost:8080/api/v1/documento/upload', formData, { headers })
        .subscribe(response => {
          console.log('Documento registrado exitosamente:', response);
          alert('Documento registrado exitosamente');
          this.router.navigate(['/ui-components/documentos']);
        }, error => {
          console.error('Error al registrar el documento:', error);
          alert('Error al registrar el documento. Por favor, intenta nuevamente.');
        });
    } else {
      alert('Por favor, completa todos los campos del formulario y selecciona un archivo.');
    }
  }
}
