package spring.patient.model;
import jakarta.persistence.*;
import jakarta.xml.bind.DatatypeConverter;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.Random;

@Component
@Entity
@Table(name="patient_login_credentials")
public class PatientLogin {
    @Id
    @Column(name="id")
    @Getter@Setter
    private String id;
    @Transient
    @Getter@Setter
    private String password;
    @Column(name="salt")
    @Getter
    private String passwordSalt;
    @Column(name="hash")
    @Getter
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

    public void setPasswordSalt(String id) {

        String alphaNumeric = "ABCDEFGHIJKLNMOPQRSTUVWXYZabcdefghijklmn" +
                "opqrstuvwxyz0123456789";
        int alphanumericLength = alphaNumeric.length();
        StringBuilder sb = new StringBuilder();
        int saltSize = 20;
        Random rand = new Random();

        log.info(String.format("Generating salt for user: '%s'",id));
        try {
            for(int i = 0; i < saltSize; i++) {
                int index = rand.nextInt(alphanumericLength);
                char randomCharacter = alphaNumeric.charAt(index);
                sb.append(randomCharacter);
            }
        } catch(Exception e) {
            log.error(String.format("Failed to generate salt for user: '%s', id"),e.getMessage());
            e.printStackTrace();
        }
        log.info(String.format("Successfully generated salt for user: '%s'",id));
        this.passwordSalt = sb.toString();
    }

    public void setPasswordHash(String id,String password, String salt) {
          String sha1 = null;
          String input = password + salt;

          log.info(String.format("Generating hash for user: '%s'",id));
          try{
              MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
              msdDigest.update(input.getBytes("UTF-8"), 0, input.length());
              sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
          } catch(Exception e) {
              log.error(String.format("Failed to generate hash for user: '%s",id), e.getMessage());
              e.printStackTrace();
          }
          log.info(String.format("Successfully generated hash for user: '%s",id));
          this.passwordHash = sha1;
    }

    @Override
    public String toString() {
        return String.format("[id='%s', salt='%s', hash='%s']",
                id,passwordSalt,passwordHash);
    }
}
