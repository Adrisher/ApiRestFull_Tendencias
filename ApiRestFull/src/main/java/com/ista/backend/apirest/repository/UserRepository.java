package com.ista.backend.apirest.repository;

import com.ista.backend.apirest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
