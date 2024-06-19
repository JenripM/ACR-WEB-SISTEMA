package com.backend_acr.springboot.web.app.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

        public Cliente addCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(int id) {
        return clienteRepository.findById(id);
    }

    public Cliente updateCliente(int id, Cliente clienteDetails) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente not found"));
        cliente.setNombre(clienteDetails.getNombre());
        cliente.setApellido(clienteDetails.getApellido());
        cliente.setDireccion(clienteDetails.getDireccion());
        cliente.setTelefono(clienteDetails.getTelefono());
        cliente.setCorreoElectronico(clienteDetails.getCorreoElectronico());
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(int id) {
        clienteRepository.deleteById(id);
    }
}
