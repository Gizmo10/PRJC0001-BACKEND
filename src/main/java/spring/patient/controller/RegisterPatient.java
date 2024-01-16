package spring.patient.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger log = LogManager.getLogger("patientLogin");

    @PostMapping("/registerPatient")
    public void registerPatient(@RequestBody PatientRegistration regDetails) {
        log.info(String.format("Registering user: '%s'",regDetails.getId()));
        try {
            patientRegistrationDao.save(regDetails);
        } catch(Exception e) {
            log.error(String.format("Failed registering user: '%s'",regDetails.getId()),e.getMessage());
            e.printStackTrace();
        }
        log.info(String.format("Successfully registered user: '%s'",regDetails.getId()));
    }
}


