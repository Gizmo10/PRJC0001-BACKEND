package spring.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.patient.model.PatientRegistration;
import spring.patient.service.PatientRegistrationService;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("patient")
public class RegisterPatient {
    @Autowired
    PatientRegistrationService patientRegistrationService;

    @PostMapping("/register")
    public boolean registerPatient(@ModelAttribute PatientRegistration regDetails) {
       return patientRegistrationService.registerPatient(regDetails);
    }

    @GetMapping("/registeredById")
    public Optional<PatientRegistration> getRegisteredPatientById(@RequestParam("idNumber") String id) {
        return patientRegistrationService.findById(id);
    }
}


