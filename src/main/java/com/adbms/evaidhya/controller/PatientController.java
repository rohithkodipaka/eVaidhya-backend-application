package com.adbms.evaidhya.controller;

import com.adbms.evaidhya.responseDTO.PatientResponseDTO;
import com.adbms.evaidhya.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/viewProfile/{patientId}")
    public ResponseEntity<PatientResponseDTO> viewPatientProfile(@PathVariable("patientId") Long patientId) throws Exception {
         PatientResponseDTO patientResponseDTO = patientService.viewPatientProfile(patientId);
         return new ResponseEntity<>(patientResponseDTO, HttpStatus.OK);
    }

}
