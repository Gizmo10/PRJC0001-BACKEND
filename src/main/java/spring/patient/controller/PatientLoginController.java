package spring.patient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.patient.model.PatientLogin;
import spring.patient.model.SqlUtil;

import java.sql.*;

@RestController
public class PatientLoginController {

    @GetMapping("/test")
    public String testServer(){
        return ("The server does work bro");
    }

    @GetMapping("/loginUser")
    public boolean verifyCredentials(@RequestBody PatientLogin patient) {
        String id = patient.getPatientId();
        String password = patient.getPatientPassword();
        String hash = null;
        boolean userExists = false;

        SqlUtil db = new SqlUtil();
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

        try{
            con.close();
        } catch(SQLException e) {
            System.out.println("Failed to close the database connection.");
            e.printStackTrace();
        }

        return userExists;
    }

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

        SqlUtil db = new SqlUtil();
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
