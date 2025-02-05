package com.comarch.szkolenia.rest.services;

import com.comarch.szkolenia.rest.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User persist(User user, Long id);
    List<User> getAll();
    List<User> getByAge(int age);
    Optional<User> getById(long id);
    void delete(long id);
}
