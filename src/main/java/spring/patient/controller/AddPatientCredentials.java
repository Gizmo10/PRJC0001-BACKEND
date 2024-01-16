package spring.patient.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger log = LogManager.getLogger("patientLogin");

    @PostMapping("/addPatientCredentials")
    public void addCredentials(@RequestParam("id") String id,@RequestParam("password") String password) {
        patient.setId(id);
        patient.setPassword(password);
        patient.setPasswordSalt(id);
        patient.setPasswordHash(patient.getId(),patient.getPassword(),patient.getPasswordSalt());

        log.info(String.format("Adding credentials for user: '%s'",patient.getId()));
        try {
            patientLoginDao.save(patient);
        } catch(Exception e) {
            log.error(String.format("Failed to add credentials for user: '%s",patient.getId()),e.getMessage());
            e.printStackTrace();
        }
        log.info(String.format("Successfully added credentials for user: '%s'",patient.getId()));
    }
}
