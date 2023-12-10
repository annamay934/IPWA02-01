package ejb;

import jakarta.persistence.EntityManager;

import example.myapp.model.ReportingPerson;
import jakarta.ejb.Stateless;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ReportingPersonService {
    @PersistenceContext(unitName = "GhostFishingNetProject")
    private EntityManager em;

    public void persist(ReportingPerson rep){
        if (em != null) {
            System.out.println("EntityManager is properly initialized.");

            em.persist(rep);
        } else {
            throw new IllegalStateException("EntityManager is not properly initialized.");
        }
    }

    public void merge(ReportingPerson rep){
        if (em != null) {
            System.out.println("EntityManager is properly initialized.");

            em.merge(rep);
        } else {
            throw new IllegalStateException("EntityManager is not properly initialized.");
        }
    }

}