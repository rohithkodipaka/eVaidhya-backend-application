package com.adbms.evaidhya.service;

import com.adbms.evaidhya.entity.PatientMedicalChart;
import com.adbms.evaidhya.requestDTO.PatientMedicalChartRequestDTO;

public interface PatientMedicalChartService {

    public PatientMedicalChart updatePatientMedicalChart(PatientMedicalChartRequestDTO request) throws Exception;

    public PatientMedicalChart getMedicalChart(Long patientId) throws Exception;
}
