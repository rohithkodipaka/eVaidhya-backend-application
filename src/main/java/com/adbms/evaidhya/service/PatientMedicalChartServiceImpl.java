package com.adbms.evaidhya.service;


import com.adbms.evaidhya.entity.PatientMedicalChart;
import com.adbms.evaidhya.repository.PatientMedicalChartRepository;
import com.adbms.evaidhya.requestDTO.PatientMedicalChartRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PatientMedicalChartServiceImpl implements PatientMedicalChartService{

    @Autowired
    private PatientMedicalChartRepository patientMedicalChartRepository;

    public PatientMedicalChart updatePatientMedicalChart(PatientMedicalChartRequestDTO request) throws Exception{
            PatientMedicalChart patientMedicalChart = patientMedicalChartRepository.findByPatientId(request.getPatientId());
            if(patientMedicalChart == null){
                throw new Exception("No PatientMedicalChart found for given Patient Id");
            }
            patientMedicalChart.setAllergies((patientMedicalChart.getAllergies()==null?"": patientMedicalChart.getAllergies())+"\n"+ LocalDate.now()+" : " +request.getAllergies());
            patientMedicalChart.setDiagnosis((patientMedicalChart.getDiagnosis()==null?"": patientMedicalChart.getDiagnosis())+"\n"+ LocalDate.now()+" : " +request.getDiagnosis());
            patientMedicalChart.setTreatment((patientMedicalChart.getTreatment()==null?"": patientMedicalChart.getTreatment())+"\n"+ LocalDate.now()+" : " +request.getTreatment());
            return patientMedicalChartRepository.save(patientMedicalChart);
    }

    public PatientMedicalChart getMedicalChart(Long patientId) throws Exception{
        PatientMedicalChart patientMedicalChart = patientMedicalChartRepository.findByPatientId(patientId);
        if(patientMedicalChart == null){
            throw new Exception("No PatientMedicalChart found for given Patient Id");
        }
        return patientMedicalChart;
    }

}
