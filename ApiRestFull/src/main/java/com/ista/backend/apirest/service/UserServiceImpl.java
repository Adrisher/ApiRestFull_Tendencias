package com.ista.backend.apirest.service;

import com.ista.backend.apirest.model.User;
import com.ista.backend.apirest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Integer> implements UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public CrudRepository<User, Integer> getDao() {
        return userRepository;
    }

}
