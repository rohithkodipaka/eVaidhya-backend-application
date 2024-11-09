package com.adbms.evaidhya.repository;


import com.adbms.evaidhya.entity.PatientMedicalChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientMedicalChartRepository extends JpaRepository<PatientMedicalChart, Long> {
    PatientMedicalChart findByPatientId(Long patientId);
}
