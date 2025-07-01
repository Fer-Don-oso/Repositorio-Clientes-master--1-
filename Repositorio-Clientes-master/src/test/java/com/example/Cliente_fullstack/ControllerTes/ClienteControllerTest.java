package com.example.Cliente_fullstack.ControllerTes;

import com.example.Cliente_fullstack.Model.Cliente;
import com.example.Cliente_fullstack.service.ClienteService;
import com.example.Cliente_fullstack.controller.ClientesController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClientesController clientesController;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fechaNacimiento = sdf.parse("2023-10-01");
            cliente = new Cliente(1, "juan", "Donoso", "Perez", "juanito123@gmail.com", fechaNacimiento, 1234567890);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testListarClientes() {
        when(clienteService.listaClientes()).thenReturn(Arrays.asList(cliente));
        List<Cliente> resultado = clientesController.listar().getBody();
            assertNotNull(resultado, "La lista de clientes no debe ser nula");
            assertEquals(1, resultado.size());
        }
     
    @Test
    public void testGuardarCliente() {
        when(clienteService.agregarCliente(any())).thenReturn(cliente);
        org.springframework.http.ResponseEntity<Cliente> response = clientesController.guardar(cliente);
        Cliente guardado = response.getBody();
        assertEquals("juan", guardado.getNombre());
        
        }
    
    @Test 
    public void testBuscarCliente() {
        when(clienteService.buscarporID(1)).thenReturn(cliente);
        Cliente encontrado = (Cliente) clientesController.buscar(1).getBody();
        assertNotNull(encontrado);
        assertEquals("juan", encontrado.getNombre());

    }
    
    @Test
    public void testBuscarClienteNoEncontrado() {
        when(clienteService.buscarporID(999)).thenReturn(null);
        Cliente resultado = (Cliente) clientesController.buscar(999).getBody();
        assertNull(resultado);
    }

    @Test
    public void testActualizarCliente() {
        when(clienteService.actualizarCliente(eq(5L), any())).thenReturn(cliente);
        Cliente actualizado = clientesController.actualizar(5L, cliente);
        assertEquals("juan", actualizado.getNombre());

    }
    
    @Test
    public void testEliminarCliente() {
        when(clienteService.eliminarCliente(1L)).thenReturn("Cliente Eliminado de el sistema");
        clientesController.eliminar(1L);
        verify(clienteService, times(1)).eliminarCliente(1L);
    }
    
    
}

    
        