package spring.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.patient.model.MedicalRecords;
import spring.patient.service.MedicalRecordsService;

@CrossOrigin(origins ="http://localhost:3000")
@RestController
@RequestMapping("record")
public class CreateMedicalRecord {
    @Autowired
    MedicalRecordsService medicalRecordsService;


    @PostMapping("/addNew")
    public boolean createMedicalRecord(@ModelAttribute MedicalRecords medicalRecord) {
        return medicalRecordsService.save(medicalRecord);
    }

}
