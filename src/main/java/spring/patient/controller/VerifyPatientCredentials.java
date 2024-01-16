package spring.patient.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.patient.data.PatientLoginDao;
import spring.patient.model.PatientLogin;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("patient")
public class VerifyPatientCredentials {

    @Autowired
    private PatientLogin patient;
    @Autowired
    private PatientLoginDao patientLoginDao;

    private static final Logger log = LogManager.getLogger("patientLogin");

    @GetMapping("/loginUser")
    public boolean verifyCredentials(@RequestParam("id") String patientId, @RequestParam("password") String patientPassword) {
        boolean userExists = false;
        patient.setId(patientId);
        patient.setPassword(patientPassword);

        log.info(String.format("Authenticating user: '%s'",patientId));
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
        return userExists;
    }
}
