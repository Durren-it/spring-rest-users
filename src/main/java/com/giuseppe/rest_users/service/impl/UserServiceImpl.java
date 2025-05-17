package com.giuseppe.rest_users.service.impl;

import com.giuseppe.rest_users.model.dto.User;
import com.giuseppe.rest_users.service.api.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementazione dell'interfaccia di servizio per la gestione degli utenti
 * */
@Service
public class UserServiceImpl implements IUserService {

    private final List<User> users = new ArrayList<>();

    @Override
    public List<User> getAllUsers(String orderBy, Integer limit) {
        String[] split = orderBy.toLowerCase().split(":");
        boolean descending = (split.length > 1 && split[1].equals("desc"));

        Comparator<User> comparator;

        switch (split[0]) {
            case "name":
                comparator = Comparator.comparing(User::name);
                break;
            case "surname":
                comparator = Comparator.comparing(User::surname);
                break;
            case "email":
            default:
                comparator = Comparator.comparing(User::email);
                break;
        }

        if (descending) comparator = comparator.reversed();

        Stream<User> stream = users.stream().sorted(comparator);

        if (limit != null)
            stream = stream.limit(limit);

        return stream.collect(Collectors.toList());
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
