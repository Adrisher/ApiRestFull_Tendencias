package com.ista.backend.apirest.model;


import lombok.*;
import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "nombre", length = 100)
    private String name;

    @Column(name = "apellido", length = 100)
    private String urname;

    @Column(name = "clave", length = 100, nullable = false, unique = true)
    private String password;

    @Column(name = "correo", length = 200, nullable = false, unique = true)
    private String email;

    @Column(name = "estado")
    private String enable;

    @NonNull
    private String titulo;
    private String imagenPath;
    private String cedula;

    @Transient
    private String imagenUrl;

    @Transient
    private String cedulaUrl;
}
