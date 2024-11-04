package com.adbms.evaidhya.controller;

import com.adbms.evaidhya.requestDTO.DoctorRequestDTO;
import com.adbms.evaidhya.responseDTO.DoctorProfileResponseDTO;
import com.adbms.evaidhya.responseDTO.DoctorResponseDTO;
import com.adbms.evaidhya.responseDTO.MessageRes;
import com.adbms.evaidhya.responseDTO.PatientResponseDTO;
import com.adbms.evaidhya.service.DoctorService;
import com.adbms.evaidhya.service.PatientService;
import org.apache.coyote.Response;
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

    @Autowired
    private DoctorService doctorService;


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

    @PostMapping("/addDoctor")
    public ResponseEntity<DoctorResponseDTO> addDoctor(@RequestBody DoctorRequestDTO request){
        DoctorResponseDTO doctorResponseDTO = doctorService.addDoctor(request);
        return new ResponseEntity<>(doctorResponseDTO,HttpStatus.CREATED);
    }

    @GetMapping("/viewDoctors")
    public ResponseEntity<?> viewAllDoctors(){
        List<DoctorProfileResponseDTO> doctorProfileResponse = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctorProfileResponse,HttpStatus.OK);
    }

    @DeleteMapping("/deleteDoctor/{doctorId}")
    public ResponseEntity<MessageRes> deleteDoctor(@PathVariable("doctorId") Long doctorId) throws
            Exception{
        MessageRes response = new MessageRes();
        try{
            doctorService.deleteDoctor(doctorId);
        }
        catch(Exception e){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        response.setMessage("Doctor deleted successfully");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
