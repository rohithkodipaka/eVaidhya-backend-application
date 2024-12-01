package com.adbms.evaidhya.requestDTO;


import lombok.Data;

@Data
public class ChangePasswordRequestDTO {

    private String userId;

    private String oldPassword;

    private String newPassword;
}
