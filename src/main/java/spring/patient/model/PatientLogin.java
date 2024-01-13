package spring.patient.model;
import jakarta.persistence.*;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.Random;

@Component
@Entity
@Table(name="patient_login_credentials")
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


    public String getPatientId() {
        return this.id; }

    public String getPatientPassword() {
        return this.password; }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPatientId(String id) {
        this.id = id;
    }

    public void setPatientPassword(String password) {
        this.password = password;
    }

    public void setPasswordSalt() {

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
        this.passwordSalt = sb.toString();
    }

    public void setPasswordHash(String password, String salt) {
          String sha1 = null;
          String input = password + salt;

          try{
              MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
              msdDigest.update(input.getBytes("UTF-8"), 0, input.length());
              sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
          } catch(Exception e) {
              e.printStackTrace();
          }
          this.passwordHash = sha1;
    }

    @Override
    public String toString() {
        return String.format("[id='%s', salt='%s', hash='%s']",
                id,passwordSalt,passwordHash);
    }
}
