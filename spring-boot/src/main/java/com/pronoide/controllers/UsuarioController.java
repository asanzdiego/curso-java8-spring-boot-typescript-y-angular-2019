package com.pronoide.controllers;

import java.util.Optional;

import javax.annotation.Resource;

import com.pronoide.entities.User;
import com.pronoide.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

	@Resource
    private UserRepository userRepository;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<Object> logout(final Authentication auth) {

        this.logger.debug("Entra en GET:/logout ... ");
        
        auth.setAuthenticated(false);
        SecurityContextHolder.clearContext();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<User> login(final Authentication auth) {

        this.logger.debug("Entra en GET:/login ... ");

        final User user = (User) auth.getPrincipal();

        final Optional<User> userDB = this.userRepository.findByUsername(user.getUsername());
        if (!userDB.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userDB.get(), HttpStatus.OK);
    }

}
