package com.adbms.evaidhya.service;

import com.adbms.evaidhya.entity.Address;
import com.adbms.evaidhya.entity.Patient;
import com.adbms.evaidhya.mapper.PatientMapper;
import com.adbms.evaidhya.repository.AddressRepository;
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
    private AddressRepository addressRepository;

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

    public PatientResponseDTO updatePatientProfile(PatientRequestDTO patientRequestDTO) throws Exception{
        Optional<Patient> patientOptional = patientRepository.findByEmail(patientRequestDTO.getEmail());
        if(patientOptional.isEmpty()){
                throw new Exception("No patient found with given username");
        }
        Patient patient = patientOptional.get();
        patient.setGender(patientRequestDTO.getGender());
        patient.setPhoneNo(patientRequestDTO.getPhoneNo());

        Address address = Address.builder()
                .aptNumber(patientRequestDTO.getAddress().getAptNumber())
                .city(patientRequestDTO.getAddress().getCity())
                .street(patientRequestDTO.getAddress().getStreet())
                .state(patientRequestDTO.getAddress().getState())
                .zipcode(patientRequestDTO.getAddress().getZipcode())
                .build();

        Address savedAddress = addressRepository.save(address);

        patient.setAddress(savedAddress);

        Patient savedPatient = patientRepository.save(patient);
        return patientMapper.fromPatient(savedPatient);

    }
}
