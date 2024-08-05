package cl.javadevs.springsecurityjwt.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import cl.javadevs.springsecurityjwt.models.Cliente;
import cl.javadevs.springsecurityjwt.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

public class ClienteServiceTest {

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

        Cliente savedCliente = clienteService.addCliente(cliente);

        assertNotNull(savedCliente);
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testGetAllClientes() {
        List<Cliente> clientes = List.of(new Cliente(), new Cliente());
        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> result = clienteService.getAllClientes();

        assertEquals(2, result.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void testGetClienteById() {
        Cliente cliente = new Cliente();
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        Optional<Cliente> result = clienteService.getClienteById(1);

        assertTrue(result.isPresent());
        assertEquals(cliente, result.get());
        verify(clienteRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateCliente() {
        Cliente existingCliente = new Cliente();
        existingCliente.setNombre("Juan");
        Cliente updatedCliente = new Cliente();
        updatedCliente.setNombre("Pedro");

        when(clienteRepository.findById(1)).thenReturn(Optional.of(existingCliente));
        when(clienteRepository.save(existingCliente)).thenReturn(existingCliente);

        Cliente result = clienteService.updateCliente(1, updatedCliente);

        assertNotNull(result);
        assertEquals("Pedro", result.getNombre());
        verify(clienteRepository, times(1)).findById(1);
        verify(clienteRepository, times(1)).save(existingCliente);
    }

    @Test
    void testUpdateClienteThrowsException() {
        Cliente updatedCliente = new Cliente();
        updatedCliente.setNombre("Pedro");

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

    @Test
    void testGetClienteByIdThrowsException() {
        // Simulamos que el método findById lanza una excepción
        when(clienteRepository.findById(1)).thenThrow(new RuntimeException("Cliente no encontrado"));

        // Verificamos que al intentar obtener el cliente, se lanza la excepción
        // esperada
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
         clienteService.getClienteById(1));

        assertEquals("Cliente no encontrado", exception.getMessage());
    }

}