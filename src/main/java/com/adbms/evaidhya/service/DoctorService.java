package com.adbms.evaidhya.service;

import com.adbms.evaidhya.entity.Doctor;
import com.adbms.evaidhya.requestDTO.DoctorRequestDTO;
import com.adbms.evaidhya.responseDTO.DoctorProfileResponseDTO;
import com.adbms.evaidhya.responseDTO.DoctorResponseDTO;

import java.util.List;

public interface DoctorService {

    DoctorResponseDTO addDoctor(DoctorRequestDTO request);

    List<DoctorProfileResponseDTO> getAllDoctors();

    void deleteDoctor(Long doctorId) throws Exception;

    Doctor getDoctorByUserId(Long userId);
}
