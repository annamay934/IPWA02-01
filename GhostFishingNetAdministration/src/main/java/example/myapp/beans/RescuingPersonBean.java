package example.myapp.beans;

import ejb.*;
import example.myapp.model.GhostFishingNet;

import example.myapp.model.RescuingPerson;
import example.myapp.model.Status;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Named
@RequestScoped


public class RescuingPersonBean {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private RescuingPersonService rescuingPersonService;

    @Inject
    private StatusService statusService;

    @Inject
    private GhostFishingNetService ghostFishingNetService;

    @Inject
    private GhostFishingNetBean ghostFishingNetBean;


    public RescuingPerson findById(Long id) {
        try {
            return em.createQuery("SELECT rp FROM RescuingPerson rp WHERE rp.id = :id", RescuingPerson.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public RescuingPerson findRescuingPersonByFirstname(String firstName) {
        try {
            return em.createQuery("SELECT rp FROM RescuingPerson rp WHERE rp.firstName = :firstName", RescuingPerson.class)
                    .setParameter("firstName", firstName)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public List<RescuingPerson> getRescuingPersons() {
        return em.createQuery("SELECT DISTINCT rp FROM RescuingPerson rp LEFT JOIN FETCH rp.rescuingGfn", RescuingPerson.class)
                .getResultList();
    }

    public void persistRescuingPerson(RescuingPerson res) {
        System.out.println("Save method called!");
        if (res != null) {

            rescuingPersonService.persist(res);
        } else {
            System.out.println("GhostFishingNet object is null."); // Falls das Objekt null ist
        }

    }

    public void saveRescuingPerson(RescuingPerson res) {
        System.out.println("Save RescuingPerson method called!");
        ghostFishingNetBean.loadSelectedGfn();
        GhostFishingNet currentGfn = ghostFishingNetBean.getSelectedGfn(); // Lokale Variable erstellen und Wert zuweisen

        System.out.println("saveRescuingPerson currentGfn: " + currentGfn);


        if (res != null && currentGfn != null) {
            // Protokolliere die eingegebenen Daten
            System.out.println("Selected Rescuing Person Details:");
            System.out.println("ID: " + currentGfn.getId());
            System.out.println("currentGfn.getStatus(): " + currentGfn.getStatus());

            Status status = currentGfn.getStatus();
            status.setGfnStatusRescuePending(true);
            statusService.merge(status);

            System.out.println("Status 1" + status.getGfnStatusRescuePending());
            System.out.println("Status 1" + status.getGfnStatusRescued());
            System.out.println("Status 1" + status.getGfnStatusLost());
            System.out.println("Status 1" + status.getGfnStatusReported());
            System.out.println("Status 1" + status.getGfnStatusRescued());

            res.setRescuingGfn(currentGfn);
            rescuingPersonService.persist(res);

            currentGfn.setRescuingPerson(res);
            ghostFishingNetService.merge(currentGfn);

            System.out.println("CurrentGfn Status1: " + currentGfn.getStatus());
            System.out.println("First Name: " + res.getFirstName());
            System.out.println("Last Name: " + res.getLastName());
            System.out.println("Phone Number: " + res.getPhoneNumber());
            System.out.println("Username: " + res.getUserName());
            System.out.println("Password: " + res.getPassword());
            System.out.println("RescuingGfn: " + res.getRescuingGfn());
            System.out.println("Id: " + res.getId());

        } else {
            System.out.println("GhostFishingNet or selectedRescuingPersonId is null."); // Falls das Objekt null ist

            System.out.println("First Name: " + res.getFirstName());
            System.out.println("Last Name: " + res.getLastName());
            System.out.println("Phone Number: " + res.getPhoneNumber());
            System.out.println("Username: " + res.getUserName());
            System.out.println("Password: " + res.getPassword());
            System.out.println("RescuingGfn: " + res.getRescuingGfn());
            System.out.println("Rescue Date: " + res.getPhoneNumber());
            System.out.println("Id: " + res.getId());
        }

    }

}
