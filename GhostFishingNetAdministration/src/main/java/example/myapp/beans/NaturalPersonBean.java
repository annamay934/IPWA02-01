package example.myapp.beans;

import ejb.GhostFishingNetService;
import ejb.ReportingPersonService;
import ejb.RescuingPersonService;
import ejb.StatusService;
import example.myapp.model.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

@Named
@SessionScoped

public class NaturalPersonBean implements Serializable {

    @PersistenceContext(unitName = "GhostFishingNetProject")
    private EntityManager em;

    @Inject
    private RescuingPersonService rescuingPersonService;

    @Inject
    private ReportingPersonService reportingPersonService;

    //@Inject
    //private StatusService statusService;

   //@Inject
   //private GhostFishingNetService ghostFishingNetService;

    //@Inject
    //private GhostFishingNetBean ghostFishingNetBean;

    private String userName;
    private String password;

    private ReportingPerson loggedInReportingPerson;
    private RescuingPerson loggedInRescuingPerson;

    public boolean isUsernameAvailable(String userName) {
        Long countRescuing = em.createQuery("SELECT COUNT(rp) FROM RescuingPerson rp WHERE rp.userName = :userName", Long.class)
                .setParameter("userName", userName)
                .getSingleResult();
        Long countReporting = em.createQuery("SELECT COUNT(rp) FROM ReportingPerson rp WHERE rp.userName = :userName", Long.class)
                .setParameter("userName", userName)
                .getSingleResult();
        return (countRescuing + countReporting) == 0; // true, wenn der Benutzername in keiner der beiden Entitäten existiert
    }

