package spring.patient.data;

import spring.patient.model.FilteredSearch;
import spring.patient.model.MedicalRecords;

import java.util.List;
import java.util.Optional;

public interface MedicalRecordsCustomRepo {
    public List<MedicalRecords> filteredSearchList(FilteredSearch filteredSearch);
}
