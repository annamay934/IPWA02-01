package example.myapp.beans;

import ejb.ReportingPersonService;
import example.myapp.model.ReportingPerson;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import jakarta.transaction.Transactional;
import java.util.List;

@Named
@RequestScoped


public class ReportingPersonBean {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private ReportingPersonService reportingPersonService;

    private Long reportingPersonById;

    public ReportingPerson findById(Long id) {
        try {
            return em.createQuery("SELECT rp FROM ReportingPerson rp WHERE rp.id = :id", ReportingPerson.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public ReportingPerson findReportingPersonByFirstname(String firstName) {
        try {
            return em.createQuery("SELECT rp FROM ReportingPerson rp WHERE rp.firstName = :firstName", ReportingPerson.class)
                    .setParameter("firstName", firstName)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /** public List allReportingPersons() {
        Query query = em.createQuery("SELECT rp FROM ReportingPerson rp", ReportingPerson.class);
        return query.getResultList();
    }
     **/

    @Transactional
    public List<ReportingPerson> getReportingPersons() {
        return em.createQuery("SELECT DISTINCT rp FROM ReportingPerson rp LEFT JOIN FETCH rp.reportingGfnList", ReportingPerson.class)
                .getResultList();
    }

    public void saveReportingPerson(ReportingPerson rep) {
        System.out.println("Save method called!");
        if (rep != null) {

            reportingPersonService.persist(rep);
        } else {
            System.out.println("GhostFishingNet object is null."); // Falls das Objekt null ist
        }

    }

    public ReportingPerson getReportingPersonById(Long id) {
        return em.find(ReportingPerson.class, id);
    }

    public void setReportingPersonById(Long reportingPersonById) {
        this.reportingPersonById = reportingPersonById;
    }
}