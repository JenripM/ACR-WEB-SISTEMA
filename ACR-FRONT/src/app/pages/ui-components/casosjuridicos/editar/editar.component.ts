import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormGroup, FormBuilder, Validators, AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
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
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MAT_DATE_FORMATS } from '@angular/material/core';
import * as moment from 'moment';

@Component({
  selector: 'app-casosjuridicos-editar',
  templateUrl: './editar.component.html',
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
    MatPaginatorModule,
    CommonModule,
    // MatDatepickerModule, // Import MatDatepickerModule
    // MatNativeDateModule,

  ],
  // providers: [
  //   { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS }
  // ],
  standalone: true,
  changeDetection: ChangeDetectionStrategy.OnPush,
})

export class CasosEditarComponent implements OnInit {
  formularioEdicion: FormGroup;
  clientes: any[] = []; // Arreglo para almacenar los clientes obtenidos del servidor
  casoId: string | undefined;

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private location: Location
  ) {
    this.formularioEdicion = this.fb.group({
      tipo: ['', Validators.required],
      estado: ['', Validators.required],
      fecha_inicio: ['', [Validators.required, this.dateValidator] ],
      //this.dateValidator
      fecha_cierre: ['',[Validators.required, this.dateValidator]],
      descripcion: ['', Validators.required],
      clienteId: ['', Validators.required], // Campo para almacenar el ID del cliente seleccionado
    },{ validator: this.compareDatesValidator('fecha_inicio', 'fecha_cierre') }
    ) ;
  }

  convertToLocaleDate(date: string): string {
    return moment(date).tz('Europe/Madrid').format('DD/MM/YYYY'); // Ajusta la zona horaria según necesites
  }

  dateValidator(control: AbstractControl): { [key: string]: boolean } | null {
    if (control.value) {
        const enteredDate = new Date(this.convertToLocaleDate(control.value));
        const currentDate = new Date();

        // Restablecer la hora, minutos y segundos de la fecha actual a 0
        currentDate.setHours(0, 0, 0, 0);

        // Comprobar si la fecha ingresada es anterior a la fecha actual
        if (enteredDate < currentDate) {
            return { 'invalidDate': true }; // La fecha ingresada es anterior a hoy
        }
    }
    return null; // La fecha ingresada es hoy o después
  }

  compareDatesValidator(controlName: string, compareToControlName: string): ValidatorFn {
    return (formGroup: AbstractControl): ValidationErrors | null => {
      const control = formGroup.get(controlName);
      const compareToControl = formGroup.get(compareToControlName);

      if (control && compareToControl && control.value && compareToControl.value) {
        const controlDate = new Date(control.value);
        const compareToControlDate = new Date(compareToControl.value);

        if (controlDate > compareToControlDate) {
          return { 'invalidDateRange': true };
        }
      }
      return null;
    }
  }


  ngOnInit(): void {
    this.casoId = this.route.snapshot.paramMap.get('id')!;
    this.obtenerClientes();
    this.obtenerCaso(this.casoId);
  }

  obtenerClientes() {
    this.http.get<any[]>('http://localhost:8080/api/v1/cliente/all')
      .subscribe(
        clientes => {
          this.clientes = clientes;
          console.log('Clientes obtenidos:', this.clientes);
        },
        error => {
          console.error('Error al obtener los clientes:', error);
        }
      );
  }

  formatDate(dateTimeString: string) {
    const date = new Date(dateTimeString);
    const year = date.getUTCFullYear();
    const month = String(date.getUTCMonth() + 1).padStart(2, '0');
    const day = String(date.getUTCDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  }

  obtenerCaso(id: string) {
    this.http.get<any>(`http://localhost:8080/api/v1/caso/${id}`)
      .subscribe(
        caso => {
          this.formularioEdicion.patchValue({
            tipo: caso.tipo,
            estado: caso.estado,
            fecha_inicio: this.formatDate(caso.fecha_inicio),
            fecha_cierre: this.formatDate(caso.fecha_cierre),
            descripcion: caso.descripcion,
            clienteId: caso.cliente.id,
          });
          console.log('caso obtenido:', caso);
        },
        error => {
          console.error('Error al obtener el caso:', error);
        }
      );
  }

  editarCaso() {
    if (this.formularioEdicion.valid) {
      const casoData = {
        tipo: this.formularioEdicion.value.tipo,
        estado: this.formularioEdicion.value.estado,
        fecha_inicio: this.formularioEdicion.value.fecha_inicio,
        fecha_cierre: this.formularioEdicion.value.fecha_cierre,
        descripcion: this.formularioEdicion.value.descripcion,
        cliente: {
          id: this.formularioEdicion.value.clienteId,
          nombre: ''
        }
      };

      this.http.put<any>(`http://localhost:8080/api/v1/caso/update/${this.casoId}`, casoData)
        .subscribe(response => {
          console.log('caso actualizado exitosamente:', response);
          alert('caso actualizado exitosamente');
          this.router.navigate(['/ui-components/casosjuridicos']);
        }, error => {
          console.error('Error al actualizar el caso:', error);
          alert('Error al actualizar el caso. Por favor, intenta nuevamente.');
        });
    } else {
      alert('Por favor, completa todos los campos del formulario correctamente.');
    }
  }
}
