package com.ista.backend.apirest.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ista.backend.apirest.model.User;
import com.ista.backend.apirest.repository.UserRepository;
import com.ista.backend.apirest.service.AwsService;
import com.ista.backend.apirest.service.UserService;
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


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/listaUsuarios")
    public ResponseEntity<List<User>> obtenerLista() {
        return new ResponseEntity<>(userService.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/crearUsuario")
    public ResponseEntity<?> createUser (@RequestBody User u) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(u));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readUser (@PathVariable (value = "id") Integer id){
        Optional<User> oUser = Optional.ofNullable(userService.findById(id));

        if (!oUser.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oUser);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser (@RequestBody User use, @PathVariable (value = "id") Integer id) {
        Optional<User> us = Optional.ofNullable(userService.findById(id));
        if (!us.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        us.get().setName(use.getName());
        us.get().setUrname(use.getUrname());
        us.get().setPassword(use.getPassword());
        us.get().setEmail(use.getEmail());
        us.get().setEnable(use.getEnable());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(us.get()));
    }

    @DeleteMapping("/EliminarUsuario/{id}")
    public ResponseEntity<?> deleteUser (@PathVariable (value = "id") Integer id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AwsService awsService;

    @GetMapping
    List<User> getAll(){
        return userRepository.findAll()
                .stream()
                .peek(usuario -> usuario.setImagenUrl(awsService.getObjectUrl(usuario.getImagenPath())))
                .collect(Collectors.toList());
    }

    @PostMapping
    User create(@RequestBody User usuario) {
        userRepository.save(usuario);
        usuario.setImagenUrl(awsService.getObjectUrl(usuario.getImagenPath()));
        usuario.setCedulaUrl(awsService.getObjectUrl(usuario.getCedula()));
        return usuario;
    }

}