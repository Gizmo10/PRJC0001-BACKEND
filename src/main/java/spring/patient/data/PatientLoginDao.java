package spring.patient.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.patient.model.PatientLogin;

import java.util.Optional;

@Repository("patientLoginDao")
public interface PatientLoginDao extends JpaRepository<PatientLogin,String> {
    Optional<PatientLogin> findByResetToken(String resetToken);
}