    public boolean isUsernameFound(String userName) {
        Long countRescuing = em.createQuery("SELECT COUNT(rp) FROM RescuingPerson rp WHERE rp.userName = :userName", Long.class)
                .setParameter("userName", userName)
                .getSingleResult();
        Long countReporting = em.createQuery("SELECT COUNT(rp) FROM ReportingPerson rp WHERE rp.userName = :userName", Long.class)
                .setParameter("userName", userName)
                .getSingleResult();
        return (countRescuing + countReporting) > 0; // true, wenn ein Benutzer mit diesem Benutzernamen existiert
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

    public void checkUsernameFound() {
        System.out.println("Found username in database for: " + userName);
        if (isUsernameFound(userName)) {
            System.out.println("Username found");
        } else {
            System.out.println("Username not found");
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

    public RescuingPerson findRescuingPersonByUsername(String userName) {
        try {
            return em.createQuery("SELECT res FROM RescuingPerson res WHERE res.userName = :userName", RescuingPerson.class)
                    .setParameter("userName", userName)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No Rescuing Person found");
            return null;
        } catch (Exception e) {
            System.out.println("Loggen und behandeln anderer möglicher Fehler");
            return null;
        }
    }

    public ReportingPerson findReportingPersonByUsername(String userName) {
        try {
            return em.createQuery("SELECT rep FROM ReportingPerson rep WHERE rep.userName = :userName", ReportingPerson.class)
                    .setParameter("userName", userName)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No Reporting Person found");
            return null;
        } catch (Exception e) {
            System.out.println("Loggen und behandeln anderer möglicher Fehler");
            return null;
        }
    }

    public NaturalPerson getLoggedInPerson() {
        // Prüfe, ob eine RescuingPerson eingeloggt ist
        if (loggedInRescuingPerson != null) {
            return loggedInRescuingPerson;
        }
        // Prüfe, ob eine ReportingPerson eingeloggt ist
        else if (loggedInReportingPerson != null) {
            return loggedInReportingPerson;
        }
        // Falls keine Person eingeloggt ist, gib null zurück
        else {
            return null;
        }
    }

    public String login() {
        System.out.println("userName: " + userName);

        // Überprüfen, ob es eine RescuingPerson ist
        RescuingPerson rescuingUser = findRescuingPersonByUsername(userName);
        if (rescuingUser != null && rescuingUser.checkPassword(password, rescuingUser.getPassword())) {
            System.out.println("Erfolgreicher RescuingPerson-Login");
            loggedInRescuingPerson = rescuingUser;
            // Logik für RescuingPerson
            return "RescuingPersonHome.xhtml";
        }

        // Überprüfen, ob es eine ReportingPerson ist
        ReportingPerson reportingUser = findReportingPersonByUsername(userName);
        if (reportingUser != null && reportingUser.checkPassword(password, reportingUser.getPassword())) {
            System.out.println("Erfolgreicher ReportingPerson-Login");
            loggedInReportingPerson = reportingUser;
            // Logik für ReportingPerson
            return "ReportingPersonHome.xhtml";
        }

        // Falls Login fehlschlägt
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Denied", "Wrong Input. Please keep attention to info boxes."));
        System.out.println("Login fehlgeschlagen");

        userName = "";
        password = "";
        return "index.xhtml";
    }

    public String logout() {
        // Benutzerspezifische Logout-Logik, z.B. Session-Daten löschen
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        // Weiterleitung zur Startseite (index.xhtml)
        return "/index.xhtml?faces-redirect=true";
    }

    public String goBack() {
        // Benutzerspezifische Logout-Logik, z.B. Session-Daten löschen
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        // Weiterleitung zur Startseite (index.xhtml)
        return "/index.xhtml?faces-redirect=true";
    }

    public void saveRescuingPerson(RescuingPerson res) {
        System.out.println("Save RescuingPerson method called!");
        System.out.println("RescuingPersonBean userName: " + userName);
        //ghostFishingNetBean.loadSelectedGfn();
        res.setPassword(res.hashPassword(password));
        res.setUserName(userName);

        //GhostFishingNet currentGfn = ghostFishingNetBean.getSelectedGfn(); // Lokale Variable erstellen und Wert zuweisen
        //System.out.println("saveRescuingPerson currentGfn: " + currentGfn);

        if (res != null) {
            // Protokolliere die eingegebenen Daten
            System.out.println("Selected Rescuing Person Details:");
            //System.out.println("ID: " + currentGfn.getId());
            //System.out.println("currentGfn.getStatus(): " + currentGfn.getStatus());

            rescuingPersonService.persist(res);

            //Status status = currentGfn.getStatus();
            //status.setGfnStatusRescuePending(true);
            //statusService.merge(status);

            //System.out.println("Status 1" + status.getGfnStatusRescuePending());
            //System.out.println("Status 1" + status.getGfnStatusRescued());
            //System.out.println("Status 1" + status.getGfnStatusLost());
            //System.out.println("Status 1" + status.getGfnStatusReported());
            //System.out.println("Status 1" + status.getGfnStatusRescued());

            //res.addGhostFishingNet(currentGfn);
            //currentGfn.setRescuingPerson(res);
            //ghostFishingNetService.merge(currentGfn);

            //System.out.println("CurrentGfn Status1: " + currentGfn.getStatus());
            System.out.println("First Name: " + res.getFirstName());
            System.out.println("Last Name: " + res.getLastName());
            System.out.println("Phone Number: " + res.getPhoneNumber());
            System.out.println("Username: " + res.getUserName());
            System.out.println("Password: " + res.getPassword());
            System.out.println("RescuingGfn: " + res.getRescuingGfnList());
            System.out.println("Id: " + res.getId());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Registration done!"));

            //return "index.xhtml?faces-redirect=true";
        } else {
            System.out.println("GhostFishingNet or selectedRescuingPersonId is null."); // Falls das Objekt null ist

            System.out.println("First Name: " + res.getFirstName());
            System.out.println("Last Name: " + res.getLastName());
            System.out.println("Phone Number: " + res.getPhoneNumber());
            System.out.println("Username: " + res.getUserName());
            System.out.println("Password: " + res.getPassword());
            System.out.println("RescuingGfn: " + res.getRescuingGfnList());
            System.out.println("Rescue Date: " + res.getPhoneNumber());
            System.out.println("Id: " + res.getId());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Something went wrong."));
        }
        res.setFirstName("");
        res.setLastName("");
        res.setPhoneNumber("");
        userName = "";
        password = "";

        //return null;
    }

    public void saveReportingPerson(ReportingPerson rep) {
        System.out.println("Save RescuingPerson method called!");
        System.out.println("RescuingPersonBean userName: " + userName);
        //ghostFishingNetBean.loadSelectedGfn();
        rep.setPassword(rep.hashPassword(password));
        rep.setUserName(userName);

        //GhostFishingNet currentGfn = ghostFishingNetBean.getSelectedGfn(); // Lokale Variable erstellen und Wert zuweisen
        //System.out.println("saveRescuingPerson currentGfn: " + currentGfn);

        //if (rep != null && currentGfn != null) {
        if (rep != null) {
            // Protokolliere die eingegebenen Daten
            System.out.println("Selected Rescuing Person Details:");
            //System.out.println("ID: " + currentGfn.getId());
            //System.out.println("currentGfn.getStatus(): " + currentGfn.getStatus());

            //Status status = currentGfn.getStatus();
            //status.setGfnStatusReported(true);
            //statusService.merge(status);

            //System.out.println("Status 1" + status.getGfnStatusRescuePending());
            //System.out.println("Status 1" + status.getGfnStatusRescued());
            //System.out.println("Status 1" + status.getGfnStatusLost());
            //System.out.println("Status 1" + status.getGfnStatusReported());
            //System.out.println("Status 1" + status.getGfnStatusRescued());

            //rep.addGhostFishingNet(currentGfn);
            reportingPersonService.persist(rep);

            //currentGfn.setReportingPerson(rep);
            //ghostFishingNetService.merge(currentGfn);

            //System.out.println("CurrentGfn Status1: " + currentGfn.getStatus());
            System.out.println("First Name: " + rep.getFirstName());
            System.out.println("Last Name: " + rep.getLastName());
            System.out.println("Phone Number: " + rep.getPhoneNumber());
            System.out.println("Username: " + rep.getUserName());
            System.out.println("Password: " + rep.getPassword());
            System.out.println("RescuingGfn: " + rep.getReportingGfnList());
            System.out.println("Id: " + rep.getId());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Registration done!"));

            //return "index.xhtml?faces-redirect=true";
        } else {
            System.out.println("GhostFishingNet or selectedRescuingPersonId is null."); // Falls das Objekt null ist

            System.out.println("First Name: " + rep.getFirstName());
            System.out.println("Last Name: " + rep.getLastName());
            System.out.println("Phone Number: " + rep.getPhoneNumber());
            System.out.println("Username: " + rep.getUserName());
            System.out.println("Password: " + rep.getPassword());
            System.out.println("RescuingGfn: " + rep.getReportingGfnList());
            System.out.println("Rescue Date: " + rep.getPhoneNumber());
            System.out.println("Id: " + rep.getId());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Something went wrong."));
        }
        rep.setFirstName("");
        rep.setLastName("");
        rep.setPhoneNumber("");
        userName = "";
        password = "";
        //return null;
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

    public ReportingPerson getLoggedInReportingPerson() {
        return loggedInReportingPerson;
    }

    public void setLoggedInReportingPerson(ReportingPerson loggedInReportingPerson) {
        this.loggedInReportingPerson = loggedInReportingPerson;
    }

    public RescuingPerson getLoggedInRescuingPerson() {
        return loggedInRescuingPerson;
    }

    public void setLoggedInRescuingPerson(RescuingPerson loggedInRescuingPerson) {
        this.loggedInRescuingPerson = loggedInRescuingPerson;
    }
}
