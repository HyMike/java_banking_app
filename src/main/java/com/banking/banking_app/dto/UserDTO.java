package com.banking.banking_app.dto;


import com.banking.banking_app.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;



public class UserDTO {

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    public UserDTO(){}
    public UserDTO(User user){
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }


}

