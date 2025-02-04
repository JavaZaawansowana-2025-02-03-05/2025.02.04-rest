package com.comarch.szkolenia.rest.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private long id;
    private String login;
    private String name;
    private String surname;
    private int age;
}
