package spring.patient.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ValidateInputTest {

    ValidateInput validateInput = new ValidateInput();

    @Test
    public void testValidId() {
        String id = "9902237223080";
        assertTrue(validateInput.validateRegistrationFormInput(id,validateInput.getIdPattern()));
    }

    @Test
    public void testIdShorterLength() {
        String id = "9902237223";
        assertFalse(validateInput.validateRegistrationFormInput(id,validateInput.getIdPattern()));
    }

    @Test
    public void testIdWithExtraLength() {
        String id = "990223722308099";
        assertFalse(validateInput.validateRegistrationFormInput(id,validateInput.getIdPattern()));
    }

    @Test
    public void testIdWithStartsWithIllegalCharacter() {
        String id = "@902237223080";
        assertFalse(validateInput.validateRegistrationFormInput(id,validateInput.getIdPattern()));
    }

    @Test
    public void testIdEndsWithIllegalCharacter() {
        String id = "990223722308a";
        assertFalse(validateInput.validateRegistrationFormInput(id,validateInput.getIdPattern()));
    }

    @Test
    public void testIdContainsIllegalCharacter() {
        String id = "99022?7223080";
        assertFalse(validateInput.validateRegistrationFormInput(id,validateInput.getIdPattern()));
    }

    @Test
    public void testEmptyId() {
        String id = "";
        assertFalse(validateInput.validateRegistrationFormInput(id,validateInput.getIdPattern()));
    }

    @Test
    public void testPasswordWithSixValidCharacters() {
        String password = "abc123";
        assertTrue(validateInput.validateRegistrationFormInput(password, validateInput.getPasswordPattern()));
    }

    @Test
    public void testPasswordWithNineValidCharacters() {
        String password = "abc123ABC";
        assertTrue(validateInput.validateRegistrationFormInput(password, validateInput.getPasswordPattern()));
    }

    @Test
    public void testPasswordWithFewerValidCharacters() {
        String password = "abc12";
        assertFalse(validateInput.validateRegistrationFormInput(password, validateInput.getPasswordPattern()));
    }

    @Test
    public void testPasswordWithExtraValidCharacters() {
        String password = "abc123EFG4";
        assertFalse(validateInput.validateRegistrationFormInput(password, validateInput.getPasswordPattern()));
    }

    @Test
    public void testPasswordStartsWithInvalidCharacter() {
        String password = "-bc123";
        assertFalse(validateInput.validateRegistrationFormInput(password, validateInput.getPasswordPattern()));
    }

    @Test
    public void testPasswordContainsInvalidCharacter() {
        String password = "abc1234*6";
        assertFalse(validateInput.validateRegistrationFormInput(password, validateInput.getPasswordPattern()));
    }

    @Test
    public void testPasswordEndsWithInvalidCharacter() {
        String password = "abc123#";
        assertFalse(validateInput.validateRegistrationFormInput(password, validateInput.getPasswordPattern()));
    }

    @Test
    public void testEmptyPassword() {
        String password = "";
        assertFalse(validateInput.validateRegistrationFormInput(password, validateInput.getPasswordPattern()));
    }

    @Test
    public void testNameWithValidInput() {
        String name = "Gizmo";
        assertTrue(validateInput.validateRegistrationFormInput(name, validateInput.getNamePattern()));
    }

    @Test
    public void testNameStartsWithInvalidCharacter() {
        String name = "gizmo";
        assertFalse(validateInput.validateRegistrationFormInput(name, validateInput.getNamePattern()));
    }

    @Test
    public void testNameContainsInvalidCharacter() {
        String name = "giZmo";
        assertFalse(validateInput.validateRegistrationFormInput(name, validateInput.getNamePattern()));
    }

    @Test
    public void testNameEndsWithInvalidCharacter() {
        String name = "gizmO";
        assertFalse(validateInput.validateRegistrationFormInput(name, validateInput.getNamePattern()));
    }

    @Test
    public void testNameIsEmpty() {
        String name = "";
        assertFalse(validateInput.validateRegistrationFormInput(name, validateInput.getNamePattern()));
    }

    @Test
    public void testSurnameWithSingleWord() {
        String surname = "Test";
        assertTrue(validateInput.validateRegistrationFormInput(surname,validateInput.getSurnamePattern()));
    }

    @Test
    public void testSurnameWithDoubleWords() {
        String surname = "Test Bot";
        assertTrue(validateInput.validateRegistrationFormInput(surname,validateInput.getSurnamePattern()));
    }

    @Test
    public void testSurnameWithDoubleWordsAndHyphen() {
        String surname = "Test-Bot";
        assertTrue(validateInput.validateRegistrationFormInput(surname,validateInput.getSurnamePattern()));
    }

    @Test
    public void testSurnameWithTripleWords() {
        String surname = "Test Bot Red";
        assertTrue(validateInput.validateRegistrationFormInput(surname,validateInput.getSurnamePattern()));
    }

    @Test
    public void testSurnameWithTripleWordsAndHyphen() {
        String surname = "Test-Bot Red";
        assertTrue(validateInput.validateRegistrationFormInput(surname,validateInput.getSurnamePattern()));
    }

    @Test
    public void testSurnameStartsWithIllegalCharacter() {
        String surname = "test";
        assertFalse(validateInput.validateRegistrationFormInput(surname,validateInput.getSurnamePattern()));
    }

    @Test
    public void testSurnameEndsWithIllegalCharacter() {
        String surname = "Test Bo2";
        assertFalse(validateInput.validateRegistrationFormInput(surname,validateInput.getSurnamePattern()));
    }

    @Test
    public void testSurnameContainsIllegalCharacter() {
        String surname = "Test bot";
        assertFalse(validateInput.validateRegistrationFormInput(surname,validateInput.getSurnamePattern()));
    }

    @Test
    public void testSurnameIsEmpty() {
        String surname = "";
        assertFalse(validateInput.validateRegistrationFormInput(surname,validateInput.getSurnamePattern()));
    }

    @Test
    public void testValidBirthdate1900() {
        String year = "1932";
        assertTrue(validateInput.validateRegistrationFormInput(year,validateInput.getBirthdatePattern1900()));
    }

    @Test
    public void testValidBirthdate2000() {
        String year = "2012";
        assertTrue(validateInput.validateRegistrationFormInput(year,validateInput.getBirthdatePattern2000()));
    }

    @Test
    public void testOutOfRangeBirthdate1900() {
        String year = "1232";
        assertFalse(validateInput.validateRegistrationFormInput(year,validateInput.getBirthdatePattern1900()));
    }

    @Test
    public void testOutOfRangeBirthdate2000() {
        String year = "3002";
        assertFalse(validateInput.validateRegistrationFormInput(year,validateInput.getBirthdatePattern2000()));
    }

    @Test
    public void testBirthdateWithIllegalCharacter1900() {
        String year = "193$";
        assertFalse(validateInput.validateRegistrationFormInput(year,validateInput.getBirthdatePattern1900()));
    }

    @Test
    public void testBirthdateWithIllegalCharacter2000() {
        String year = "20e2";
        assertFalse(validateInput.validateRegistrationFormInput(year,validateInput.getBirthdatePattern2000()));
    }

    @Test
    public void testEmptyBirthdate1900() {
        String year = "";
        assertFalse(validateInput.validateRegistrationFormInput(year,validateInput.getBirthdatePattern1900()));
    }

    @Test
    public void testEmptyBirthdate2000() {
        String year = "";
        assertFalse(validateInput.validateRegistrationFormInput(year,validateInput.getBirthdatePattern2000()));
    }

    @Test
    public void testValidCellphoneWithCode() {
        String number = "+27736440987";
        assertTrue(validateInput.validateRegistrationFormInput(number,validateInput.getCellphoneNumberWithCountryCodePattern()));
    }

    @Test
    public void testValidCellphone() {
        String number = "0736440987";
        assertTrue(validateInput.validateRegistrationFormInput(number,validateInput.getCellphoneNumberPattern()));
    }

    @Test
    public void testCellphoneWithCodeContainsIllegalCharacter() {
        String number = "+2773o440987";
        assertFalse(validateInput.validateRegistrationFormInput(number,validateInput.getCellphoneNumberWithCountryCodePattern()));
    }

    @Test
    public void testCellphoneContainsIllegalCharacter() {
        String number = "07364409@7";
        assertFalse(validateInput.validateRegistrationFormInput(number,validateInput.getCellphoneNumberPattern()));
    }

    @Test
    public void testCellphoneWithCodeOutOfBoundarySize() {
        String number = "+2773644098734";
        assertFalse(validateInput.validateRegistrationFormInput(number,validateInput.getCellphoneNumberWithCountryCodePattern()));
    }

    @Test
    public void testCellphoneWithOutOfBoundarySize() {
        String number = "07364409827";
        assertFalse(validateInput.validateRegistrationFormInput(number,validateInput.getCellphoneNumberPattern()));
    }

    @Test
    public void testEmptyCellphoneWithCode() {
        String number = "";
        assertFalse(validateInput.validateRegistrationFormInput(number,validateInput.getCellphoneNumberWithCountryCodePattern()));
    }

    @Test
    public void testEmptyCellphone() {
        String number = "";
        assertFalse(validateInput.validateRegistrationFormInput(number,validateInput.getCellphoneNumberPattern()));
    }

    @Test
    public void testValidEmail() {
        String email = "gizmo@gmail.com";
        assertTrue(validateInput.validateRegistrationFormInput(email, validateInput.getEmailPattern()));
    }

    @Test
    public void testValidEmailWithPeriodBeforeHandle() {
        String email = "gizmo.test@gmail.com";
        assertTrue(validateInput.validateRegistrationFormInput(email, validateInput.getEmailPattern()));
    }

    @Test
    public void testEmailStartsWithNumber() {
        String email = "2gizmo@gmail.com";
        assertFalse(validateInput.validateRegistrationFormInput(email, validateInput.getEmailPattern()));
    }

    @Test
    public void testEmailWithDoublePeriodsBeforeHandle() {
        String email = "gizmo.test.trial@gmail.com";
        assertFalse(validateInput.validateRegistrationFormInput(email, validateInput.getEmailPattern()));
    }

    @Test
    public void testEmailWithoutHandle() {
        String email = "gizmo.test5gmail.com";
        assertFalse(validateInput.validateRegistrationFormInput(email, validateInput.getEmailPattern()));
    }

    @Test
    public void testEmailEndsWithIllegalCharacter() {
        String email = "gizmo.test@gmail.com.";
        assertFalse(validateInput.validateRegistrationFormInput(email, validateInput.getEmailPattern()));
    }

    @Test
    public void testEmptyEmail() {
        String email = "";
        assertFalse(validateInput.validateRegistrationFormInput(email, validateInput.getEmailPattern()));
    }

    @Test
    public void testValidPostalCode() {
        String code = "8001";
        assertTrue(validateInput.validateRegistrationFormInput(code,validateInput.getPostalCodePattern()));
    }

    @Test
    public void testOutOfRangePostalCode() {
        String code = "80001";
        assertFalse(validateInput.validateRegistrationFormInput(code,validateInput.getPostalCodePattern()));
    }

    @Test
    public void testPostalCodeWithIllegalExpression() {
        String code = "8s01";
        assertFalse(validateInput.validateRegistrationFormInput(code,validateInput.getPostalCodePattern()));
    }

    @Test
    public void testEmptyPostalCode() {
        String code = "";
        assertFalse(validateInput.validateRegistrationFormInput(code,validateInput.getPostalCodePattern()));
    }

    @Test
    public void testValidStreetName() {
        String street = "12 Oak Street";
        assertTrue(validateInput.validateRegistrationFormInput(street,validateInput.getStreetNamePattern()));
    }

    @Test
    public void testValidStreetNameWithMultipleWords() {
        String street = "12 Oak Eagle Street";
        assertTrue(validateInput.validateRegistrationFormInput(street,validateInput.getStreetNamePattern()));
    }

    @Test
    public void testValidStreetNameWithLetterAppendedToHouseNumber() {
        String street = "12A Oak Street";
        assertTrue(validateInput.validateRegistrationFormInput(street,validateInput.getStreetNamePattern()));
    }

    @Test
    public void testStreetNameWithoutHouseNumber() {
        String street = "Oak Street";
        assertFalse(validateInput.validateRegistrationFormInput(street,validateInput.getStreetNamePattern()));
    }

    @Test
    public void testStreetNameWithoutName() {
        String street = "12A";
        assertFalse(validateInput.validateRegistrationFormInput(street,validateInput.getStreetNamePattern()));
    }

    @Test
    public void testEmptyStreetName() {
        String street = "";
        assertFalse(validateInput.validateRegistrationFormInput(street,validateInput.getStreetNamePattern()));
    }

    @Test
    public void testValidGenericWithoutSpace() {
        String street = "Gizmo";
        assertTrue(validateInput.validateRegistrationFormInput(street,validateInput.getGenericPattern()));
    }

    @Test
    public void testValidGenericWithSpace() {
        String street = "Gizmo Test";
        assertTrue(validateInput.validateRegistrationFormInput(street,validateInput.getGenericPattern()));
    }

    @Test
    public void testGenericWithIllegalCapitalisation() {
        String street = "Gizmo test";
        assertFalse(validateInput.validateRegistrationFormInput(street,validateInput.getGenericPattern()));
    }

    @Test
    public void testEmptyGeneric() {
        String street = "";
        assertFalse(validateInput.validateRegistrationFormInput(street,validateInput.getGenericPattern()));
    }

    @Test
    public void testValidIdMatchesBirthdate() {
        String id = "9209145234099";
        String birthdate = "1992/09/14";
        assertTrue(validateInput.idMatchesBirthdate(id,birthdate));
    }

    @Test
    public void testIdMatchesBirthdateYearMismatch() {
        String id = "9209145234099";
        String birthdate = "1982/09/14";
        assertFalse(validateInput.idMatchesBirthdate(id,birthdate));
    }

    @Test
    public void testIdMatchesBirthdateMonthMismatch() {
        String id = "9209145234099";
        String birthdate = "1992/02/14";
        assertFalse(validateInput.idMatchesBirthdate(id,birthdate));
    }

    @Test
    public void testIdMatchesBirthdateDayMismatch() {
        String id = "9202145234099";
        String birthdate = "1992/02/24";
        assertFalse(validateInput.idMatchesBirthdate(id,birthdate));
    }





























}