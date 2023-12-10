package example.myapp.beans;

import ejb.GhostFishingNetService;
import ejb.ReportingPersonService;
import ejb.StatusService;
import example.myapp.model.GhostFishingNet;
import example.myapp.model.ReportingPerson;

import example.myapp.model.RescuingPerson;
import example.myapp.model.Status;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Set;

@Named
@RequestScoped
public class GhostFishingNetBean {
    @PersistenceContext(unitName = "GhostFishingNetProject")
    private EntityManager em;

    @Inject
    private GhostFishingNetService ghostFishingNetService;
    @Inject
    private ReportingPersonService reportingPersonService;

    @Inject
    private StatusService statusService;

    @Inject
    private ReportingPersonBean reportingPersonBean;

    private Long selectedReportingPersonId;

    private ReportingPerson selectedReportingPerson;

    private Long selectedGfnId;
    private GhostFishingNet selectedGfn;


    public void loadSelectedReportingPerson() {
        System.out.println("loadSelectedReportingPerson selectedReportingPersonId: " + selectedReportingPersonId);
        if (selectedReportingPersonId != null) {
            selectedReportingPerson = reportingPersonBean.getReportingPersonById(selectedReportingPersonId);
            // Verwende reportingPerson oder führe weitere Aktionen durch
            System.out.println("loadSelectedReportingPerson selectedReportingPerson: " + selectedReportingPerson.getId());
            System.out.println("loadSelectedReportingPerson selectedReportingPerson: " + selectedReportingPerson);
        } else {
            ReportingPerson anonymousReportingPerson = new ReportingPerson();
            anonymousReportingPerson.setId(selectedReportingPersonId);
            anonymousReportingPerson.setFirstName("Anonymous");
            anonymousReportingPerson.setLastName("Anonymous");
            anonymousReportingPerson.setPhoneNumber("1234567");

            reportingPersonBean.saveReportingPerson(anonymousReportingPerson);

            selectedReportingPerson = anonymousReportingPerson;
        }
    }

    public void saveGhostFishingNet(GhostFishingNet gfn) {
        System.out.println("Save GhostFishingNet method called!");
        loadSelectedReportingPerson();
        ReportingPerson currentReportingPerson = selectedReportingPerson; // Lokale Variable erstellen und Wert zuweisen

        System.out.println("saveGhostFishingNet currentReportingPerson: " + currentReportingPerson);
        System.out.println("saveGhostFishingNet selectedReportingPerson: " + selectedReportingPerson);


        if (gfn != null && currentReportingPerson != null) {
            // Protokolliere die eingegebenen Daten
            System.out.println("Selected Reporting Person Details:");
            System.out.println("ID: " + currentReportingPerson.getId());
            System.out.println("First Name: " + currentReportingPerson.getFirstName());
            System.out.println("Last Name: " + currentReportingPerson.getLastName());

            gfn.setReportingPerson(currentReportingPerson);
            currentReportingPerson.addGhostFishingNet(gfn);

            Status status1 = new Status();
            status1.setGfnStatusReported(true);
            gfn.setStatus(status1);
            status1.setGhostFishingNet(gfn);
            System.out.println("status1: " + status1);
            System.out.println("status1: " + gfn.getStatus());
            statusService.persist(status1);

            reportingPersonService.merge(currentReportingPerson);

            System.out.println("Latitude: " + gfn.getGfnLocationLatitude());
            System.out.println("Longitude: " + gfn.getGfnLocationLongitude());
            System.out.println("Estimated Length: " + gfn.getGfnEstimatedSizeLength());
            System.out.println("Estimated Width: " + gfn.getGfnEstimatedSizeWidth());
            System.out.println("Report Date: " + gfn.getGfnReportDate());
            System.out.println("Rescue Date: " + gfn.getGfnRescueDate());
            System.out.println("Reporting Person: " + gfn.getReportingPerson());
            System.out.println("Id: " + gfn.getId());
            System.out.println("Id: " + gfn.getStatus());

        } else if (gfn != null){
            System.out.println("SelectedReportingPersonId is null."); // Falls das Objekt null ist

            Status status1 = new Status();
            status1.setGfnStatusReported(true);
            gfn.setStatus(status1);
            status1.setGhostFishingNet(gfn);
            System.out.println("status1: " + status1);
            System.out.println("status1: " + gfn.getStatus());
            statusService.persist(status1);

            System.out.println("Latitude: " + gfn.getGfnLocationLatitude());
            System.out.println("Longitude: " + gfn.getGfnLocationLongitude());
            System.out.println("Estimated Length: " + gfn.getGfnEstimatedSizeLength());
            System.out.println("Estimated Width: " + gfn.getGfnEstimatedSizeWidth());
            System.out.println("Report Date: " + gfn.getGfnReportDate());
            System.out.println("Rescue Date: " + gfn.getGfnRescueDate());
            System.out.println("Reporting Person: " + gfn.getReportingPerson());
            System.out.println("Id: " + gfn.getId());
        } else {
            System.out.println("GhostFishingNet and selectedReportingPersonId is null.");
        }

    }

