package com.adbms.evaidhya.controller;


import com.adbms.evaidhya.entity.PrescriptionNotes;
import com.adbms.evaidhya.requestDTO.PrescriptionRequestDTO;
import com.adbms.evaidhya.service.PrescriptionNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescription")
public class PrescriptionNotesController {

    @Autowired
    private PrescriptionNotesService prescriptionNotesService;

    @PostMapping("/updatePrescriptionNotes")
    public ResponseEntity<?> updatePrescriptionNotes(@RequestBody PrescriptionRequestDTO request) throws Exception {
        List<PrescriptionNotes> response = prescriptionNotesService.updatePrescription(request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/view/prescription/{patientId}/{appointmentId}")
    public ResponseEntity<?> viewPatientPrescription(@PathVariable("patientId") Long patientId,
                                                     @PathVariable("appointmentId") Long appointmentId){
        List<PrescriptionNotes> response = prescriptionNotesService.viewPatientPrescription(patientId, appointmentId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
