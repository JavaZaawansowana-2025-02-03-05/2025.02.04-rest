package com.comarch.szkolenia.rest.database;

import com.comarch.szkolenia.rest.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements IUserRepository {
    private final List<User> users = new ArrayList<>();
    private long id_sequence = 0;

    public UserRepository() {
        this.users.add(new User(++this.id_sequence, "janusz", "Janusz", "Kowalski", 30));
        this.users.add(new User(++this.id_sequence, "wiesiek", "Wieslaw", "Malinowski", 40));
        this.users.add(new User(++this.id_sequence, "zbyszek", "Zbigniew", "Krupa", 50));
        this.users.add(new User(++this.id_sequence, "bogdan", "Bogdan", "Jakis", 60));
        this.users.add(new User(++this.id_sequence, "mateusz", "Mateusz", "Bereda", 70));
    }

    @Override
    public User persist(User user) {
        Optional<User> userBox = getById(user.getId());
        if(userBox.isEmpty()) {
            user.setId(++this.id_sequence);
            this.users.add(user);
            return user;
        } else {
            userBox.get().setLogin(user.getLogin());
            userBox.get().setName(user.getName());
            userBox.get().setSurname(user.getSurname());
            userBox.get().setAge(user.getAge());
            return userBox.get();
        }
    }

    @Override
    public void delete(final long id) {
        this.users.removeIf(user -> user.getId() == id);
    }

    @Override
    public List<User> getAll() {
        return this.users;
    }

    @Override
    public Optional<User> getById(final long id) {
        return this.users.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    @Override
    public List<User> getByAge(final int age) {
        return this.users.stream().filter(u -> u.getAge() == age).toList();
    }
}
