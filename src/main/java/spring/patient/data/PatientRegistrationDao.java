package spring.patient.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.patient.model.PatientRegistration;

@Repository
public interface PatientRegistrationDao extends CrudRepository<PatientRegistration,String> {
}
