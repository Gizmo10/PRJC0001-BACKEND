package spring.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.patient.model.PatientLogin;
import spring.patient.model.SqlUtil;

import java.sql.*;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("patient")
public class AddPatientCredentials {
    @Autowired
    private SqlUtil db;
    @Autowired
    PatientLogin patient;

    @PostMapping("/addPatientCredentials")
    public void addCredentials(@RequestParam("id") String id,@RequestParam("password") String password) {
        patient.setPatientId(id);
        patient.setPatientPassword(password);

        String salt = null;
        String hash = null;

        patient.setPasswordSalt();
        salt = patient.getPasswordSalt();
        patient.setPasswordHash(patient.getPatientPassword(), salt);
        hash = patient.getPasswordHash();

        Connection con = db.getConnection();

        try {
            String sqlQuery = "INSERT INTO patient_login_credentials(id, salt, hash)" +
                    " VALUES(?,?,?)";
            PreparedStatement prpdState = con.prepareStatement(sqlQuery);
            prpdState.setString(1, patient.getPatientId());
            prpdState.setString(2, salt);
            prpdState.setString(3, hash);
            prpdState.executeUpdate();
        } catch (SQLException e) {
            System.out.println("failed to update db");
            e.printStackTrace();
        }
    }
}
