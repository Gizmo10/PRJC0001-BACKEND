package spring.patient.model;

import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class SqlUtil {
    private Connection connection = null;

    public SqlUtil() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/med_records_data", "root", "bsx@14EHI");
            if (this.connection.equals(null)) {
                System.out.println("Failed to connect to database");
            } else {
                System.out.println("Connection successful");
            }
        } catch(ClassNotFoundException e) {
            System.out.println("Class not found.");
            e.printStackTrace();
        } catch(SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }
}
