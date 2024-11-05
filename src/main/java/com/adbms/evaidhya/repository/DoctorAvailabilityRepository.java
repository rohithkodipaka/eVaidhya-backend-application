package com.adbms.evaidhya.repository;

import com.adbms.evaidhya.entity.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.Optional;


@Repository
public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability,Long> {

    Optional<DoctorAvailability> findByDoctorIdAndDayOfWeek(Long doctorId, DayOfWeek dayOfWeek);
}
