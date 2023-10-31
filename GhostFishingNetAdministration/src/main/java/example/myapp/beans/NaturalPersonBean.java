package example.myapp.beans;

import example.myapp.model.NaturalPerson;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
@Named
@RequestScoped
public class NaturalPersonBean {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("GhostFishingNetProject");
    EntityManager em = factory.createEntityManager();

    public NaturalPerson findByFirstname(String firstName) {
        try {
            return em.createQuery("SELECT np FROM NaturalPerson np WHERE np.firstName = :vorname", NaturalPerson.class)
                    .setParameter("vorname", firstName)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // Keine Übereinstimmung gefunden
        }
    }
}
