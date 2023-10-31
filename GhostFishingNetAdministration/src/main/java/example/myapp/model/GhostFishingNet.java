package example.myapp.model;

import jakarta.enterprise.context.RequestScoped;

import jakarta.inject.Named;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;


@RequestScoped
@Named

@Entity
public class GhostFishingNet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double gfnLocationLatitude;
    private Double gfnLocationLongitude;
    private Integer gfnEstimatedSize;
    private Integer gfnEstimatedSizeLength;
    private Integer gfnEstimatedSizeWidth;

    private String gfnReportDate;
    private String gfnRescueDate;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Status> status;

    @OneToOne
    private RescuingPerson rescuingPerson;

    @ManyToOne
    private ReportingPerson reportingPerson;

    public void calculatedGfnEstimatedSize(Integer gfnEstimatedSizeLength, Integer gfnEstimatedSizeWidth){
        if (gfnEstimatedSizeLength != null && gfnEstimatedSizeWidth != null) {
            gfnEstimatedSize = gfnEstimatedSizeLength * gfnEstimatedSizeWidth;
        }
    }

    public Integer getGfnEstimatedSize() {
        return gfnEstimatedSize;
    }

    public void setGfnEstimatedSize(Integer gfnEstimatedSize) {
        this.gfnEstimatedSize = gfnEstimatedSize;
    }


    public String getGfnReportDate() {
        return gfnReportDate;
    }

    public void setGfnReportDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy");
        this.gfnReportDate  = dateFormat.format(new Date());
    }

    public String getGfnRescueDate() {
        return gfnRescueDate;
    }

    public void setGfnRescueDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy");
        this.gfnRescueDate  = dateFormat.format(new Date());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void rescued() {
        // Get the Ghost Fishing Net entity from the database
        EntityManager em = Persistence.createEntityManagerFactory("GhostFishingNetProject").createEntityManager();
        GhostFishingNet gfn = em.find(GhostFishingNet.class, this.getId());

        // Update the status of the Ghost Fishing Net
        Set<Status> status = gfn.getStatus();

        // Set the status of the Ghost Fishing Net


        // Set the rescue date to the current date in "MM.dd.yyyy" format
        gfn.setGfnRescueDate();

        // Commit the transaction
        em.getTransaction().begin();
        em.merge(gfn);
        em.getTransaction().commit();

        // Close the EntityManager
        em.close();
    }


    public void lost() {
        // Get the Ghost Fishing Net entity from the database
        EntityManager em = Persistence.createEntityManagerFactory("GhostFishingNetProject").createEntityManager();
        GhostFishingNet gfn = em.find(GhostFishingNet.class, this.getId());

        // Update the status of the Ghost Fishing Net
        Set<Status> status = gfn.getStatus();

        // Set the status of the Ghost Fishing Net


        // Commit the transaction
        em.getTransaction().begin();
        em.merge(gfn);
        em.getTransaction().commit();

        // Close the EntityManager
        em.close();
    }

    public void newGhostFishingNet() {
        // Get the Ghost Fishing Net entity from the database
        EntityManager em = Persistence.createEntityManagerFactory("GhostFishingNetProject").createEntityManager();
        GhostFishingNet gfn = em.find(GhostFishingNet.class, this.getId());

        // Commit the transaction
        em.getTransaction().begin();
        em.merge(gfn);
        em.getTransaction().commit();

        // Close the EntityManager
        em.close();
    }

    public Double getGfnLocationLatitude() {
        return gfnLocationLatitude;
    }

    public void setGfnLocationLatitude(Double gfnLocationLatitude) {
        this.gfnLocationLatitude = gfnLocationLatitude;
    }

    public Double getGfnLocationLongitude() {
        return gfnLocationLongitude;
    }

    public void setGfnLocationLongitude(Double gfnLocationLongitude) {
        this.gfnLocationLongitude = gfnLocationLongitude;
    }

    public Integer getGfnEstimatedSizeLength() {
        return gfnEstimatedSizeLength;
    }

    public void setGfnEstimatedSizeLength(Integer gfnEstimatedSizeLength) {
        this.gfnEstimatedSizeLength = gfnEstimatedSizeLength;
    }

    public Integer getGfnEstimatedSizeWidth() {
        return gfnEstimatedSizeWidth;
    }

    public void setGfnEstimatedSizeWidth(Integer gfnEstimatedSizeWidth) {
        this.gfnEstimatedSizeWidth = gfnEstimatedSizeWidth;
    }

    public void setReportingPerson(ReportingPerson reportingPerson) {
        this.reportingPerson = reportingPerson;
    }

    public ReportingPerson getReportingPerson() {
        return reportingPerson;
    }

    public void setRescuingPerson(RescuingPerson rescuingPerson) {
        this.rescuingPerson = rescuingPerson;
    }

    public RescuingPerson getRescuingPerson() {
        return rescuingPerson;
    }

    public Set<Status> getStatus() {
        return status;
    }

    public void clearStatus(){
        status.clear();
    }

    public void addStatus(Status status){
        this.status.add(status);
    }
}

