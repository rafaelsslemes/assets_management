package com.spexcode.asset_management.model.dto;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spexcode.asset_management.model.User;

public class UserDTO {
    private String login;
    private String name;
    private String email;

    private String token; // JWT Token

    public static UserDTO create(User user, String token){
        UserDTO userDTO = new ModelMapper().map(user, UserDTO.class);
        userDTO.token = token;
        return userDTO;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    

}
