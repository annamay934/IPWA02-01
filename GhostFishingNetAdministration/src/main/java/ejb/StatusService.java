package ejb;

import example.myapp.model.Status;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class StatusService {
    @PersistenceContext(unitName = "GhostFishingNetProject")
    private EntityManager em;

    public void persist(Status status){
        if (em != null) {
            System.out.println("EntityManager is properly initialized.");

            em.persist(status);
        } else {
            throw new IllegalStateException("EntityManager is not properly initialized.");
        }
    }

    public void merge(Status status) {
        if (em != null) {
            System.out.println("EntityManager is properly initialized.");
            em.merge(status);
        } else {
            throw new IllegalStateException("EntityManager is not properly initialized.");
        }
    }
}
