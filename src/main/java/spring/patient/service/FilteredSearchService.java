package spring.patient.service;

import spring.patient.model.FilteredSearch;

public interface FilteredSearchService {
    public boolean validateSearchCriteria(FilteredSearch filteredSearch);
    public boolean validateDates(FilteredSearch filteredSearch);
}
