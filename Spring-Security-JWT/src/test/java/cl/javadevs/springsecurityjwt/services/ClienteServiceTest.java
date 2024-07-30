package cl.javadevs.springsecurityjwt.services;

import cl.javadevs.springsecurityjwt.models.Cliente;
import cl.javadevs.springsecurityjwt.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCliente() {
        Cliente cliente = new Cliente();
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente result = clienteService.addCliente(cliente);
        assertEquals(cliente, result);
        verify(clienteRepository, times(1)).save(cliente);
    }
     @Test

    void testAddCliente2() {
        Cliente cliente = new Cliente();
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        // Introducimos un error intencional: esperamos que el resultado sea null
        Cliente result = clienteService.addCliente(cliente);
        assertNull(result);  // Esto debería fallar porque result no es null
    }
    
    @Test
    void testGetAllClientes() {
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        List<Cliente> clientes = Arrays.asList(cliente1, cliente2);
        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> result = clienteService.getAllClientes();
        assertEquals(clientes, result);
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void testGetClienteById() {
        Cliente cliente = new Cliente();
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        Optional<Cliente> result = clienteService.getClienteById(1);
        assertEquals(Optional.of(cliente), result);
        verify(clienteRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateCliente() {
        Cliente existingCliente = new Cliente();
        existingCliente.setNombre("John");
        Cliente updatedCliente = new Cliente();
        updatedCliente.setNombre("Jane");
        when(clienteRepository.findById(1)).thenReturn(Optional.of(existingCliente));
        when(clienteRepository.save(existingCliente)).thenReturn(existingCliente);

        Cliente result = clienteService.updateCliente(1, updatedCliente);
        assertEquals("Jane", result.getNombre());
        verify(clienteRepository, times(1)).findById(1);
        verify(clienteRepository, times(1)).save(existingCliente);
    }

    @Test
    void testUpdateClienteNotFound() {
        Cliente updatedCliente = new Cliente();
        when(clienteRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> clienteService.updateCliente(1, updatedCliente));
        verify(clienteRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteCliente() {
        doNothing().when(clienteRepository).deleteById(1);

        clienteService.deleteCliente(1);
        verify(clienteRepository, times(1)).deleteById(1);
    }
}
