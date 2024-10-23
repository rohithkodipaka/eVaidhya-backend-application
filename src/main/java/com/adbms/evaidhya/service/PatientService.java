package com.adbms.evaidhya.service;

import com.adbms.evaidhya.requestDTO.PatientRequestDTO;
import com.adbms.evaidhya.responseDTO.PatientResponseDTO;

public interface PatientService {

    public Long getPatientId(Long userId) throws Exception;

    PatientResponseDTO viewPatientProfile(Long patientId) throws Exception;

    PatientResponseDTO updatePatientProfile(PatientRequestDTO patientRequestDTO) throws Exception;
}
