package spring.patient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilteredSearch {
    private String idNumber;
    private String doctor;
    private String facility;
    private String dateFrom;
    private String dateTo;
}
