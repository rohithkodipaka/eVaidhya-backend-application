package com.adbms.evaidhya.requestDTO;

import com.adbms.evaidhya.util.Medication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrescriptionRequestDTO {

    private Long patientId;

    private Long doctorId;

    private Long appointmentId;

    List<Medication> medication;

    private String notes;
}
