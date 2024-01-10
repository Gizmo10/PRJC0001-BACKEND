package spring.patient.model;

import org.springframework.beans.factory.annotation.Autowired;

public class PatientRegistration {
    private String name;
    private String surname;
    private String id;
    private String birthdate;
    private String email;
    private String cellphoneNumber;
    private String streetName;
    private String city;
    private String suburb;
    private String province;
    private String postalCode;

    public PatientRegistration(String name, String surname, String id, String birthdate,String cellphoneNumber, String email,
                               String streetName, String suburb, String city, String postalCode, String province) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.birthdate = birthdate;
        this.cellphoneNumber = cellphoneNumber;
        this.email = email;
        this.streetName = streetName;
        this.city = city;
        this.suburb = suburb;
        this.province = province;
        this.postalCode = postalCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getId() {
        return id;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getCity() {
        return city;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }
    //selfie and ID as member variables
}
