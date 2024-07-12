package spring.patient.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointments {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="id_number")
    private String idNumber;
    @Column(name="doctor_name")
    private String doctorName;
    @Column(name="facility_name")
    private String facilityName;
    @Column(name="contact_number")
    private String contactNumber;
    @Column(name="date")
    private String date;
}
