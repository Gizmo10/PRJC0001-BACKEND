package spring.patient.service;

import spring.patient.model.PatientLogin;

import java.util.Optional;

public interface PatientLoginInterface {
    public Optional<PatientLogin> findByResetToken(String resetToken);
    public void save(PatientLogin patient);
}
