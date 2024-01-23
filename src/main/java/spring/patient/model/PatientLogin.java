package spring.patient.model;
import jakarta.persistence.*;
import jakarta.xml.bind.DatatypeConverter;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity
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

    private static final Logger log = LogManager.getLogger("patientLogin");

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
