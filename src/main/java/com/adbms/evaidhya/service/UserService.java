package com.adbms.evaidhya.service;

import com.adbms.evaidhya.requestDTO.ChangePasswordRequestDTO;
import com.adbms.evaidhya.requestDTO.UpdatePasswordRequestDTO;
import com.adbms.evaidhya.requestDTO.signUpRequestDTO;
import com.adbms.evaidhya.responseDTO.MessageRes;
import com.adbms.evaidhya.responseDTO.userResponseDTO;

public interface UserService {

    userResponseDTO signUpPatient(signUpRequestDTO request);

    Boolean emailExists(String email);

    userResponseDTO signUpDoctor(signUpRequestDTO request);

    userResponseDTO signUpAdmin(signUpRequestDTO request);

    MessageRes changePassword(ChangePasswordRequestDTO request);

    MessageRes updatePassword(UpdatePasswordRequestDTO request);
}
