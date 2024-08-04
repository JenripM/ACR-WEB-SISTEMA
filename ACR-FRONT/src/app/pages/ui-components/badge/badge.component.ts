import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/models/cliente';
import { ClienteService } from 'src/app/services/cliente.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
@Component({
  selector: 'app-badge',
  templateUrl: './badge.component.html'
})
export class AppBadgeComponent implements OnInit {
  clientes: Cliente[] = [];
  isNewClienteModalVisible = false;
  isEditClienteModalVisible = false;
  isDeleteClienteModalVisible = false;

  newCliente: Cliente = {
    id: 0,
    nombre: '',
    apellido: '',
    correoElectronico: '',
    direccion: '',
    telefono: '',
    deserto: 0
  };
  editCliente: Cliente = { ...this.newCliente };
  deleteClienteId: number | null = null;
  constructor(private clienteService: ClienteService) {}

  ngOnInit(): void {
    this.getClientes();
  }

  getClientes(): void {
    this.clienteService.getClientes().subscribe(
      (data: Cliente[]) => {
        this.clientes = data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  openNewClienteModal(): void {
    this.isNewClienteModalVisible = true;
  }

  closeNewClienteModal(): void {
    this.isNewClienteModalVisible = false;
  }

  closeNewClienteModalOutside(event: MouseEvent): void {
    if ((event.target as HTMLElement).classList.contains('fixed')) {
      this.closeNewClienteModal();
    }
  }

  addCliente(): void {
    this.clienteService.createCliente(this.newCliente).subscribe(
      (data: Cliente) => {
        this.clientes.push(data);
        this.closeNewClienteModal();
        this.resetNewCliente();
      },
      (error) => {
        console.log(error);
      }
    );
  }

  resetNewCliente(): void {
    this.newCliente = {
      id: 0,
      nombre: '',
      apellido: '',
      correoElectronico: '',
      direccion: '',
      telefono: '',
      deserto: 0

    };
  }


  openEditClienteModal(cliente: Cliente): void {
    this.editCliente = { ...cliente };
    this.isEditClienteModalVisible = true;
  }

  closeEditClienteModal(): void {
    this.isEditClienteModalVisible = false;
  }

  closeEditClienteModalOutside(event: MouseEvent): void {
    if ((event.target as HTMLElement).classList.contains('fixed')) {
      this.closeEditClienteModal();
    }
  }

  updateCliente(): void {
    this.clienteService.updateCliente(this.editCliente.id, this.editCliente).subscribe(
      (data: Cliente) => {
        const index = this.clientes.findIndex(c => c.id === data.id);
        if (index !== -1) {
          this.clientes[index] = data;
        }
        this.closeEditClienteModal();
      },
      (error) => {
        console.log(error);
      }
    );
  }

  openDeleteClienteModal(id: number): void {
    this.deleteClienteId = id;
    this.isDeleteClienteModalVisible = true;
  }

  closeDeleteClienteModal(): void {
    this.isDeleteClienteModalVisible = false;
    this.deleteClienteId = null;
  }

  closeDeleteClienteModalOutside(event: MouseEvent): void {
    if ((event.target as HTMLElement).classList.contains('fixed')) {
      this.closeDeleteClienteModal();
    }
  }

  deleteCliente(): void {
    if (this.deleteClienteId !== null) {
      this.clienteService.deleteCliente(this.deleteClienteId).subscribe(
        () => {
          this.clientes = this.clientes.filter(c => c.id !== this.deleteClienteId);
          this.closeDeleteClienteModal();
        },
        (error) => {
          console.log(error);
        }
      );
    }
  }

}
