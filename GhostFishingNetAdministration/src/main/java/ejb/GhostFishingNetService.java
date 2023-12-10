package ejb;

import example.myapp.beans.GhostFishingNetBean;
import example.myapp.model.GhostFishingNet;
import example.myapp.model.ReportingPerson;
import jakarta.ejb.Stateless;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class GhostFishingNetService {
    @PersistenceContext(unitName = "GhostFishingNetProject")
    private EntityManager em;


    public void persist(GhostFishingNet gfn){
        if (em != null) {
            System.out.println("EntityManager is properly initialized.");

            em.persist(gfn);
        } else {
            throw new IllegalStateException("EntityManager is not properly initialized.");
        }
    }

    public void merge(GhostFishingNet gfn) {
        if (em != null) {
            System.out.println("EntityManager is properly initialized.");
            em.merge(gfn);
        } else {
            throw new IllegalStateException("EntityManager is not properly initialized.");
        }
    }
}
