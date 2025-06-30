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

import com.example.Cliente_fullstack.Model.Cliente;
import com.example.Cliente_fullstack.service.ClienteService;


@RestController
@RequestMapping("/api/v1/cliente")
public class ClientesController {

        @Autowired
        private ClienteService ClienteService;

        @GetMapping()
        public ResponseEntity<List<Cliente>> listar(){
            List<Cliente> Cliente = ClienteService.listaClientes();

            if (Cliente.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(Cliente);
        }
        @PostMapping("/agregar")
        public ResponseEntity<Cliente> guardar(@RequestBody Cliente cliente) {
            Cliente clienteNuevo = ClienteService.agregarCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteNuevo);
        }

        @GetMapping("/buscar/{id}")
        public ResponseEntity<Cliente> buscar(@PathVariable Integer id) {
            try {
                Cliente cliente = ClienteService.buscarporID(id);
                return ResponseEntity.ok(cliente);
            } catch (Exception e ) {
                return ResponseEntity.notFound().build();
            }
        }

        @PutMapping("/{id}")
        public ResponseEntity<Cliente> actualizar(@PathVariable Integer id, @RequestBody Cliente cliente) {
            try {
                Cliente cli = ClienteService.buscarporID(id);
                cli.setNombre(cliente.getNombre());
                cli.setApellido_paterno(cliente.getApellido_paterno());
                cli.setApellido_materno(cliente.getApellido_materno());
                cli.setCorreo(cliente.getCorreo());
                cli.setFechaNacimiento(cliente.getFechaNacimiento());
                cli.setTelefono(cliente.getTelefono());

                ClienteService.agregarCliente(cli);
                return ResponseEntity.ok(cliente);
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }
        @DeleteMapping("/{id}") 
        public ResponseEntity<?> eliminar(@PathVariable Long id) {
            try {
                ClienteService.eliminarCliente(id);
                return ResponseEntity.noContent().build();
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }
}
