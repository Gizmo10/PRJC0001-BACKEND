package spring.patient.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.patient.model.MedicalRecords;

import java.util.List;
import java.util.Optional;

@Repository("medicalRecordsDao")
public interface MedicalRecordsDao extends JpaRepository<MedicalRecords, Integer>, MedicalRecordsCustomRepo {
    List<Optional<MedicalRecords>> findAllByIdNumber(String idNumber);
}
