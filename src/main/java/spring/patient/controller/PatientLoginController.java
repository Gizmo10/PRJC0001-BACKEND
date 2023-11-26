package spring.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.patient.model.PatientLogin;
import spring.patient.model.SqlUtil;

import java.sql.*;

@RestController
public class PatientLoginController {

    @Autowired
    private SqlUtil db;
    @PostMapping("/addPatientCredentials")
    public void addCredentials(@RequestBody PatientLogin patient) {
        String id = patient.getPatientId();
        String password = patient.getPatientPassword();
        String salt = null;
        String hash = null;

        patient.setPasswordSalt();
        salt = patient.getPasswordSalt();
        patient.setPasswordHash(password,salt);
        hash = patient.getPasswordHash();

        Connection con = db.getConnection();

        try{
            String sqlQuery = "INSERT INTO patient_login_credentials(id, salt, hash)" +
                    " VALUES(?,?,?)";
            PreparedStatement prpdState = con.prepareStatement(sqlQuery);
            prpdState.setString(1,id);
            prpdState.setString(2,salt);
            prpdState.setString(3,hash);
            prpdState.executeUpdate();
        } catch(SQLException e) {
            System.out.println("failed to update db");
            e.printStackTrace();
        }

        try{
            con.close();
        } catch(SQLException e) {
            System.out.println("Failed to close the database connection.");
            e.printStackTrace();
        }
    }
}
