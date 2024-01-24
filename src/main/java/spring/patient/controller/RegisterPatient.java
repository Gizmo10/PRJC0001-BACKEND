package spring.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.patient.model.PatientRegistration;
import spring.patient.service.AddPatientRegistrationDetails;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("patient")
public class RegisterPatient {
    @Autowired
    AddPatientRegistrationDetails addPatientRegistrationDetails;

    @PostMapping("/register")
    public boolean registerPatient(@ModelAttribute PatientRegistration regDetails) {
       return addPatientRegistrationDetails.registerPatient(regDetails);
    }
}


