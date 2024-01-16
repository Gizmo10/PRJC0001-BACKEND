package spring.patient.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="registered_patients")
public class PatientRegistration {
    @Column(name="name")
    @Getter@Setter
    private String name;
    @Column(name="surname")
    @Getter@Setter
    private String surname;
    @Id
    @Column(name="id")
    @Getter@Setter
    private String id;
    @Column(name="birthdate")
    @Getter@Setter
    private String birthdate;
    @Transient
    @Getter@Setter
    private String password;
    @Transient
    @Getter@Setter
    private String rePassword;
    @Column(name="email")
    @Getter@Setter
    private String email;
    @Column(name="cellphone_number")
    @Getter@Setter
    private String cellphoneNumber;
    @Column(name="street")
    @Getter@Setter
    private String streetName;
    @Column(name="city")
    @Getter@Setter
    private String city;
    @Column(name="suburb")
    @Getter@Setter
    private String suburb;
    @Column(name="province")
    @Getter@Setter
    private String province;
    @Column(name="postal_code")
    @Getter@Setter
    private String postalCode;

    public PatientRegistration(){};

    public PatientRegistration(String name, String surname, String id, String birthdate,String cellphoneNumber,
                               String email, String password, String rePassword,String streetName, String suburb,
                               String city, String postalCode, String province) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.birthdate = birthdate;
        this.cellphoneNumber = cellphoneNumber;
        this.email = email;
        this.password = password;
        this.rePassword = rePassword;
        this.streetName = streetName;
        this.city = city;
        this.suburb = suburb;
        this.province = province;
        this.postalCode = postalCode;
    }
    //selfie and ID as member variables
}
