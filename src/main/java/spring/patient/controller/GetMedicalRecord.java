package spring.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.patient.model.FilteredSearch;
import spring.patient.model.MedicalRecords;
import spring.patient.service.MedicalRecordsService;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("record")
@CrossOrigin(origins ="http://localhost:3000")
public class GetMedicalRecord {
    @Autowired
    MedicalRecordsService medicalRecordsService;

    @GetMapping("/getByIdNumber")
    public List<Optional<MedicalRecords>> getByIdNumber(@RequestParam("idNumber") String idNumber) {
        return medicalRecordsService.findAllByIdNumber(idNumber);
    }

    @GetMapping("/getByFilteredSearch")
    public List<MedicalRecords> getByFilteredSearch(@RequestParam("idNumber") String idNumber,
                                                        @RequestParam(value="facility", required=false) String facility,
                                                        @RequestParam(value="doctor",  required=false) String doctor,
                                                        @RequestParam(value="dateFrom", required=false) String dateFrom,
                                                        @RequestParam(value="dateTo", required=false) String dateTo) {

        FilteredSearch filteredSearch = new FilteredSearch(idNumber, doctor, facility, dateFrom, dateTo);
        return medicalRecordsService.findAllByFilteredSearch(filteredSearch);
    }
}
