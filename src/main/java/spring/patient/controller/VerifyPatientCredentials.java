package spring.patient.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.patient.data.PatientLoginDao;
import spring.patient.model.PatientLogin;
import spring.patient.model.ValidateInput;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("patient")
public class VerifyPatientCredentials {

    @Autowired
    private PatientLogin patient;
    @Autowired
    private PatientLoginDao patientLoginDao;
    @Autowired
    private ValidateInput inputValidator;
    private static final Logger log = LogManager.getLogger("patientLogin");

    @GetMapping("/loginUser")
    public boolean verifyCredentials(@RequestParam("id") String patientId, @RequestParam("password") String patientPassword) {
        boolean userExists = false;
        boolean validInput = inputValidator.validateRegistrationFormInput(patientId,inputValidator.getIdPattern()) &&
                inputValidator.validateRegistrationFormInput(patientPassword,inputValidator.getPasswordPattern());

        log.info(String.format("Authenticating user: '%s'",patientId));
        if (validInput) {
            patient.setId(patientId);
            patient.setPassword(patientPassword);
            try{
                for (PatientLogin p : patientLoginDao.findAll()) {
                    if (p.getId().equals(patient.getId())) {
                        patient.setPasswordHash(patient.getId(),patient.getPassword(), p.getPasswordSalt());
                        userExists = patient.getPasswordHash().equals(p.getPasswordHash());
                    }
                }
            } catch(Exception e) {
                log.error(String.format("Failed to authenticate user: '%s'",patientId),e.getMessage());
                e.printStackTrace();
            }

            if(userExists){
                log.info(String.format("Successfully authenticated user: '%s'",patientId));
            } else{
                log.warn(String.format("Failed to authenticate user: '%s'",patientId));
            }
        } else {
            log.warn(String.format("Invalid credentials user: '%s'",patientId));
        }
        return userExists;
    }
}
