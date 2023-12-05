package com.puneeth.expensemanager.Dto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    @NotBlank(message = "Please enter your name")
    private String name;

    @Email(message = "enter valid email")
    private String email;
    @Size(min=5, message = "password should have minimum of 5 characters")
    private String password;
    
}
