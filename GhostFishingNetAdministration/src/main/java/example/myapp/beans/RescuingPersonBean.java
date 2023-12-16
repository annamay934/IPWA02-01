package example.myapp.beans;

import ejb.*;
import example.myapp.model.GhostFishingNet;

import example.myapp.model.RescuingPerson;
import example.myapp.model.Status;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

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

    private String userName;
    private String password;


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

    public boolean isUsernameAvailable(String userName) {
        Long count = em.createQuery("SELECT COUNT(rp) FROM RescuingPerson rp WHERE rp.userName = :userName", Long.class)
                .setParameter("userName", userName)
                .getSingleResult();
        return count == 0; // true, wenn kein Benutzer mit diesem Benutzernamen existiert
    }

    public void checkUsernameAvailability() {
        System.out.println("Checking username availability for: " + userName);
        FacesContext context = FacesContext.getCurrentInstance();
        if (isUsernameAvailable(userName)) {
            System.out.println("Username available");
            context.addMessage("username", new FacesMessage(FacesMessage.SEVERITY_INFO, "Username available", null));
        } else {
            System.out.println("Username not available");
            context.addMessage("username", new FacesMessage(FacesMessage.SEVERITY_WARN, "Username not available. Please choose something else.", null));
        }
    }

    public void checkPassword(){
        System.out.println("Checking password for: " + password);
        FacesContext context = FacesContext.getCurrentInstance();
        if (isValidPassword(password)) {
            // Gültiges Passwort
            context.addMessage("password", new FacesMessage(FacesMessage.SEVERITY_INFO, "Valid password", null));
        } else {
            // Ungültiges Passwort
            context.addMessage("password", new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid password. It must contain numbers, both lowercase and uppercase letters, and be at least 8 characters long.", null));
        }
    }

    private boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        return Pattern.matches(passwordPattern, password);
    }

    public RescuingPerson findUserByUsername(String userName) {
        try {
            return em.createQuery("SELECT rp FROM RescuingPerson rp WHERE rp.userName = :userName", RescuingPerson.class)
                    .setParameter("userName", userName)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Kein Benutzer gefunden");
            return null;
        } catch (Exception e) {
            System.out.println("Loggen und behandeln anderer möglicher Fehler");
            return null;
        }
    }

    public String login() {
        System.out.println("userName: " + userName);
        System.out.println("password: " + password); // Achtung: Passwörter sollten nicht geloggt werden!

        RescuingPerson user = findUserByUsername(userName);

        if (user != null && user.checkPassword(password, user.getPassword())) {
            System.out.println("Erfolgreicher Login");
            // Hier Logik für erfolgreichen Login, z.B. Session setzen
            return "Home.xhtml";
        } else {
            System.out.println("Login fehlgeschlagen");
            // Hier Logik für gescheiterten Login, z.B. Fehlermeldung setzen
            return "index.xhtml";
        }
    }

    public void addTestMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Testnachricht", "Dies ist eine Testnachricht"));
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
        res.setPassword(res.hashPassword(res.getPassword()));

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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
