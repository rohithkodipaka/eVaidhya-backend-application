package com.adbms.evaidhya.mapper;


import com.adbms.evaidhya.entity.Address;
import com.adbms.evaidhya.entity.Doctor;
import com.adbms.evaidhya.requestDTO.DoctorRequestDTO;
import com.adbms.evaidhya.responseDTO.DoctorProfileResponseDTO;
import com.adbms.evaidhya.responseDTO.DoctorResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class DoctorMapper {

    public Doctor toDoctor(DoctorRequestDTO request){
        return Doctor.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .specialization(request.getSpecialization())
                .yearsOfExperience(request.getYearsOfExperience())
                .phoneNo(request.getPhoneNo())
                .address(Address.builder()
                        .state(request.getAddress().getState())
                        .city(request.getAddress().getCity())
                        .aptNumber(request.getAddress().getAptNumber())
                        .street(request.getAddress().getStreet())
                        .zipcode(request.getAddress().getZipcode())
                        .build())
                .build();
    }

    public DoctorResponseDTO fromDoctor(Doctor doctor) {
        return DoctorResponseDTO.builder()
                .username(doctor.getEmail())
                .userId(doctor.getUser().getId())
                .doctorId(doctor.getId())
                .build();
    }

    public DoctorProfileResponseDTO fromDoctorToDoctorProfile(Doctor request) {
        return DoctorProfileResponseDTO.builder()
                .doctorId(request.getId())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .specialization(request.getSpecialization())
                .yearsOfExperience(request.getYearsOfExperience())
                .phoneNo(request.getPhoneNo())
                .address(Address.builder()
                        .state(request.getAddress().getState())
                        .city(request.getAddress().getCity())
                        .aptNumber(request.getAddress().getAptNumber())
                        .street(request.getAddress().getStreet())
                        .zipcode(request.getAddress().getZipcode())
                        .build())
                .build();
    }
}
