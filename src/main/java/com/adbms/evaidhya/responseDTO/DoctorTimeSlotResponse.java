package com.adbms.evaidhya.responseDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorTimeSlotResponse {

    List<LocalTime> availableSlots;

    List<LocalTime> bookedSlots;
}
