package com.adbms.evaidhya.util;


import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TimeSlotDivisionUtil {

    public static List<LocalTime> generateTimeSlots(LocalTime startTime, LocalTime endTime, int slotDuration){
        List<LocalTime> timeSlots = new ArrayList<>();

        LocalTime slotStart = startTime;

        while(slotStart.isBefore(endTime)){
            timeSlots.add(slotStart);
            slotStart = slotStart.plusMinutes(slotDuration);
        }
        return timeSlots;
    }
}
