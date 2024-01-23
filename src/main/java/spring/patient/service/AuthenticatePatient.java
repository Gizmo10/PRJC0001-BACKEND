package spring.patient.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.patient.data.PatientLoginDao;
import spring.patient.model.PatientLogin;
import spring.patient.service.ValidateRegistrationDetails;

@Service
public class AuthenticatePatient {
    @Autowired
    private ValidateRegistrationDetails inputValidator;
    @Autowired
    private PatientLoginDao patientLoginDao;
    @Autowired
    private HashPassword passwordHasher;
    @Autowired
    private PatientLogin patient;
    @Autowired
    private static final Logger log = LogManager.getLogger("patientLogin");
    public boolean authenticate(String patientId, String patientPassword) {
        boolean userExists = false;
        boolean validInput = inputValidator.validateRegistrationFormInput(patientId,inputValidator.getIdPattern()) &&
                inputValidator.validateRegistrationFormInput(patientPassword,inputValidator.getPasswordPattern());

        log.info(String.format("Authenticating user: '%s'",patientId));
        if (validInput) {
            try{
                for (PatientLogin p : patientLoginDao.findAll()) {
                    if (p.getId().equals(patientId)) {
                        String hash = passwordHasher.createPasswordHash(patientId,patientPassword,p.getPasswordSalt());
                        userExists = hash.equals(p.getPasswordHash());
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

    public void addCredentials(String patientId, String patientPassword) {
        boolean validInput = inputValidator.validateRegistrationFormInput(patientId, inputValidator.getIdPattern()) &&
                inputValidator.validateRegistrationFormInput(patientPassword,inputValidator.getPasswordPattern());

        log.info(String.format("Adding credentials for user: '%s'",patientId));
        if(validInput) {
            patient.setId(patientId);
            patient.setPasswordSalt(passwordHasher.createPasswordSalt(patientId));
            patient.setPasswordHash(passwordHasher.createPasswordHash(patientId,patientPassword,patient.getPasswordSalt()));

            try {
                patientLoginDao.save(patient);
            } catch(Exception e) {
                log.error(String.format("Failed to add credentials for user: '%s",patient.getId()),e.getMessage());
                e.printStackTrace();
            }
            log.info(String.format("Successfully added credentials for user: '%s'",patient.getId()));
        } else {
            log.warn(String.format("Invalid credential user: '%s'",patient.getId()));
        }
    }
}
