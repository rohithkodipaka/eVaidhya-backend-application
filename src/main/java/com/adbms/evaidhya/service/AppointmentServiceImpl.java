package com.adbms.evaidhya.service;


import com.adbms.evaidhya.entity.Appointment;
import com.adbms.evaidhya.entity.Doctor;
import com.adbms.evaidhya.entity.DoctorAvailability;
import com.adbms.evaidhya.entity.Patient;
import com.adbms.evaidhya.enumerations.STATUS;
import com.adbms.evaidhya.repository.AppointmentRepository;
import com.adbms.evaidhya.repository.DoctorAvailabilityRepository;
import com.adbms.evaidhya.repository.DoctorRepository;
import com.adbms.evaidhya.repository.PatientRepository;
import com.adbms.evaidhya.requestDTO.AppointmentRequest;
import com.adbms.evaidhya.responseDTO.DoctorTimeSlotResponse;
import com.adbms.evaidhya.util.TimeSlotDivisionUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    private DoctorAvailabilityRepository availabilityRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public DoctorTimeSlotResponse getDoctorSlotsForAppointment(Long doctorId, LocalDate date) throws Exception{
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println(dayOfWeek);
        Optional<DoctorAvailability> availabilityOptional = availabilityRepository.findByDoctorIdAndDayOfWeek(doctorId, dayOfWeek);
        DoctorAvailability availability = new DoctorAvailability();
        if(availabilityOptional.isPresent()){
             availability = availabilityOptional.get();
        }
        List<LocalTime> timeSlots = TimeSlotDivisionUtil.generateTimeSlots(availability.getStartTime(),availability.getEndTime(),60);

        List<LocalTime> bookedSlots = appointmentRepository.findByDoctorIdAndAppointmentDate(doctorId,date)
                .stream()
                .map(Appointment::getAppointmentTime)
                .toList();

        List<LocalTime> availableSlots = timeSlots.stream()
                .filter(slot->!bookedSlots.contains(slot))
                .toList();

        return DoctorTimeSlotResponse.builder()
                .availableSlots(availableSlots)
                .bookedSlots(bookedSlots)
                .build();
    }

    @Transactional
    public Appointment bookAppointment(AppointmentRequest request){
        LocalDate appointmentDate = request.getDate();
        LocalTime appointmentTime = request.getTime();
        List<Appointment> bookedAppointments = appointmentRepository.findByDoctorIdAndAppointmentDate(request.getDoctorId(),
                appointmentDate);
        boolean isSlotBooked = bookedAppointments.stream()
                .anyMatch(appointment->appointment.getAppointmentTime().equals(appointmentTime));

        if(isSlotBooked){
            throw new RuntimeException("The selected time slot is already booked. Please try another time slot");
        }
        Optional<Doctor> doctor = doctorRepository.findById(request.getDoctorId());
        Doctor doc = new Doctor();
        if(doctor.isPresent()){
            doc = doctor.get();
        }
        Optional<Patient> patient = patientRepository.findById(request.getPatientId());
        Patient pat = new Patient();
        if(patient.isPresent()){
            pat = patient.get();
        }
        System.out.println("Saving appointment with Doctor ID: " + doc.getId() +
                ", Patient ID: " + pat.getId() +
                ", Date: " + request.getDate() +
                ", Time: " + request.getTime());
        Appointment appointment = Appointment.builder()
                .doctor(doc)
                .patient(pat)
                .appointmentDate(appointmentDate)
                .appointmentTime(appointmentTime)
                .status(STATUS.SCHEDULED)
                .reasonForAppointment(request.getReasonForAppointment())
                .build();
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return savedAppointment;
    }

    public void cancelAppointment(Long appointmentId){
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(()->new RuntimeException("Appointment not found"));
        appointmentRepository.delete(appointment);
    }
}
