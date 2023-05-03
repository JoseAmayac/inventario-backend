package com.stock.inventario.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

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

    // TODO: Se podría usar Lombock para evitar tener que agregar getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
