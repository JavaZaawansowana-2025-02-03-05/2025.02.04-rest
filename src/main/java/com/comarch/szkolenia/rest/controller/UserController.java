package com.comarch.szkolenia.rest.controller;

import com.comarch.szkolenia.rest.database.IUserRepository;
import com.comarch.szkolenia.rest.model.User;
import com.comarch.szkolenia.rest.model.dto.UsersDTO;
import com.comarch.szkolenia.rest.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserController {

    private final IUserService userService;

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<User> add(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.userService.persist(user, 0));
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public UsersDTO getUsers(@RequestParam(value = "age", required = false) Integer age) {
        if(age == null) {
            return new UsersDTO(this.userService.getAll());
        }
        return new UsersDTO(this.userService.getByAge(age));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getById(@PathVariable("id") long id) {
        return this.userService.getById(id)
                .map(u -> ResponseEntity.ok().body(u))
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@RequestBody User user,
                                           @PathVariable("id") long id) {
        User userFromDb = this.userService.persist(user, id);
        if(id == userFromDb.getId()) {
            return ResponseEntity.ok().body(userFromDb);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userFromDb);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        this.userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
