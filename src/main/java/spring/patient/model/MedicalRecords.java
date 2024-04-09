package spring.patient.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="medical_records")
@Data
@NoArgsConstructor
public class MedicalRecords {
    @Id
    @Column(name="id")
    private int id;

    @Column(name="patient_identity_number")
    private String idNumber;

    @Column(name="doctor_name")
    private String name;

    @Column(name="facility_name")
    private String facility;

    @Column(name="diagnosis")
    private String diagnosis;

    @Column(name="prescription")
    private String prescription;

    @Column(name="date")
    private String date;

    @Column(name="contact_number")
    private String contactNumber;
}
