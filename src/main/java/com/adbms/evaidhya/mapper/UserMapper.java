package com.adbms.evaidhya.mapper;

import com.adbms.evaidhya.entity.User;
import com.adbms.evaidhya.requestDTO.signUpRequestDTO;
import com.adbms.evaidhya.responseDTO.userResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User toUser(signUpRequestDTO request){
        return User.builder()
                .id(request.getId())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
    }

    public userResponseDTO fromUser(User user){
        return  userResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userRole(user.getUserRole())
                .build();
    }
}
