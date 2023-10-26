package spring.patient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.patient.model.PatientLogin;
import spring.patient.model.SqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
public class PatientLoginController {

    @GetMapping("/test")
    public String testServer(){
        return ("The server does work bro");
    }

    @PostMapping("/addPatientCredentials")
    public void addCredentials(@RequestBody PatientLogin patient) {
        String name = patient.getPatientName();
        String surname = patient.getPatientSurname();
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
            Statement statement = con.createStatement();
            String sqlQuery = "INSERT INTO patient_login_credentials(name,surname,salt,hash)" +
                    " VALUES(?,?,?,?)";
            PreparedStatement prpdState = con.prepareStatement(sqlQuery);
            prpdState.setString(1,name);
            prpdState.setString(2,surname);
            prpdState.setString(3,salt);
            prpdState.setString(4,hash);

            prpdState.executeUpdate();
        } catch(SQLException e) {
            System.out.println("failed to update db");
            e.printStackTrace();
        }
    }
}
