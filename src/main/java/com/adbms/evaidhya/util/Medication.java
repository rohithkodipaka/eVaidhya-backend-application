package com.adbms.evaidhya.util;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Medication {

    private String medicationName;
    private String dosage;
    private String frequency;
}
