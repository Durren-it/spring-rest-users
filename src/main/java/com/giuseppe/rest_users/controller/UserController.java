package com.giuseppe.rest_users.controller;

import com.giuseppe.rest_users.model.dto.User;
import com.giuseppe.rest_users.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST per la gestione degli utenti
 * */
@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    /**
     * Costruttore per iniettare il servizio utente
     * */
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * Restituisce una lista di utenti
     *
     * @return una lista di utenti
     * */
    @GetMapping
    public List<User> getAllUsers() {
        // TODO orderBy email, name, surname <> asc/desc
        // TODO limit results
        return userService.getAllUsers();
    }

    /**
     * Restituisce un utente dato il suo indirizzo email
     *
     * @param email l'email dell'utente
     * @return (200) l'utente corrispondente o (404) utente non trovato
     * */
    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    /**
     * Crea un nuovo utente
     *
     * @param user l'utente da creare
     * @return (201) Se l'utente è creato, (404) se email utente già in uso
     * */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        if (createdUser == null) return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    /**
     * Aggiorna i dati di un utente
     *
     * @param email l'email dell'utente da aggiornare
     * @param updatedUser i dati aggiornati
     * @return (200) se l'utente è aggiornato, (404) se l'utente con quella email non esiste
     * */
    @PutMapping("/{email}")
    public ResponseEntity<User> udpateUser(@PathVariable String email, @RequestBody User updatedUser) {
        User user = userService.updateUser(email, updatedUser);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    /**
     * Elimina un utente data una email
     *
     * @param email l'indirizzo email dell'utente da eliminare
     * @return (204) se l'utente è eliminato, (404) se non è trovato
     * */
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        boolean deleted = userService.deleteUser(email);
        if (deleted) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
