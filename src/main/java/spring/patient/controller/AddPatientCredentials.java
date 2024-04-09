package spring.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.patient.service.PatientLoginService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("patient")
public class AddPatientCredentials {
    @Autowired
    private PatientLoginService patientLoginService;

    @PostMapping("/addCredentials")
    public void addPatientCredentials(@RequestParam("id") String id,@RequestParam("password") String password) {
        patientLoginService.addCredentials(id,password);
    }
}
