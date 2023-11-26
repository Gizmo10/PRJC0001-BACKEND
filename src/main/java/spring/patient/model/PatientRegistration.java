package spring.patient.model;

import org.springframework.beans.factory.annotation.Autowired;

public class PatientRegistration {
    private String name;
    private String surname;
    private String idNumber;
    private String email;
    private String cellphoneNumber;
    private String password;
    @Autowired
    private ResidentialAddress residentialAddress;
    //Must figure how to store Selfie and ID as member variables

}
