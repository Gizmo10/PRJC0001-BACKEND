package spring.patient.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.patient.data.PatientRegistrationDao;
import spring.patient.model.PatientRegistration;
import spring.patient.model.ValidateInput;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("patient")
public class RegisterPatient {
    @Autowired
    private PatientRegistrationDao patientRegistrationDao;
    @Autowired
    ValidateInput inputValidator;
    private static final Logger log = LogManager.getLogger("patientLogin");

    @PostMapping("/registerPatient")
    public boolean registerPatient(@RequestBody PatientRegistration regDetails) {
        boolean registered = false;

        log.info(String.format("Registering user: '%s'",regDetails.getId()));
        if(inputValidator.validateRegistrationDetails(regDetails)) {
            try {
                patientRegistrationDao.save(regDetails);
                registered = true;
            } catch(Exception e) {
                log.error(String.format("Failed registering user: '%s'",regDetails.getId()),e.getMessage());
                e.printStackTrace();
            }

            log.info(String.format("Successfully registered user: '%s'",regDetails.getId()));

        } else {
            log.warn(String.format("Invalid registration details user: '%s",regDetails.getId()));
        }
        return registered;
    }
}


