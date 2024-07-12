package spring.patient.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.patient.model.PatientRegistration;

import java.util.Optional;

@Repository("patientRegistrationDao")
public interface PatientRegistrationDao extends JpaRepository<PatientRegistration,String> {
    Optional<PatientRegistration> findByEmail(String email);
    Optional<PatientRegistration> findById(String id);
}
