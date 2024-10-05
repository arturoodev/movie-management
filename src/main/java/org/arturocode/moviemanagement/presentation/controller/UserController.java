package org.arturocode.moviemanagement.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.arturocode.moviemanagement.exception.ObjectNotFoundException;
import org.arturocode.moviemanagement.presentation.dto.request.SaveUser;
import org.arturocode.moviemanagement.presentation.dto.response.GetUser;
import org.arturocode.moviemanagement.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<GetUser>> getAllUsers() {
        return ResponseEntity.ok(userService.finaAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity<GetUser> getOneUserById(@PathVariable String username) {
        try {
            return ResponseEntity.ok(userService.findOneByUsername(username));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<GetUser> createUser(@Valid @RequestBody SaveUser saveDto, HttpServletRequest request) {
        try {
            GetUser userSaved = userService.createOne(saveDto);
            String baseUrl = request.getHttpServletMapping().toString();
            URI uri = URI.create(baseUrl + "/" + userSaved.username());
            return ResponseEntity.created(uri).body(userSaved);
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<GetUser> updateUser(@Valid @RequestBody SaveUser saveDto, @PathVariable String username) {
        try {
            GetUser userSaved = userService.updateOneByUsername(username, saveDto);
            return ResponseEntity.ok(userSaved);
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        try {
            userService.deleteOneByUsername(username);
            return ResponseEntity.noContent().build();
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
