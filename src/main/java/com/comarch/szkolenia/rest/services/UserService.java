package com.comarch.szkolenia.rest.services;

import com.comarch.szkolenia.rest.database.IUserRepository;
import com.comarch.szkolenia.rest.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    @Override
    public User persist(User user, Long id) {
        user.setId(id);
        return this.userRepository.persist(user);
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.getAll();
    }

    @Override
    public List<User> getByAge(int age) {
        return this.userRepository.getByAge(age);
    }

    @Override
    //@Transactional - spring data
    public Optional<User> getById(long id) {
        return this.userRepository.getById(id);
    }

    @Override
    public void delete(long id) {
        this.userRepository.delete(id);
    }
}
