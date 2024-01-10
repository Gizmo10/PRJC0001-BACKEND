package spring.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.patient.model.PatientRegistration;
import spring.patient.model.SqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("patient")
public class RegisterPatient {
    @Autowired
    private SqlUtil db;

    @PostMapping("/registerPatient")
    public void registerPatient(@RequestBody PatientRegistration regDetails) {
        Connection con = db.getConnection();

        try {
            String sqlQuery = "INSERT INTO registered_patients(name, surname, id, birthdate," +
                    " cellphone_number, email, street, suburb, city, postal_code, province)" +
                    " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement prpdState = con.prepareStatement(sqlQuery);
            prpdState.setString(1, regDetails.getName());
            prpdState.setString(2, regDetails.getSurname());
            prpdState.setString(3, regDetails.getId());
            prpdState.setString(4, regDetails.getBirthdate());
            prpdState.setString(5, regDetails.getCellphoneNumber());
            prpdState.setString(6, regDetails.getEmail());
            prpdState.setString(7, regDetails.getStreetName());
            prpdState.setString(8, regDetails.getSuburb());
            prpdState.setString(9, regDetails.getCity());
            prpdState.setString(10, regDetails.getPostalCode());
            prpdState.setString(11, regDetails.getProvince());
            prpdState.executeUpdate();
        } catch (SQLException e) {
            System.out.println("failed to update db");
            e.printStackTrace();
        }
    }
}


