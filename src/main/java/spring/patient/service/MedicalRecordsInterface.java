package spring.patient.service;

import spring.patient.model.FilteredSearch;
import spring.patient.model.MedicalRecords;

import java.util.List;
import java.util.Optional;

public interface MedicalRecordsInterface {
    public boolean save(MedicalRecords record);
    public List<MedicalRecords> findAllByFilteredSearch(FilteredSearch filteredSearch);
    public List<Optional<MedicalRecords>> findAllByIdNumber(String idNumber);
}
