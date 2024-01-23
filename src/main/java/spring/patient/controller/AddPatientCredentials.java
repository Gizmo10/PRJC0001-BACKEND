package spring.patient.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.patient.data.PatientLoginDao;
import spring.patient.model.PatientLogin;
import spring.patient.service.AuthenticatePatient;
import spring.patient.service.HashPassword;
import spring.patient.service.ValidateRegistrationDetails;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("patient")
public class AddPatientCredentials {
    @Autowired
    private AuthenticatePatient authenticatePatient;
    private static final Logger log = LogManager.getLogger("patientLogin");

    @PostMapping("/addPatientCredentials")
    public void addCredentials(@RequestParam("id") String id,@RequestParam("password") String password) {
        authenticatePatient.addCredentials(id,password);
    }
}
