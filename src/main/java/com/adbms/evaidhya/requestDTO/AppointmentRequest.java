package com.adbms.evaidhya.requestDTO;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequest {

    private Long patientId;

    private Long doctorId;

    private LocalDate date;

    private LocalTime time;

    private String reasonForAppointment;
}
