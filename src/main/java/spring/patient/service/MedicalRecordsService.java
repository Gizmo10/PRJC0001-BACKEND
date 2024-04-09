package spring.patient.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.patient.data.MedicalRecordsDao;
import spring.patient.model.FilteredSearch;
import spring.patient.model.MedicalRecords;

import java.util.List;
import java.util.Optional;

@Service("medicalRecordsService")
public class MedicalRecordsService implements MedicalRecordsInterface{

    @Autowired
    private MedicalRecordsDao medicalRecordsDao;

    @Autowired
    private PatientRegistrationService patientRegistrationService;
    @Autowired
    private static final Logger log = LogManager.getLogger("patientLogin");

    @Override
    public boolean save(MedicalRecords record) {
        log.info(String.format("Creating record for user: '%s'",record.getIdNumber()));
        if(this.verifyRecord(record)) {
            medicalRecordsDao.save(record);
            log.info(String.format("Successfully created record for user: '%s'",record.getIdNumber()));
            return true;
        }
        log.warn(String.format("Failed to create record for user: '%s'",record.getIdNumber()));
        return false;
    }

    @Override
    public List<MedicalRecords> findAllByFilteredSearch(FilteredSearch filteredSearch) {
            return medicalRecordsDao.filteredSearchList(filteredSearch);
    }

    @Override
    public List<Optional<MedicalRecords>> findAllByIdNumber(String idNumber) {
        return medicalRecordsDao.findAllByIdNumber(idNumber);
    }

    public boolean verifyRecord(MedicalRecords record) {
        boolean isValidName =  patientRegistrationService.validateRegistrationFormInput(record.getName(),
                patientRegistrationService.getGenericPattern());
        boolean isValidIdNumber = patientRegistrationService.validateRegistrationFormInput(record.getIdNumber(),
                patientRegistrationService.getIdPattern());
        boolean isValidFacilityName = patientRegistrationService.validateRegistrationFormInput(record.getFacility(),
                patientRegistrationService.getGenericPattern());
        boolean isValidDate = patientRegistrationService.validateRegistrationFormInput(record.getDate(),
                patientRegistrationService.getBirthdatePattern2000());
        boolean isValidContactNumber = patientRegistrationService.validateRegistrationFormInput(record.getContactNumber(),
                patientRegistrationService.getCellphoneNumberPattern());

        return isValidName && isValidIdNumber && isValidFacilityName && isValidDate && isValidContactNumber;
    }

}
