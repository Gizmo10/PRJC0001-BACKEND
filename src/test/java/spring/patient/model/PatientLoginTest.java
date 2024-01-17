
package spring.patient.model;

//import org.junit.Test; Tests aren't recognised when you import this
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

//Make the class public for test to be picked up
public class PatientLoginTest {
    private PatientLogin patient = new PatientLogin();

    @Test
    public void saltCannotBeEmpty(){
        patient.setPasswordSalt("6910056332070");
        assertNotNull(patient.getPasswordSalt());
    }

    @Test
    public void generatesAnAlphanumericSalt() {
        String saltPattern = "^[A-Z | a-z | 0-9]([A-Z | a-z | 0-9]{19})$";
        Pattern pattern = Pattern.compile(saltPattern);
        patient.setPasswordSalt("6910056332070");
        Matcher matcher = pattern.matcher(patient.getPasswordSalt());
        assertTrue(matcher.find());
    }

    @Test
    public void theSaltIsAFixedLength() {
        patient.setPasswordSalt("6910056332070");
        assertEquals(20, patient.getPasswordSalt().length());
    }

    @Test
    public void hashCannotBeNull() {
        patient.setPasswordSalt("6910056332070");
        patient.setPasswordHash("6910056332070","123456as", patient.getPasswordSalt());
        assertNotNull(patient.getPasswordHash());
    }

    @Test
    public void generatesHashInHexadecimal() {
        String hashPattern = "^[0-9 | A-F]([0-9 | A-F]+)$";
        Pattern pattern = Pattern.compile(hashPattern);
        patient.setPasswordSalt("6910056332070");
        patient.setPasswordHash("6910056332070","123456as", patient.getPasswordSalt());
        Matcher matcher = pattern.matcher(patient.getPasswordHash());
        assertTrue(matcher.find());
    }
}