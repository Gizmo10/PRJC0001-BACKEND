package spring.patient.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.patient.data.AppointmentsDao;
import spring.patient.model.Appointments;
import spring.patient.model.FilteredSearch;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service("AppointmentsService")
public class AppointmentsServiceImpl implements AppointmentsService{
    @Autowired
    private AppointmentsDao appointmentsDao;

    @Autowired
    private PatientRegistrationService patientRegistrationService;
    @Autowired
    private static final Logger log = LogManager.getLogger("patientLogin");

    @Override
    public boolean save(Appointments appointment) {
        log.info(String.format("Creating appointment for user: '%s'",appointment.getIdNumber()));
        if(verifyAppointment(appointment)) {
            appointmentsDao.save(appointment);
            log.info(String.format("Successfully created appointment for user: '%s'",appointment.getIdNumber()));
            return true;
        }
        log.warn(String.format("Failed to create appointment for user: '%s'",appointment.getIdNumber()));
        return false;
    }

    @Override
    public List<Optional<Appointments>>findAllByIdNumber(String idNumber) {
        return appointmentsDao.findAllByIdNumber(idNumber);
    }

    @Override
    public List<Appointments> findAllAppointmentsByFilter(FilteredSearch filteredSearch) {
       return appointmentsDao.findAllAppointmentsByFilter(filteredSearch);
    }
    public boolean verifyAppointment(Appointments appointment) {
        boolean isValidName =  patientRegistrationService.validateRegistrationFormInput(appointment.getDoctorName(),
                patientRegistrationService.getGenericPattern());
        boolean isValidIdNumber = patientRegistrationService.validateRegistrationFormInput(appointment.getIdNumber(),
                patientRegistrationService.getIdPattern());
        boolean isValidFacilityName = patientRegistrationService.validateRegistrationFormInput(appointment.getFacilityName(),
                patientRegistrationService.getGenericPattern());
        boolean isValidDate = patientRegistrationService.validateRegistrationFormInput(appointment.getDate(),
                patientRegistrationService.getBirthdatePattern2000());
        boolean isValidContactNumber = patientRegistrationService.validateRegistrationFormInput(appointment.getContactNumber(),
                patientRegistrationService.getCellphoneNumberPattern());

        return isValidName && isValidIdNumber && isValidFacilityName && isValidDate && isValidContactNumber;
    }
}
