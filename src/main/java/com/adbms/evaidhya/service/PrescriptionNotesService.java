package com.adbms.evaidhya.service;

import com.adbms.evaidhya.entity.PrescriptionNotes;
import com.adbms.evaidhya.requestDTO.PrescriptionRequestDTO;

import java.util.List;

public interface PrescriptionNotesService {
    List<PrescriptionNotes> updatePrescription(PrescriptionRequestDTO request) throws Exception;

    List<PrescriptionNotes> viewPatientPrescription(Long patientId, Long appointmentId);
}
