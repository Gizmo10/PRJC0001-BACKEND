package spring.patient.service;

import org.junit.jupiter.api.Test;
import spring.patient.model.PatientLogin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class HashPasswordTest {
    private HashPassword patient = new HashPassword();

    @Test
    public void saltCannotBeEmpty(){
       String salt = patient.createPasswordSalt("6910056332070");
        assertNotNull(salt);
    }

    @Test
    public void generatesAnAlphanumericSalt() {
        String saltPattern = "^[A-Z | a-z | 0-9]([A-Z | a-z | 0-9]{19})$";
        Pattern pattern = Pattern.compile(saltPattern);
        String salt = patient.createPasswordSalt("6910056332070");
        Matcher matcher = pattern.matcher(salt);
        assertTrue(matcher.find());
    }

    @Test
    public void theSaltIsAFixedLength() {
        String salt = patient.createPasswordSalt("6910056332070");
        assertEquals(20, salt.length());
    }

    @Test
    public void hashCannotBeNull() {
        String salt = patient.createPasswordSalt("6910056332070");
        String hash = patient.createPasswordHash("6910056332070","123456as", salt);
        assertNotNull(hash);
    }

    @Test
    public void generatesHashInHexadecimal() {
        String hashPattern = "^[0-9 | A-F]([0-9 | A-F]+)$";
        Pattern pattern = Pattern.compile(hashPattern);
        String salt = patient.createPasswordSalt("6910056332070");
        String hash = patient.createPasswordHash("6910056332070","123456as", salt);
        Matcher matcher = pattern.matcher(hash);
        assertTrue(matcher.find());
    }
}