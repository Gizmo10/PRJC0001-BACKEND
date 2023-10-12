package spring.patient.model;

public class PatientLogin {
    private String patientName;
    private String patientSurname;
    private String patientPassword;

    public PatientLogin() {}

    public PatientLogin(String name, String surname, String password) {
        this.patientName = name;
        this.patientSurname = surname;
        this.patientPassword = password;
    }

    public String getPatientName(){
        return patientName; }

    public String getPatientSurname(){
        return patientSurname; }

    public String getPatientPassword(){
        return patientPassword; }

    public void setPatientName(String name){
        this.patientName = name;
    }

    public void setPatientSurname(String surname){
        this.patientSurname = surname;
    }

    public void setPatientPassword(String password){
        this.patientPassword = password;
    }
}
