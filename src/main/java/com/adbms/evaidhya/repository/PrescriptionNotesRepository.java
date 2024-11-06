package com.adbms.evaidhya.repository;


import com.adbms.evaidhya.entity.PrescriptionNotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionNotesRepository extends JpaRepository<PrescriptionNotes, Long> {

    List<PrescriptionNotes> findByPatientIdAndAppointmentId(Long patientId, Long appointmentId);

    List<PrescriptionNotes> findByDoctorIdAndAppointmentIdAndPatientId(Long doctorId,Long appointmentId, Long patientId);
}
