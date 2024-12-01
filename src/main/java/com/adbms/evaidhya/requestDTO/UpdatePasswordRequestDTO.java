package com.adbms.evaidhya.requestDTO;


import lombok.Data;

@Data
public class UpdatePasswordRequestDTO {

    private String email;

    private String password;
}
