package com.example.Cliente_fullstack.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Cliente_fullstack.Model.Cliente;

@Repository
public interface clienterepository extends JpaRepository<Cliente, Long> {

}
