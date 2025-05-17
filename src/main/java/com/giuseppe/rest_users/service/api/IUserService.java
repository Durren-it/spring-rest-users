package com.giuseppe.rest_users.service.api;

import com.giuseppe.rest_users.model.dto.User;

import java.util.List;

/**
 * Interfaccia per la gestione delle operazioni CRUD sugli utenti
 */
public interface IUserService {

    /**
     * Restituisce tutti gli utenti.
     *
     * @return Una lista di utenti
     */
    List<User> getAllUsers(String orderBy, Integer limit);

    /**
     * Restituisce un utente dato il suo indirizzo email.
     *
     * @param email l'email dell'utente
     * @return l'utente corrispondente o null se non esiste
     */
    User getUserByEmail(String email);

    /**
     * Crea un nuovo utente.
     *
     * @param user l'utente da creare
     * @return l'utente creato o null se esiste
     */
    User createUser(User user);

    /**
     * Aggiorna un utente esistente.
     *
     * @param email l'email dell'utente da aggiornare
     * @param updatedUser i dati aggiornati dell'utente
     * @return l'utente aggiornato o null se non esiste
     */
    User updateUser(String email, User updatedUser);

    /**
     * Elimina un utente dato il suo indirizzo email
     *
     * @param email l'email dell'utente da eliminare
     * @return true se l'utente è stato eliminato, false altrimenti
     */
    boolean deleteUser(String email);
}
