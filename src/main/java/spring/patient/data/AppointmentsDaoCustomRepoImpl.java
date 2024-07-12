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
import spring.patient.model.Appointments;
import spring.patient.model.FilteredSearch;
import spring.patient.service.FilteredSearchServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("AppointmentsDaoCustomRepoImpl")
@AllArgsConstructor
public class AppointmentsDaoCustomRepoImpl implements AppointmentsDaoCustomRepo{

    private final EntityManager em;
    private static final Logger log = LogManager.getLogger("patientLogin");

    @Autowired
    FilteredSearchServiceImpl filteredSearchServiceImpl;

    @Override
    public List<Appointments> findAllAppointmentsByFilter(FilteredSearch filteredSearch){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Appointments> cq = cb.createQuery(Appointments.class);
        Root root = cq.from(Appointments.class);
        List<Predicate> predicates = new ArrayList<>();

        log.info("Filter appointments search.");

        if(filteredSearch.getIdNumber().length() != 0) {
            predicates.add(cb.like(root.get("idNumber"),filteredSearch.getIdNumber()));
        }

        if(filteredSearch.getFacility().length() != 0) {
            predicates.add(cb.like(root.get("facilityName"),filteredSearch.getFacility()));
        }

        if(filteredSearch.getDoctor().length() != 0) {
            predicates.add(cb.like(root.get("doctorName"),filteredSearch.getDoctor()));
        }

        if(filteredSearch.getDateFrom().length() != 0) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("date"), filteredSearch.getDateFrom()));
        }

        if(filteredSearchServiceImpl.compareDates(filteredSearch)) {
            predicates.add(cb.lessThanOrEqualTo(root.get("date"), filteredSearch.getDateTo()));
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        TypedQuery<Appointments> query = em.createQuery(cq);

        log.info("Search complete.");
        return query.getResultList();
    }
}
