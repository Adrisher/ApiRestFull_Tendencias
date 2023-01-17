package com.ista.backend.apirest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class User implements Serializable {


    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", length = 50)
    private String name;

    @Column(name = "apellido", length = 50)
    private String surname;

    @Column(name = "mail", length = 150, nullable = false, unique = true)
    private String email;

    @Column(name = "activo")
    private String enabled;


}
