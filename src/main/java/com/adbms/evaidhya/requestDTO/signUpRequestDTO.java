package com.adbms.evaidhya.requestDTO;


import lombok.Data;

@Data
public class signUpRequestDTO {

    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;
}
