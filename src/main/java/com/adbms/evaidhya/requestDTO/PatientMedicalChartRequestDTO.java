package com.adbms.evaidhya.requestDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientMedicalChartRequestDTO {

    private Long patientId;

    private String diagnosis;

    private String treatment;

    private String allergies;
}
