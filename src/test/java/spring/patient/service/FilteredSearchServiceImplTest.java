package spring.patient.service;

import org.junit.jupiter.api.Test;
import spring.patient.model.FilteredSearch;

import static org.junit.jupiter.api.Assertions.*;

class FilteredSearchServiceImplTest {
    FilteredSearchServiceImpl filteredSearchService = new FilteredSearchServiceImpl();

    @Test
    public void testValidSearchCriteriaWithValidName() {
        FilteredSearch filteredSearch = new FilteredSearch("9601135223088", "Gizmo Test",
                "Test Facility","2024-01-01","2024-04-01");
        assertTrue(filteredSearchService.validateSearchCriteria(filteredSearch));
    }

    @Test
    public void testValidSearchCriteriaWithInvalidName() {
        FilteredSearch filteredSearch = new FilteredSearch("9601135223088", "Gizmo 4est",
                "Test Facility","2024-01-01","2024-04-01");
        assertFalse(filteredSearchService.validateSearchCriteria(filteredSearch));
    }

    @Test
    public void testValidSearchCriteriaWithValidFacility() {
        FilteredSearch filteredSearch = new FilteredSearch("9601135223088", "Gizmo Test",
                "Test Facility","2024-01-01","2024-04-01");
        assertTrue(filteredSearchService.validateSearchCriteria(filteredSearch));
    }

    @Test
    public void testValidSearchCriteriaWithInvalidFacility() {
        FilteredSearch filteredSearch = new FilteredSearch("9601135223088", "Gizmo Test",
                "7est Facility","2024-01-01","2024-04-01");
        assertFalse(filteredSearchService.validateSearchCriteria(filteredSearch));
    }

    @Test
    public void testValidSearchCriteriaWithInvalidFacilityAndName() {
        FilteredSearch filteredSearch = new FilteredSearch("9601135223088", "Giz3o Test",
                "0est Facility","2024-01-01","2024-04-01");
        assertFalse(filteredSearchService.validateSearchCriteria(filteredSearch));
    }

    @Test
    public void testDayToIntCorrectValues() {
        String date = "2024-01-14";
        assertEquals(14, filteredSearchService.dayToInt(date));
    }

    @Test
    public void testDayToIntIncorrectValues() {
        String date = "2024-01";
        assertEquals(0, filteredSearchService.dayToInt(date));
    }

    @Test
    public void testMonthToIntCorrectValues() {
        String date = "2024-11-14";
        assertEquals(11, filteredSearchService.monthToInt(date));
    }

    @Test
    public void testMonthToIntIncorrectValues() {
        String date = "2024-14";
        assertEquals(0, filteredSearchService.monthToInt(date));
    }

    @Test
    public void testYearToIntCorrectValues() {
        String date = "2024-01-14";
        assertEquals(2024, filteredSearchService.yearToInt(date));
    }

    @Test
    public void testYearToIntIncorrectValues() {
        String date = "224-01-14";
        assertEquals(0, filteredSearchService.yearToInt(date));
    }

    @Test
    public void testCompareDatesYearFromLessThanYearTo() {
        FilteredSearch filteredSearch = new FilteredSearch("9601135223088", "Gizmo Test",
                "Test Facility","2023-01-01","2024-04-01");
        assertTrue(filteredSearchService.compareDates(filteredSearch));
    }

    @Test
    public void testCompareDatesYearFromGreaterThanYearTo() {
        FilteredSearch filteredSearch = new FilteredSearch("9601135223088", "Gizmo Test",
                "Test Facility","2023-01-01","2022-04-01");
        assertFalse(filteredSearchService.compareDates(filteredSearch));
    }

    @Test
    public void testCompareDatesYearsEqualMonthFromLessThanMonthTo() {
        FilteredSearch filteredSearch = new FilteredSearch("9601135223088", "Gizmo Test",
                "Test Facility","2023-01-01","2023-04-01");
        assertTrue(filteredSearchService.compareDates(filteredSearch));
    }

    @Test
    public void testCompareDatesYearsEqualMonthFromGreaterThanMonthTo() {
        FilteredSearch filteredSearch = new FilteredSearch("9601135223088", "Gizmo Test",
                "Test Facility","2023-10-01","2023-04-01");
        assertFalse(filteredSearchService.compareDates(filteredSearch));
    }

    @Test
    public void testCompareDatesYearsAndMonthsEqualDayFromLessThanDayTo() {
        FilteredSearch filteredSearch = new FilteredSearch("9601135223088", "Gizmo Test",
                "Test Facility","2023-10-01","2023-10-04");
        assertTrue(filteredSearchService.compareDates(filteredSearch));
    }

    @Test
    public void testCompareDatesYearsAndMonthsEqualDayFromGreaterThanDayTo() {
        FilteredSearch filteredSearch = new FilteredSearch("9601135223088", "Gizmo Test",
                "Test Facility","2023-10-03","2023-10-01");
        assertFalse(filteredSearchService.compareDates(filteredSearch));
    }

    @Test
    public void testCompareDatesYearsAndMonthsEqualDayFromEqualsDayTo() {
        FilteredSearch filteredSearch = new FilteredSearch("9601135223088", "Gizmo Test",
                "Test Facility","2023-10-01","2023-10-01");
        assertTrue(filteredSearchService.compareDates(filteredSearch));
    }

    @Test
    public void testValidateDatesDateFromAndDateToNotEmpty() {
        FilteredSearch filteredSearch = new FilteredSearch("9601135223088", "Gizmo Test",
                "Test Facility","2023-10-01","2023-10-01");
        assertTrue(filteredSearchService.validateDates(filteredSearch));
    }

    @Test
    public void testValidateDatesDateFromEmpty() {
        FilteredSearch filteredSearch = new FilteredSearch("9601135223088", "Gizmo Test",
                "Test Facility","2023-10-01","2023-10-01");
        filteredSearch.setDateFrom("");
        assertFalse(filteredSearchService.validateDates(filteredSearch));
    }

    @Test
    public void testValidateDatesDateToEmpty() {
        FilteredSearch filteredSearch = new FilteredSearch("9601135223088", "Gizmo Test",
                "Test Facility","2023-10-01","2023-10-01");
        filteredSearch.setDateTo("");
        assertFalse(filteredSearchService.validateDates(filteredSearch));
    }

    @Test
    public void testValidateDatesDateFromAndDateToEmpty() {
        FilteredSearch filteredSearch = new FilteredSearch("9601135223088", "Gizmo Test",
                "Test Facility","2023-10-01","2023-10-01");
        filteredSearch.setDateTo("");
        filteredSearch.setDateFrom("");
        assertFalse(filteredSearchService.validateDates(filteredSearch));
    }








}