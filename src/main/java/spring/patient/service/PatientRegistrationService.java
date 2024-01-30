package spring.patient.service;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import spring.patient.data.PatientRegistrationDao;
import spring.patient.model.PatientRegistration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatientRegistrationService {
    @Getter
    private String namePattern;
    @Getter
    private String surnamePattern;
    @Getter
    private String idPattern;
    @Getter
    private String passwordPattern;
    @Getter
    private String birthdatePattern1900;
    @Getter
    private String birthdatePattern2000;
    @Getter
    private String cellphoneNumberWithCountryCodePattern;
    @Getter
    private String cellphoneNumberPattern;
    @Getter
    private String emailPattern;
    @Getter
    private String streetNamePattern;
    @Getter
    private String postalCodePattern;
    @Getter
    private String genericPattern;
    @Autowired
    PatientRegistrationDao patientRegistrationDao;
    private static final Logger log = LogManager.getLogger("patientLogin");

    public PatientRegistrationService() {
        this.namePattern = "^[A-Z]([a-z]{2,30})$";
        this.surnamePattern = "^[A-Z][a-z]{0,20}(([\s | -][A-Z][a-z]{2,20}){0,2})$";
        this.idPattern = "^[0-9]{13}$";
        this.passwordPattern = "^[A-Z | a-z | 0-9]{6,9}$";
        this.birthdatePattern1900 = "^[1][8 | 9][0-9]{2}";
        this.birthdatePattern2000 = "^[2][0][0-9]{2}";
        this.cellphoneNumberWithCountryCodePattern = "^[+][0-9]{11}$";
        this.cellphoneNumberPattern =  "^[0][0-9]{9}$";
        this.emailPattern = "^[A-Z | a-z][A-Z | a-z |0-9]{0,20}([.][0-9 | A-Z | a-z]{1,30})?[@][a-z]{2,30}[.](com)$";
        this.genericPattern = "^[A-Z][a-z]{1,20}((\s[A-Z][a-z]{2,20}){0,3})$";
        this.streetNamePattern = "^[0-9]{1,6}[A-Z | a-z]{0,2}((\s[A-Z][a-z]{2,20}){1,4})$";
        this.postalCodePattern = "^[0-9]{4}$";
    }
    public boolean validateRegistrationFormInput(String input, String regEx) {
        boolean valid = false;
        try{
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(input);
            valid = matcher.find();
        } catch(Exception e) {
            log.error(e.getMessage());
        }
        return valid;
    }

    public boolean idMatchesBirthdate(String id, String birthdate) {
        boolean isMatch = false;
        try {
            String idYear = id.substring(0,2);
            String idMonth = id.substring(2,4);
            String idDay = id.substring(4,6);

            String birthdateYear = birthdate.substring(2,4);
            String birthdateMonth = birthdate.substring(5,7);
            String birthdateDay = birthdate.substring(8);
            isMatch = idYear.equals(birthdateYear) && idMonth.equals(birthdateMonth)
                    && idDay.equals(birthdateDay);
        } catch(Exception e) {
            log.error(e.getMessage());
        }
        return isMatch;
    }

    public boolean passwordsMatch(String password, String rePassword) {
        return password.equals(rePassword);
    }

    public boolean validateRegistrationDetails(PatientRegistration patientDetails) {
        boolean isValidName = this.validateRegistrationFormInput(patientDetails.getName(),this.getNamePattern());
        boolean isValidSurname = this.validateRegistrationFormInput(patientDetails.getSurname(),this.getSurnamePattern());
        boolean isValidId = this.validateRegistrationFormInput(patientDetails.getId(),this.getIdPattern());
        boolean isValidBirthdate = this.validateRegistrationFormInput(patientDetails.getBirthdate(),this.getBirthdatePattern1900())
                || this.validateRegistrationFormInput(patientDetails.getBirthdate(),this.getBirthdatePattern2000());
        boolean idMatchesBirthdate = this.idMatchesBirthdate(patientDetails.getId(),patientDetails.getBirthdate());
        boolean isValidCellphone = this.validateRegistrationFormInput(patientDetails.getCellphone(),this.getCellphoneNumberWithCountryCodePattern())
                || this.validateRegistrationFormInput(patientDetails.getCellphone(),this.getCellphoneNumberPattern());
        boolean isValidEmail = this.validateRegistrationFormInput(patientDetails.getEmail(),this.getEmailPattern());
        boolean isValidPassword = this.validateRegistrationFormInput(patientDetails.getPassword(), this.getPasswordPattern());
        boolean isValidRepassword = this.validateRegistrationFormInput(patientDetails.getRePassword(),this.getPasswordPattern());
        boolean passwordsMatch = this.passwordsMatch(patientDetails.getPassword(),patientDetails.getRePassword());
        boolean isValidStreet = this.validateRegistrationFormInput(patientDetails.getStreet(),this.getStreetNamePattern());
        boolean isValidSuburb = this.validateRegistrationFormInput(patientDetails.getSuburb(),this.getGenericPattern());
        boolean isValidCity = this.validateRegistrationFormInput(patientDetails.getCity(),this.getGenericPattern());
        boolean isValidProvince = this.validateRegistrationFormInput(patientDetails.getProvince(),this.getGenericPattern());
        boolean isValidPostalCode = this.validateRegistrationFormInput(patientDetails.getCode(),this.getPostalCodePattern());
        boolean isValidIdF = patientDetails.getIdF().getSize() > 0;
        boolean isValidSelfieF = patientDetails.getSelfieF().getSize() > 0;

        return isValidName && isValidSurname && isValidId && isValidBirthdate && idMatchesBirthdate && isValidCellphone
                && isValidEmail && isValidPassword && isValidRepassword && passwordsMatch && isValidStreet && isValidSuburb
                && isValidCity && isValidProvince && isValidPostalCode && isValidIdF && isValidSelfieF;
    }

    public boolean registerPatient(PatientRegistration registrationDetails) {
        boolean registered = false;

        try{
            registrationDetails.setIdCopy(registrationDetails.getIdF().getBytes());
            registrationDetails.setSelfie(registrationDetails.getSelfieF().getBytes());
        } catch(Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        log.info(String.format("Registering user: '%s'",registrationDetails.getId()));
        if(this.validateRegistrationDetails(registrationDetails)) {
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
