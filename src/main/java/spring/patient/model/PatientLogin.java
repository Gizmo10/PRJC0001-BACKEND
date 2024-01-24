package spring.patient.model;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="patient_login_credentials")
@Data
public class PatientLogin {
    @Id
    @Column(name="id")
    private String id;
    @Transient
    private String password;
    @Column(name="salt")
    private String passwordSalt;
    @Column(name="hash")
    private String passwordHash;

    public PatientLogin() {}

    public PatientLogin(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public PatientLogin(String id, String salt, String hash){
        this.id = id;
        this.passwordSalt = salt;
        this.passwordHash = hash;
    }
}
