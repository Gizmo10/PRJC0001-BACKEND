package spring.patient.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="registered_patients")
public class PatientRegistration {
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Id
    @Column(name="id")
    private String id;
    @Column(name="birthdate")
    private String birthdate;
    @Column(name="email")
    private String email;
    @Column(name="cellphone_number")
    private String cellphoneNumber;
    @Column(name="street")
    private String streetName;
    @Column(name="city")
    private String city;
    @Column(name="suburb")
    private String suburb;
    @Column(name="province")
    private String province;
    @Column(name="postal_code")
    private String postalCode;

    public PatientRegistration(){};

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
