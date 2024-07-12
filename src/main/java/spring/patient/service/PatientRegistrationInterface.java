package spring.patient.service;

import spring.patient.model.PatientRegistration;

import java.util.Optional;

public interface PatientRegistrationInterface {
    public Optional<PatientRegistration> findByEmail(String email);
    public Optional<PatientRegistration> findById(String id);
    public void save(PatientRegistration registrationDetails);
}
