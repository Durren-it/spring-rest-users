package com.giuseppe.rest_users.service.impl;

import com.giuseppe.rest_users.model.dto.User;
import com.giuseppe.rest_users.service.api.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione dell'interfaccia di servizio per la gestione degli utenti
 * */
@Service
public class UserServiceImpl implements IUserService {

    private final List<User> users = new ArrayList<>();

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(this.users);
    }

    @Override
    public User getUserByEmail(String email) {
        return this.users.stream()
                .filter(user -> user.email().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User createUser(User user) {
        for (User u : users) {
            if (u.email().equals(user.email())){
                return null;
            }
        }
        this.users.add(user);
        return user;
    }

    @Override
    public User updateUser(String email, User updatedUser) {
        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).email().equals(email)) {
                this.users.set(i, updatedUser);
                return updatedUser;
            }
        }
        return null;
    }

    @Override
    public boolean deleteUser(String email) {
        return this.users.removeIf(user -> user.email().equals(email));
    }
}
