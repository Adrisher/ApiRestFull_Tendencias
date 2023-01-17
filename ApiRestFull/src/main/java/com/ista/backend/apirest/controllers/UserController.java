package com.ista.backend.apirest.controllers;

import com.ista.backend.apirest.model.User;
import com.ista.backend.apirest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/listUsers")
    public ResponseEntity<List<User>> obtenerLista() {
        return new ResponseEntity<>(userService.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/createUser")
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
            us.get().setSurname(use.getSurname());
            us.get().setEmail(use.getEmail());
            us.get().setEnabled(use.getEnabled());
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(us.get()));

    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser (@PathVariable (value = "id") Integer id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
