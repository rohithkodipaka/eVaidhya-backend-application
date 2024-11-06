package com.adbms.evaidhya.service;

import com.adbms.evaidhya.entity.Appointment;
import com.adbms.evaidhya.requestDTO.AppointmentRequest;
import com.adbms.evaidhya.responseDTO.DoctorTimeSlotResponse;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {
    DoctorTimeSlotResponse getDoctorSlotsForAppointment(Long doctorId, LocalDate date) throws Exception;

    Appointment bookAppointment(AppointmentRequest request);

    void cancelAppointment(Long appointmentId) throws Exception;

    List<Appointment> viewAllPatientAppointments(Long patientId);

    List<Appointment> viewAllDoctorAppointments(Long doctorId);

    List<Appointment> getAllAppointments();

    Appointment updateAppointmentStatus(Long appointmentId, int statusCode) throws Exception;
}
