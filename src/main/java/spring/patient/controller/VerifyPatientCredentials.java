package spring.patient.controller;

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

    private static final Logger logger = LogManager.getLogger("patientLogin");

    @GetMapping("/loginUser")
    public boolean verifyCredentials(@RequestParam("id") String patientId, @RequestParam("password") String patientPassword) {
        boolean userExists = false;
        patient.setPatientId(patientId);
        patient.setPatientPassword(patientPassword);

        try{
            for (PatientLogin p : patientLoginDao.findAll()) {
                if (p.getPatientId().equals(patient.getPatientId())) {
                    logger.info(String.format("PatientID: '%s' and PatientID(DB): '%s ",patient.getPatientId(),
                    p.getPatientId()));
                    patient.setPasswordHash(patient.getPatientPassword(), p.getPasswordSalt());
                    logger.info(String.format("Password hash: '%s'",patient.getPasswordHash()));
                    userExists = patient.getPasswordHash().equals(p.getPasswordHash());
                    logger.info("User Exists: %b", userExists);
                    }
                }
        } catch(Exception e) {
            System.out.println("Failed to verify patient credentials");
            e.printStackTrace();
        }
        //Fix this to work with Log4J - logger.setLevel(LEVEL.trace);
        logger.trace("Trace Message!");
        logger.debug("Debug Message!");
        logger.info("Info Message!");
        logger.warn("Warn Message!");
        logger.error("Error Message!");
        logger.fatal("Fatal message");
        return userExists;
    }
}
