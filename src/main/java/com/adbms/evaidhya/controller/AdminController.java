package com.adbms.evaidhya.controller;

import com.adbms.evaidhya.responseDTO.MessageRes;
import com.adbms.evaidhya.responseDTO.PatientResponseDTO;
import com.adbms.evaidhya.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private PatientService patientService;


    @GetMapping("/viewPatients")
    public ResponseEntity<?> viewPatientList(){
        List<PatientResponseDTO> patientResponseDTO = patientService.getAllPatients();
        return new ResponseEntity<>(patientResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deletePatient/{patientId}")
    public ResponseEntity<MessageRes> deletePatient(@PathVariable("patientId") Long patientId) throws
            Exception{
        MessageRes response = new MessageRes();
        try{
            patientService.deletePatient(patientId);
        }
        catch(Exception e){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        response.setMessage("Patient deleted successfully");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
