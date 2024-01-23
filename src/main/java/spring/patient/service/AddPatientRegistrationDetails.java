package spring.patient.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import spring.patient.data.PatientRegistrationDao;
import spring.patient.model.PatientRegistration;


public class AddPatientRegistrationDetails {
    @Autowired
    ValidateRegistrationDetails inputValidator;
    @Autowired
    PatientRegistrationDao patientRegistrationDao;
    private static final Logger log = LogManager.getLogger("patientLogin");

    public boolean registerPatient(PatientRegistration registrationDetails) {
        boolean registered = false;

        try{
            registrationDetails.setIdCopy(registrationDetails.getIdF().getBytes());
        } catch(Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        log.info(String.format("Registering user: '%s'",registrationDetails.getId()));
        if(inputValidator.validateRegistrationDetails(registrationDetails)) {
            try {
                patientRegistrationDao.save(registrationDetails);
                registered = true;
            } catch(Exception e) {
                log.error(String.format("Failed registering user: '%s'",registrationDetails.getId()),e.getMessage());
                e.printStackTrace();
            }
            log.info(String.format("Successfully registered user: '%s'",registrationDetails.getId()));
        } else {
            log.warn(String.format("Invalid registration details user: '%s'",registrationDetails.getId()));
        }
        return registered;
        }
}
