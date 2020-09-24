package com.bestcommerce.requests;

import javax.validation.constraints.NotBlank;

public class LoginRequest {


    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private Boolean rememberMe = false;

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}