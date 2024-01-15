package spring.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.patient.data.PatientLoginDao;
import spring.patient.model.PatientLogin;

//@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("patient")
public class AddPatientCredentials {
    @Autowired
    private PatientLoginDao patientLoginDao;
    @Autowired
    PatientLogin patient;

    @PostMapping("/addPatientCredentials")
    public void addCredentials(@RequestParam("id") String id,@RequestParam("password") String password) {
        patient.setPatientId(id);
        patient.setPatientPassword(password);
        patient.setPasswordSalt();
        patient.setPasswordHash(patient.getPatientPassword(),patient.getPasswordSalt());
        System.out.println(patient.toString());

        try {
            patientLoginDao.save(patient);
        } catch(Exception e) {
            System.out.println("Failed to add patient credentials");
            e.printStackTrace();
        }
    }
}
