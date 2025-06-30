package com.example.Cliente_fullstack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Cliente_fullstack.Model.Cliente;
import com.example.Cliente_fullstack.repository.clienterepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private clienterepository clienterepository;
    
    public Cliente agregarCliente(Cliente ing){
        clienterepository.save(ing);
        return ing;
    }

    public List<Cliente> listaClientes(){
        return clienterepository.findAll();
    }

    public Cliente buscarporID(long id){
        return clienterepository.findById(id).get();
    }

    public String eliminarCliente(Long id){
        clienterepository.deleteById(id);
        return "Cliente Eliminado de el sistema";
    }
}
