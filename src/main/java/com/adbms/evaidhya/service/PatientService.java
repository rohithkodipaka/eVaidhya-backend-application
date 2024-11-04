package com.adbms.evaidhya.service;

import com.adbms.evaidhya.entity.Patient;
import com.adbms.evaidhya.requestDTO.PatientRequestDTO;
import com.adbms.evaidhya.responseDTO.PatientResponseDTO;

import java.util.List;

public interface PatientService {

    public Long getPatientId(Long userId) throws Exception;

    PatientResponseDTO viewPatientProfile(Long patientId) throws Exception;

    PatientResponseDTO updatePatientProfile(PatientRequestDTO patientRequestDTO) throws Exception;

    List<PatientResponseDTO> getAllPatients();

    void deletePatient(Long patientId) throws Exception;

    Patient findPatientById(Long patientId) throws Exception;
}
