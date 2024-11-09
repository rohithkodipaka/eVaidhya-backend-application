package com.adbms.evaidhya.controller;


import com.adbms.evaidhya.entity.PatientMedicalChart;
import com.adbms.evaidhya.requestDTO.PatientMedicalChartRequestDTO;
import com.adbms.evaidhya.service.PatientMedicalChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicalChart")
public class PatientMedicalChartController {

    @Autowired
    private PatientMedicalChartService patientMedicalChartService;

    @PostMapping("/update")
    public ResponseEntity<?> updatePatientMedicalChart(@RequestBody PatientMedicalChartRequestDTO request) throws Exception{
        PatientMedicalChart patientMedicalChart = patientMedicalChartService.updatePatientMedicalChart(request);
        return new ResponseEntity<>(patientMedicalChart, HttpStatus.OK);
    }

    @PostMapping("/getMedicalChart/{patientId}")
    public ResponseEntity<?> getPatientMedicalChart(@PathVariable("patientId") Long patientId) throws Exception{
        PatientMedicalChart patientMedicalChart = patientMedicalChartService.getMedicalChart(patientId);
        return new ResponseEntity<>(patientMedicalChart,HttpStatus.OK);
    }

}
