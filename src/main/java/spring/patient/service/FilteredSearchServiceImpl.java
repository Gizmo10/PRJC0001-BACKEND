package spring.patient.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.patient.model.FilteredSearch;

@Service
public class FilteredSearchServiceImpl implements FilteredSearchService{
    @Autowired
    PatientRegistrationService patientRegistrationService = new PatientRegistrationService();

    private static final Logger log = LogManager.getLogger("patientLogin");
    @Override
    public boolean validateSearchCriteria(FilteredSearch filteredSearch) {
        boolean validName = patientRegistrationService.validateRegistrationFormInput(filteredSearch.getDoctor(),
                patientRegistrationService.getGenericPattern());
        boolean validFacility = patientRegistrationService.validateRegistrationFormInput(filteredSearch.getFacility(),
                patientRegistrationService.getGenericPattern());

        return validName && validFacility;
    }

    public int dayToInt(String date) {
        if(date.length() == 10) {
            try
            {
                return Integer.parseInt(date.substring(8));
            } catch(Exception e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int monthToInt(String date) {
        if(date.length() == 10) {
            try
            {
                return Integer.parseInt(date.substring(5,7));
            } catch(Exception e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int yearToInt(String date) {
        if(date.length() == 10) {
            try
            {
                return Integer.parseInt(date.substring(0,4));
            } catch(Exception e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return 0;
    }

    public boolean compareDates(FilteredSearch filteredSearch) {

        if(!this.validateDates(filteredSearch))
            return false;

        int dayFrom = this.dayToInt(filteredSearch.getDateFrom());
        int monthFrom = this.monthToInt(filteredSearch.getDateFrom());
        int yearFrom = this.yearToInt(filteredSearch.getDateFrom());
        int dayTo = this.dayToInt(filteredSearch.getDateTo());
        int monthTo = this.monthToInt(filteredSearch.getDateTo());
        int yearTo = this.yearToInt(filteredSearch.getDateTo());

        if(yearFrom > yearTo) {
            return false;
        } else if(yearFrom < yearTo) {
            return true;
        } else {
            if(monthFrom > monthTo) {
                return false;
            } else if(monthFrom < monthTo) {
                return true;
            } else {
                if(dayFrom > dayTo) {
                    return false;
                } else if(dayFrom <= dayTo) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean validateDates(FilteredSearch filteredSearch) {
        return filteredSearch.getDateFrom().length() != 0 && filteredSearch.getDateTo().length() != 0;
    }
}
