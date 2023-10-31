package example.myapp.beans;

import example.myapp.model.GhostFishingNet;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
@Named
@RequestScoped
public class GhostFishingNetBean {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("GhostFishingNetProject");
    EntityManager em = factory.createEntityManager();

    public GhostFishingNet findGhostFishingNetById(Long id) {
        try {
            return em.find(GhostFishingNet.class, id);
        } catch (NoResultException e) {
            return null; // Keine Übereinstimmung gefunden
        }
    }

}