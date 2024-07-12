package spring.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.patient.model.Appointments;
import spring.patient.model.FilteredSearch;
import spring.patient.service.AppointmentsService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins ="http://localhost:3000")
@RestController
@RequestMapping("appointments")
public class AppointmentsController {
    @Autowired
    AppointmentsService appointmentsService;

    @GetMapping("/byIdNumber")
    public List<Optional<Appointments>> findAllAppointmentsById(String idNumber) {
        return appointmentsService.findAllByIdNumber(idNumber);
    }

    @GetMapping("/byFilteredSearch")
    public List<Appointments> findAppointmentsByFilter(@RequestParam("idNumber") String idNumber,
                                                       @RequestParam(value="facility", required=false) String facility,
                                                       @RequestParam(value="doctor",  required=false) String doctor,
                                                       @RequestParam(value="dateFrom", required=false) String dateFrom,
                                                       @RequestParam(value="dateTo", required=false) String dateTo) {
        FilteredSearch filteredSearch = new FilteredSearch(idNumber, facility, doctor, dateFrom, dateTo);
        return appointmentsService.findAllAppointmentsByFilter(filteredSearch);
    }

    @PostMapping("/createAppointment")
    public boolean addAppointment(@ModelAttribute Appointments appointment) {
        return appointmentsService.save(appointment);
    }
}
