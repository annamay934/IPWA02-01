package ejb;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import example.myapp.model.ReportingPerson;
import example.myapp.model.RescuingPerson;
import jakarta.ejb.Stateless;

@Stateless
public class RescuingPersonService {
    @PersistenceContext(unitName = "GhostFishingNetProject")
    private EntityManager em;

    public void persist(RescuingPerson res){
        if (em != null) {
            System.out.println("EntityManager is properly initialized.");

            em.persist(res);
        } else {
            throw new IllegalStateException("EntityManager is not properly initialized.");
        }
    }

    public void merge(RescuingPerson res){
        if (em != null) {
            System.out.println("EntityManager is properly initialized.");

            em.merge(res);
        } else {
            throw new IllegalStateException("EntityManager is not properly initialized.");
        }
    }

}
