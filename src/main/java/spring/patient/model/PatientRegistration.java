package spring.patient.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="registered_patients")
@Data
@NoArgsConstructor
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
    @Transient
    private String password;
    @Transient
    private String rePassword;
    @Column(name="email")
    private String email;
    @Column(name="cellphone_number")
    private String cellphone;
    @Column(name="street")
    private String street;
    @Column(name="city")
    private String city;
    @Column(name="suburb")
    private String suburb;
    @Column(name="province")
    private String province;
    @Column(name="postal_code")
    private String code;
    @Column(name="id_copy")
    @Lob
    private byte[] idCopy;
    @Transient
    private MultipartFile idF;
    @Column(name="selfie")
    @Lob
    private byte[] selfie;
    @Transient
    private MultipartFile selfieF;

    public PatientRegistration(String name, String surname, String id, String birthdate,String cellphone,
                               String email, String password, String rePassword,String street, String suburb,
                               String city, String code, String province, MultipartFile idF, MultipartFile selfieF) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.birthdate = birthdate;
        this.cellphone = cellphone;
        this.email = email;
        this.password = password;
        this.rePassword = rePassword;
        this.street = street;
        this.city = city;
        this.suburb = suburb;
        this.province = province;
        this.code = code;
        this.idF = idF;
        this.selfieF = selfieF;
        }
}
