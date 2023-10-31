package ejb;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import example.myapp.model.ReportingPerson;

@Stateless
public class ReportingPersonService {
    @PersistenceContext(unitName = "GhostFishingNetProject")
    private EntityManager em;
    public void persist(ReportingPerson p){
        em.persist(p);
    }
}