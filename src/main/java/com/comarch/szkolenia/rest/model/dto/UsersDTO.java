package com.comarch.szkolenia.rest.model.dto;

import com.comarch.szkolenia.rest.model.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsersDTO {
    private List<User> users;
}
