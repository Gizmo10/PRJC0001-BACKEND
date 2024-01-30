package spring.patient.service;

import jakarta.xml.bind.DatatypeConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import spring.patient.data.PatientLoginDao;
import spring.patient.model.PatientLogin;

import java.security.MessageDigest;
import java.util.Random;


public class PatientLoginService {
    @Autowired
    private PatientRegistrationService patientRegistrationService;
    @Autowired
    private PatientLoginDao patientLoginDao;
    @Autowired
    private PatientLogin patient;
    @Autowired
    private static final Logger log = LogManager.getLogger("patientLogin");

    public boolean authenticate(String patientId, String patientPassword) {
        boolean userExists = false;
        boolean validInput = patientRegistrationService.validateRegistrationFormInput(patientId,patientRegistrationService.getIdPattern()) &&
                patientRegistrationService.validateRegistrationFormInput(patientPassword,patientRegistrationService.getPasswordPattern());

        log.info(String.format("Authenticating user: '%s'",patientId));
        if (validInput) {
            try{
                for (PatientLogin p : patientLoginDao.findAll()) {
                    if (p.getId().equals(patientId)) {
                        String hash = this.createPasswordHash(patientId,patientPassword,p.getPasswordSalt());
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
        boolean validInput = patientRegistrationService.validateRegistrationFormInput(patientId, patientRegistrationService.getIdPattern()) &&
                patientRegistrationService.validateRegistrationFormInput(patientPassword,patientRegistrationService.getPasswordPattern());

        log.info(String.format("Adding credentials for user: '%s'",patientId));
        if(validInput) {
            patient.setId(patientId);
            patient.setPasswordSalt(this.createPasswordSalt(patientId));
            patient.setPasswordHash(this.createPasswordHash(patientId,patientPassword,patient.getPasswordSalt()));

            try {
                patientLoginDao.save(patient);
            } catch(Exception e) {
                log.error(String.format("Failed to add credentials for user: '%s'",patient.getId()),e.getMessage());
                e.printStackTrace();
            }
            log.info(String.format("Successfully added credentials for user: '%s'",patient.getId()));
        } else {
            log.warn(String.format("Invalid credential user: '%s'",patient.getId()));
        }
    }

    public String createPasswordSalt(String id) {

        String alphaNumeric = "ABCDEFGHIJKLNMOPQRSTUVWXYZabcdefghijklmn" +
                "opqrstuvwxyz0123456789";
        int alphanumericLength = alphaNumeric.length();
        StringBuilder sb = new StringBuilder();
        int saltSize = 20;
        Random rand = new Random();

        log.info(String.format("Generating salt for user: '%s'",id));
        try {
            for(int i = 0; i < saltSize; i++) {
                int index = rand.nextInt(alphanumericLength);
                char randomCharacter = alphaNumeric.charAt(index);
                sb.append(randomCharacter);
            }
        } catch(Exception e) {
            log.error(String.format("Failed to generate salt for user: '%s', id"),e.getMessage());
            e.printStackTrace();
        }
        log.info(String.format("Successfully generated salt for user: '%s'",id));
        return sb.toString();
    }

    public String createPasswordHash(String id,String password, String salt) {
        String sha1 = null;
        String input = password + salt;

        log.info(String.format("Generating hash for user: '%s'",id));
        try{
            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
            msdDigest.update(input.getBytes("UTF-8"), 0, input.length());
            sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
        } catch(Exception e) {
            log.error(String.format("Failed to generate hash for user: '%s",id), e.getMessage());
            e.printStackTrace();
        }
        log.info(String.format("Successfully generated hash for user: '%s",id));
        return sha1;
    }
}
