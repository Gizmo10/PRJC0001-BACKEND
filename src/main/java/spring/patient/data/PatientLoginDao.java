package spring.patient.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.patient.model.PatientLogin;

@Repository
public interface PatientLoginDao extends CrudRepository<PatientLogin,String> {

}
