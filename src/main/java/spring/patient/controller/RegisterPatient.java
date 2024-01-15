package spring.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.patient.data.PatientRegistrationDao;
import spring.patient.model.PatientRegistration;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("patient")
public class RegisterPatient {
    @Autowired
    private PatientRegistrationDao patientRegistrationDao;

    @PostMapping("/registerPatient")
    public void registerPatient(@RequestBody PatientRegistration regDetails) {

        try {
            patientRegistrationDao.save(regDetails);
        } catch(Exception e) {
            System.out.println("Patient failed registration");
            e.printStackTrace();
        }
    }
}


