package spring.patient.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.patient.model.Appointments;

import java.util.List;
import java.util.Optional;

@Repository("AppointmentsDao")
public interface AppointmentsDao extends JpaRepository<Appointments, Integer>, AppointmentsDaoCustomRepo {
    List<Optional<Appointments>> findAllByIdNumber(String idNumber);
}
