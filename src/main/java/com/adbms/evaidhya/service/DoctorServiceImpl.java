package com.adbms.evaidhya.service;

import com.adbms.evaidhya.entity.Address;
import com.adbms.evaidhya.entity.Doctor;
import com.adbms.evaidhya.entity.User;
import com.adbms.evaidhya.enumerations.ROLE;
import com.adbms.evaidhya.mapper.DoctorMapper;
import com.adbms.evaidhya.repository.DoctorRepository;
import com.adbms.evaidhya.repository.UserRepository;
import com.adbms.evaidhya.requestDTO.DoctorRequestDTO;
import com.adbms.evaidhya.responseDTO.DoctorProfileResponseDTO;
import com.adbms.evaidhya.responseDTO.DoctorResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public DoctorResponseDTO addDoctor(DoctorRequestDTO request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .userRole(ROLE.DOCTOR)
                .build();
        String password = createDefaultPassword(request.getFirstName(), request.getLastName());
        user.setPassword(passwordEncoder.encode(password));
        User savedUser = userRepository.save(user);
        DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();
        Doctor doctor = doctorMapper.toDoctor(request);
        doctor.setUser(savedUser);
        DoctorResponseDTO response = doctorMapper.fromDoctor(doctorRepository.save(doctor));
        response.setPassword(password);
        return response;
    }

    public String createDefaultPassword(String firstName, String lastName) {
        Random random = new Random();

        String part1 = firstName.length() >= 3 ? firstName.substring(0, 3) : firstName;
        String part2 = lastName.length() >= 3 ? lastName.substring(0, 3) : lastName;

        // Generate a random 4-digit number
        int randomNum = 1000 + random.nextInt(9000); // Ensures a 4-digit number

        StringBuilder password = new StringBuilder(part1 + part2 + randomNum);
        for (int i = 0; i < password.length(); i++) {
            int swapIndex = random.nextInt(password.length());
            char temp = password.charAt(i);
            password.setCharAt(i, password.charAt(swapIndex));
            password.setCharAt(swapIndex, temp);
        }

        return password.toString();

    }

    public List<DoctorProfileResponseDTO> getAllDoctors(){
        List<DoctorProfileResponseDTO> doctorsList = new ArrayList<>();
        List<Doctor> doctors = doctorRepository.findAll();
        for(Doctor doctor:doctors){
            doctorsList.add( doctorMapper.fromDoctorToDoctorProfile(doctor));
        }
        return doctorsList;
    }

    public void deleteDoctor(Long doctorId) throws Exception{
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        if(doctor.isEmpty()){
            throw new Exception("Doctor not found");
        }
        doctorRepository.delete(doctor.get());
    }
}
