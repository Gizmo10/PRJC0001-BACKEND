package spring.patient.data;

import spring.patient.model.Appointments;
import spring.patient.model.FilteredSearch;

import java.util.List;
import java.util.Optional;

public interface AppointmentsDaoCustomRepo {
    public List<Appointments> findAllAppointmentsByFilter(FilteredSearch filteredSearch);
}
