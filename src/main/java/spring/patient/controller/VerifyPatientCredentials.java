package spring.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.patient.service.PatientLoginService;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("patient")
public class VerifyPatientCredentials {
    @Autowired
    private PatientLoginService patientLoginService;

    @GetMapping("/authenticate")
    public boolean verifyCredentials(@RequestParam("id") String patientId, @RequestParam("password") String patientPassword) {
       return patientLoginService.authenticate(patientId,patientPassword);
    }
}
