package spring.patient.service;

import org.springframework.stereotype.Service;
import spring.patient.model.Appointments;
import spring.patient.model.FilteredSearch;

import java.util.List;
import java.util.Optional;

public interface AppointmentsService {
    public boolean save(Appointments appointment);
    List<Optional<Appointments>> findAllByIdNumber(String idNumber);
    List<Appointments> findAllAppointmentsByFilter(FilteredSearch filteredSearch);
}
