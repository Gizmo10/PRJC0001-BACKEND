package spring.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.patient.data.PatientLoginDao;
import spring.patient.model.PatientLogin;
import spring.patient.model.SqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("patient")
public class VerifyPatientCredentials {

    @Autowired
    private PatientLogin patient;
    @Autowired
    private PatientLoginDao patientLoginDao;

    @GetMapping("/loginUser")
    public boolean verifyCredentials(@RequestParam("id") String patientId, @RequestParam("password") String patientPassword) {
        boolean userExists = false;

        try{
            for (PatientLogin p : patientLoginDao.findAll()) {
                if (p.getPatientId().equals(patient.getPatientId())) {
                    patient.setPatientId(patientId);
                    patient.setPatientPassword(patientPassword);
                    patient.setPasswordHash(patient.getPatientPassword(), p.getPasswordSalt());
                    userExists = patient.getPasswordHash().equals(p.getPasswordHash());
                    }
                }
        } catch(Exception e) {
            System.out.println("Failed to verify patient credentials");
            e.printStackTrace();
        }
        return userExists;
    }
}
