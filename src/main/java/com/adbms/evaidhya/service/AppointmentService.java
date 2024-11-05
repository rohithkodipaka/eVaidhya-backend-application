package com.adbms.evaidhya.service;

import com.adbms.evaidhya.entity.Appointment;
import com.adbms.evaidhya.requestDTO.AppointmentRequest;
import com.adbms.evaidhya.responseDTO.DoctorTimeSlotResponse;

import java.time.LocalDate;

public interface AppointmentService {
    DoctorTimeSlotResponse getDoctorSlotsForAppointment(Long doctorId, LocalDate date) throws Exception;

    Appointment bookAppointment(AppointmentRequest request);

    void cancelAppointment(Long appointmentId) throws Exception;
}
