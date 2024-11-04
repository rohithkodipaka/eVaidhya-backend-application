package com.adbms.evaidhya.service;

import com.adbms.evaidhya.entity.Patient;
import com.adbms.evaidhya.entity.User;
import com.adbms.evaidhya.enumerations.ROLE;
import com.adbms.evaidhya.mapper.UserMapper;
import com.adbms.evaidhya.repository.PatientRepository;
import com.adbms.evaidhya.repository.UserRepository;
import com.adbms.evaidhya.requestDTO.signUpRequestDTO;
import com.adbms.evaidhya.responseDTO.userResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.adbms.evaidhya.responseDTO.userResponseDTO;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserMapper userMapper;

    public userResponseDTO signUpPatient(signUpRequestDTO request){
         User user = userMapper.toUser(request);
         user.setUserRole(ROLE.PATIENT);
         User savedUser = userRepository.save(user);

         Patient patient = Patient.builder()
                 .email(request.getEmail())
                 .firstName(request.getFirstName())
                 .lastName(request.getLastName())
                 .user(savedUser)
                 .build();
         patientRepository.save(patient);
         return userMapper.fromUser(savedUser);

    }

    public Boolean emailExists(String email){
        return userRepository.findFirstByEmail(email)!=null;
    }

    public userResponseDTO signUpDoctor(signUpRequestDTO request){
        User user = userMapper.toUser(request);
        user.setUserRole(ROLE.DOCTOR);
        return userMapper.fromUser(userRepository.save(user));
    }
    public userResponseDTO signUpAdmin(signUpRequestDTO request){
        User user = userMapper.toUser(request);
        user.setUserRole(ROLE.ADMIN);
        return userMapper.fromUser(userRepository.save(user));
    }
}
