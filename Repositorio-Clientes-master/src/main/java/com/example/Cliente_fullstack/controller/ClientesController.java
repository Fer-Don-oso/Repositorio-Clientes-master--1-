package com.example.Cliente_fullstack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import com.example.Cliente_fullstack.Model.Cliente;
import com.example.Cliente_fullstack.service.ClienteService;

@RestController
@RequestMapping("/api/v1/cliente")
@Tag(name = "Clientes", description = "Operaciones relacionadas con los clientes de Miku-Food")
public class ClientesController {

        @Autowired
        private ClienteService ClienteService;
        @Operation(summary = "Listar todos los clientes", description = "Obtiene una lista de todos los clientes registrados en el sistema")
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de pedidos obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "La lista se encuentra vacía (No hay pedidos)")
        })
        @GetMapping
        public ResponseEntity<List<Cliente>> listar(){
            List<Cliente> clientes = ClienteService.listaClientes();

            if (clientes.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(clientes);
        }
        @PostMapping("/agregar")
        @Operation(summary = "Agregar a nuevos clientes al sistema", description = "Permite insertar los datos de un nuevo cliente en el sistema")
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta, datos del cliente inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
            @ApiResponse(responseCode = "204", description = "No se pudo agregar el cliente")
        })
        public ResponseEntity<Cliente> guardar(@RequestBody Cliente cliente) {
            Cliente clienteNuevo = ClienteService.agregarCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteNuevo);
        }

        @GetMapping("/buscar/{idUsuario}")
        @Operation(summary = "Buscar un Usuario por su ID", description = "Permite buscar un cliente específico por su ID en el sistema")
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Este Cliente no existe en el sistema"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta, Error en el ID proporcionado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
        })
        public ResponseEntity<?> buscar(@PathVariable Integer id) {
            try {
                Cliente cliente = ClienteService.buscarporID(id);
                return ResponseEntity.ok(cliente);
            } catch (Exception e ) {
                return ResponseEntity.notFound().build();
            }
        }

        @PutMapping("/actualizar/{idUsuario}")
        @Operation(summary = "Actualizar informacion de los Clientes", description = "Permite modificar los datos de un cliente existente en el sistema")
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta, datos del cliente inválidos"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
            @ApiResponse(responseCode = "204", description = "No se pudo actualizar el cliente")
        })
        public Cliente actualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
            return ClienteService.actualizarCliente(id, cliente);
        }
        @DeleteMapping("/eliminar/{idUsuario}")
        @Operation(summary = "Eliminar un Cliente", description = "Permite Eliminar un cliente del sistema por su ID")
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta, Error en el ID proporcionado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
            @ApiResponse(responseCode = "204", description = "No se pudo eliminar el cliente")
        })
        public ResponseEntity<?> eliminar(@PathVariable Long id) {
            try {
                ClienteService.eliminarCliente(id);
                return ResponseEntity.noContent().build();
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }
}
