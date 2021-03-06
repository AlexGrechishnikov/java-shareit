package ru.practicum.shareit.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;

    @NotBlank(message = "Поле не должно быть пустым или null")
    private String name;

    @NotBlank
    @Email
    private String email;
}