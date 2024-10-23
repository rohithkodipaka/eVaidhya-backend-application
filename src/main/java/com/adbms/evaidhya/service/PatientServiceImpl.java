package com.adbms.evaidhya.service;

import com.adbms.evaidhya.entity.Patient;
import com.adbms.evaidhya.mapper.PatientMapper;
import com.adbms.evaidhya.repository.PatientRepository;
import com.adbms.evaidhya.requestDTO.PatientRequestDTO;
import com.adbms.evaidhya.responseDTO.PatientResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientMapper patientMapper;

    public Long getPatientId(Long userId) throws Exception {
        Patient patient = patientRepository.findByUserId(userId);
        if(patient == null){
            throw new Exception("No patient found with given userId");
        }
        return patient.getId();
    }

    public PatientResponseDTO viewPatientProfile(Long patientId) throws Exception {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if(patientOptional.isEmpty()){
            throw new Exception("Patient not found with given Id");
        }
        Patient patient = patientOptional.get();
        return patientMapper.fromPatient(patient);
    }
}
