package com.adbms.evaidhya.service;


import com.adbms.evaidhya.entity.Appointment;
import com.adbms.evaidhya.entity.Doctor;
import com.adbms.evaidhya.entity.Patient;
import com.adbms.evaidhya.entity.PrescriptionNotes;
import com.adbms.evaidhya.repository.AppointmentRepository;
import com.adbms.evaidhya.repository.DoctorRepository;
import com.adbms.evaidhya.repository.PatientRepository;
import com.adbms.evaidhya.repository.PrescriptionNotesRepository;
import com.adbms.evaidhya.requestDTO.PrescriptionRequestDTO;
import com.adbms.evaidhya.util.Medication;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionNotesServiceImpl  implements PrescriptionNotesService{

    @Autowired
    private PrescriptionNotesRepository prescriptionNotesRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<PrescriptionNotes> updatePrescription(PrescriptionRequestDTO request) throws Exception{
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(()-> new Exception("Patient Not found with given id"));
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(()->new Exception("Doctor not found with given id"));
        Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
                .orElseThrow(()->new Exception("Appointment Not Found with given Id"));
        List<PrescriptionNotes> prescriptionNotes = new ArrayList<>();
        for(Medication medication:request.getMedication()){
                PrescriptionNotes prescription = PrescriptionNotes.builder()
                        .appointment(appointment)
                        .doctor(doctor)
                        .patient(patient)
                        .medicationName(medication.getMedicationName())
                        .dosage(medication.getDosage())
                        .frequency(medication.getFrequency())
                        .notes(request.getNotes())
                        .prescriptionDate(LocalDateTime.now())
                        .build();
                prescriptionNotes.add(prescriptionNotesRepository.save(prescription));
        }
        return prescriptionNotes;
    }

    public List<PrescriptionNotes> viewPatientPrescription(Long patientId, Long appointmentId){
        return prescriptionNotesRepository.findByPatientIdAndAppointmentId(patientId,appointmentId);
    }

}
