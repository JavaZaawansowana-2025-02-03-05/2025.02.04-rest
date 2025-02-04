package com.comarch.szkolenia.rest.database;

import com.comarch.szkolenia.rest.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    User persist(User user);
    void delete(long id);
    List<User> getAll();
    Optional<User> getById(long id);
    List<User> getByAge(int age);
}
