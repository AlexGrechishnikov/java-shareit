package ru.practicum.shareit.user.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class User {

    private Long id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    @EqualsAndHashCode.Include
    private String email;

    public User(Long userId) {
        id = userId;
    }
}