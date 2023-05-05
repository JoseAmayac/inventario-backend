package com.stock.inventario.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Getter
@Setter
public class UserCreationDTO implements Serializable {
    @NotNull(message = "El nombre es obligatorio")
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    @NotNull(message = "El apellido es obligatorio")
    @NotBlank(message = "El apellido no puede estar vacío")
    private String lastName;
    private String phone;
    @NotNull(message = "El correo electrónico es obligatorio")
    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Email(message = "Formato de correo electrónico no es válido")
    private String email;
    @NotNull(message = "La contraseña es obligatoria")
    @NotBlank(message = "La contraseña no puede estar vacía")
    //@Min(value = 8, message = "Debe contener al menos 8 caracteres")
    @Length(min = 8, message = "Debe contener al menos 8 caracteres")
    private String password;
    @NotNull(message = "El nombre de usuario es obligatorio")
    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String username;
}
