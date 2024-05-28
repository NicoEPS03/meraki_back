package com.meraki.back.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDto {
    @NotNull(message = "Document is obligatory")
    @Size(min = 5, max = 15, message = "The document must be between 5 and 15 characters")
    private String document;
    @NotNull(message = "Password is obligatory")
    @Size(min = 8, max = 30, message = "The password must be between 8 and 30 characters")
    private String password;

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