    public void loadSelectedGfn() {
        System.out.println("loadSelectedGfn selectedGfnId: " + selectedGfnId);
        if (selectedGfnId != null) {
            selectedGfn = getGhostFishingNetByID(selectedGfnId);
            // Verwende reportingPerson oder führe weitere Aktionen durch
            System.out.println("loadSelectedGfn selectedGfn: " + selectedGfn.getId());
            System.out.println("loadSelectedGfn selectedGfn: " + selectedGfn);
        } else {
            GhostFishingNet gfn = new GhostFishingNet();
            gfn.setId(selectedGfnId);
            gfn.setGfnLocationLatitude(25.678);
            gfn.setGfnLocationLongitude(124.456);
            gfn.calculatedGfnEstimatedSize(3,1);
            Status status1 = new Status();
            status1.setGfnStatusReported(true);
            gfn.setStatus(status1);

            ghostFishingNetService.persist(gfn);

            selectedGfn = gfn;
        }
    }

    public void saveGhostFishingNetAsLost(GhostFishingNet gfn) {
        System.out.println("saveGhostFishingNetAsLost method called!");
        loadSelectedGfn();
        GhostFishingNet currentGfn = selectedGfn; // Lokale Variable erstellen und Wert zuweisen

        System.out.println("saveRescuingPerson currentGfn: " + currentGfn);

        if (currentGfn != null) {
            // Protokolliere die eingegebenen Daten
            System.out.println("Selected Rescuing Person Details:");
            System.out.println("ID: " + currentGfn.getId());
            System.out.println("currentGfn.getStatus(): " + currentGfn.getStatus());

            Status status = currentGfn.getStatus();
            status.setGfnStatusLost(true);
            statusService.merge(status);

            System.out.println("Status 1" + status.getGfnStatusRescuePending());
            System.out.println("Status 1" + status.getGfnStatusRescued());
            System.out.println("Status 1" + status.getGfnStatusLost());
            System.out.println("Status 1" + status.getGfnStatusReported());
            System.out.println("Status 1" + status.getGfnStatusRescued());

            ghostFishingNetService.merge(currentGfn);
            System.out.println("CurrentGfn Status1: " + currentGfn.getStatus());

        } else {
            System.out.println("GhostFishingNet or selectedRescuingPersonId is null.");
        }
    }

    public void saveGhostFishingNetAsRescued(GhostFishingNet gfn) {
        System.out.println("saveGhostFishingNetAsRescued method called!");
        loadSelectedGfn();
        GhostFishingNet currentGfn = selectedGfn; // Lokale Variable erstellen und Wert zuweisen

        System.out.println("saveRescuingPerson currentGfn: " + currentGfn);

        if (currentGfn != null) {
            // Protokolliere die eingegebenen Daten
            System.out.println("Selected Rescuing Person Details:");
            System.out.println("ID: " + currentGfn.getId());
            System.out.println("currentGfn.getStatus(): " + currentGfn.getStatus());

            Status status = currentGfn.getStatus();
            status.setGfnStatusRescued(true);
            statusService.merge(status);

            System.out.println("Status 1" + status.getGfnStatusRescuePending());
            System.out.println("Status 1" + status.getGfnStatusRescued());
            System.out.println("Status 1" + status.getGfnStatusLost());
            System.out.println("Status 1" + status.getGfnStatusReported());
            System.out.println("Status 1" + status.getGfnStatusRescued());

            ghostFishingNetService.merge(currentGfn);
            System.out.println("CurrentGfn Status1: " + currentGfn.getStatus());

        } else {
            System.out.println("GhostFishingNet or selectedRescuingPersonId is null.");
        }
    }

    public GhostFishingNet getGhostFishingNetByID (Long id) {
        return em.find(GhostFishingNet.class, id);
    }

    public List<GhostFishingNet> getAllGhostFishingNets() {
        return em.createQuery("SELECT DISTINCT gfn FROM GhostFishingNet gfn", GhostFishingNet.class)
                .getResultList();
    }

    public Long getSelectedReportingPersonId() {
        return selectedReportingPersonId;
    }

    public void setSelectedReportingPersonId(Long selectedReportingPersonId) {
        this.selectedReportingPersonId = selectedReportingPersonId;
    }

    public ReportingPerson getSelectedReportingPerson() {
        return selectedReportingPerson;
    }

    public void setSelectedReportingPerson(ReportingPerson selectedReportingPerson) {
        this.selectedReportingPerson = selectedReportingPerson;
    }

    public void setSelectedGfnId(Long selectedGfnId) {
        this.selectedGfnId = selectedGfnId;
    }

    public Long getSelectedGfnId() {
        return selectedGfnId;
    }


    public GhostFishingNet getSelectedGfn() {
        return selectedGfn;
    }

    public void setSelectedGfn(GhostFishingNet selectedGfn) {
        this.selectedGfn = selectedGfn;
    }
}