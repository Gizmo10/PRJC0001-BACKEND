package spring.patient.service;

import jakarta.xml.bind.DatatypeConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Random;

@Service
public class HashPassword {
    private static final Logger log = LogManager.getLogger("patientLogin");

    public String createPasswordSalt(String id) {

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
        return sb.toString();
    }

    public String createPasswordHash(String id,String password, String salt) {
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
        return sha1;
    }
}
