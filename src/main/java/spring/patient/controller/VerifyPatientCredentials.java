package spring.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.patient.model.PatientLogin;
import spring.patient.model.SqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("patient")
public class VerifyPatientCredentials {
    @Autowired
    private SqlUtil db;
    @Autowired
    private PatientLogin patient;

    @GetMapping("/loginUser")
    public boolean verifyCredentials(@RequestParam("patientId") String patientId, @RequestParam String patientPassword) {
        patient.setPatientId(patientId);
        patient.setPatientPassword(patientPassword);

        String id = patient.getPatientId();
        String password = patient.getPatientPassword();
        String hash = null;
        boolean userExists = false;

        Connection con = db.getConnection();

        try{
            String sqlQuery = "SELECT * FROM patient_login_credentials WHERE id = id";
            PreparedStatement prepStat = con.prepareStatement(sqlQuery);
            ResultSet res = prepStat.executeQuery();

            while(res.next()){
                String pId = res.getString(1);
                String pSalt = res.getString(2);
                String pHash = res.getString(3);

                if( pId.equals(id)){
                    patient.setPasswordHash(password, pSalt);
                    hash = patient.getPasswordHash();

                    if( pHash.equals(hash)){
                        userExists = true;
                    } else {
                        userExists = false;
                    }
                }
            }
        } catch(SQLException e){
            System.out.println("Failed to retrieve user credentials");
            e.printStackTrace();
        }
        return userExists;
    }
}
