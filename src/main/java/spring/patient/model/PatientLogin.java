package spring.patient.model;

import java.util.Random;

public class PatientLogin {
    private String patientName;
    private String patientSurname;
    private String patientPassword;
    private String passwordSalt;
    private String passwordHash;

    public PatientLogin() {}

    public PatientLogin(String name, String surname, String password) {
        this.patientName = name;
        this.patientSurname = surname;
        this.patientPassword = password;
    }

    public String getPatientName() {
        return patientName; }

    public String getPatientSurname() {
        return patientSurname; }

    public String getPatientPassword() {
        return patientPassword; }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPatientName(String name) {
        this.patientName = name;
    }

    public void setPatientSurname(String surname) {
        this.patientSurname = surname;
    }

    public void setPatientPassword(String password) {
        this.patientPassword = password;
    }

    public String createPasswordSalt() {

        String alphaNumeric = "ABCDEFGHIJKLNMOPQRSTUVWXYZabcdefghijklmn" +
                "opqrstuvwxyz0123456789";
        int alphanumericLength = alphaNumeric.length();
        StringBuilder sb = new StringBuilder();
        int saltSize = 20;
        Random rand = new Random();

        for(int i = 0; i < saltSize; i++) {
            int index = rand.nextInt(alphanumericLength);
            char randomCharacter = alphaNumeric.charAt(index);
            sb.append(randomCharacter);
        }
        return sb.toString();
    }

    public void setPasswordSalt() {
        passwordSalt = createPasswordSalt();
    }

    public void setPasswordHash(String password, String salt) {

    }
}
