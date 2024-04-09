package spring.patient.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.patient.model.FilteredSearch;
import spring.patient.model.MedicalRecords;
import spring.patient.service.FilteredSearchServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class MedicalRecordsCustomRepoImpl implements MedicalRecordsCustomRepo{
    private final EntityManager em;
    private static final Logger log = LogManager.getLogger("patientLogin");
    @Autowired
    FilteredSearchServiceImpl filteredSearchService;

    @Override
    public List<MedicalRecords> filteredSearchList(FilteredSearch filteredSearch){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MedicalRecords> cq = cb.createQuery(MedicalRecords.class);
        Root root = cq.from(MedicalRecords.class);
        List<Predicate> predicates = new ArrayList<>();

        log.info("Filter medical records search.");

        if(filteredSearch.getIdNumber().length() != 0) {
            predicates.add(cb.like(root.get("idNumber"),filteredSearch.getIdNumber()));
        }

        if(filteredSearch.getFacility().length() != 0) {
            predicates.add(cb.like(root.get("facility"),filteredSearch.getFacility()));
        }

        if(filteredSearch.getDoctor().length() != 0) {
            predicates.add(cb.like(root.get("name"),filteredSearch.getDoctor()));
        }

        if(filteredSearch.getDateFrom().length() != 0) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("date"), filteredSearch.getDateFrom()));
        }

        if(filteredSearchService.compareDates(filteredSearch)) {
            predicates.add(cb.lessThanOrEqualTo(root.get("date"), filteredSearch.getDateTo()));
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        TypedQuery<MedicalRecords> query = em.createQuery(cq);

        log.info("Search complete.");
        return query.getResultList();
    }
}
