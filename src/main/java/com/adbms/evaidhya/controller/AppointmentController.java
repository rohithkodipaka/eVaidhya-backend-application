package com.adbms.evaidhya.controller;


import com.adbms.evaidhya.entity.Appointment;
import com.adbms.evaidhya.entity.DoctorAvailability;
import com.adbms.evaidhya.requestDTO.AppointmentRequest;
import com.adbms.evaidhya.responseDTO.DoctorTimeSlotResponse;
import com.adbms.evaidhya.responseDTO.MessageRes;
import com.adbms.evaidhya.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


    @GetMapping("/doctor/{doctorId}/slots")
    public ResponseEntity<?> getDoctorSlots(@PathVariable("doctorId") Long doctorId,
                                            @RequestParam("date") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
                                    LocalDate date) throws Exception {
        DoctorTimeSlotResponse response = appointmentService.getDoctorSlotsForAppointment(doctorId, date);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<?> bookAppointment(@RequestBody AppointmentRequest request){
            Appointment bookedAppointment = appointmentService.bookAppointment(request);
            return new ResponseEntity<>(bookedAppointment,HttpStatus.OK);
    }

    @DeleteMapping("/cancel/{appointmentId}")
    public ResponseEntity<?> cancelAppointment(@PathVariable("appointmentId") Long appointmentId) throws Exception{
        appointmentService.cancelAppointment(appointmentId);
        return new ResponseEntity<>("Appointment cancelled",HttpStatus.OK);
    }
}
