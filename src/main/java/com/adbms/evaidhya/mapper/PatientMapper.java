package com.adbms.evaidhya.mapper;


import com.adbms.evaidhya.entity.Patient;
import com.adbms.evaidhya.responseDTO.PatientResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class PatientMapper {

    public PatientResponseDTO fromPatient(Patient patient){

        return PatientResponseDTO.builder()
                .patientId(patient.getId())
                .email(patient.getEmail())
                .gender(patient.getGender())
                .phoneNo(patient.getPhoneNo())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .address(patient.getAddress())
                .build();
    }
}
