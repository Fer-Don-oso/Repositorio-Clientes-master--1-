package com.example.Cliente_fullstack.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Clientes")
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(nullable = false, length = 20)
    private String apellido_paterno;

    @Column(nullable = false, length = 20)
    private String apellido_materno;

    @Column(nullable = false, length = 20)
    private String correo;

    @Column(nullable = false, length = 20)
    private Date fechaNacimiento;

    @Column(nullable = false, length = 20)
    private Integer telefono;

}
