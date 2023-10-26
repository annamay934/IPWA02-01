package example.myapp;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import javax.persistence.*;
import java.util.Date;


@RequestScoped
@Named

@Entity
public class GhostFishingNet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double gfnLocation;
    private Integer gfnEstimatedSize;
    @Embedded
    private Status gfnStatus;
    private Date gfnReportDate;
    private Date gfnRescueDate;

    public Double getGfnLocation() {
        return gfnLocation;
    }

    public void setGfnLocation(Double gfnLocation) {
        this.gfnLocation = gfnLocation;
    }

    public Integer getGfnEstimatedSize() {
        return gfnEstimatedSize;
    }


    public void setGfnEstimatedSize(Integer gfnEstimatedSize) {
        this.gfnEstimatedSize = gfnEstimatedSize;
    }

    public Status getGfnStatus() {
        return gfnStatus;
    }

    public void setGfnStatus(Status gfnStatus) {
        this.gfnStatus = gfnStatus;
    }

    public Date getGfnReportDate() {
        return gfnReportDate;
    }

    public void setGfnReportDate(Date gfnReportDate) {
        this.gfnReportDate = gfnReportDate;
    }

    public Date getGfnRescueDate() {
        return gfnRescueDate;
    }

    public void setGfnRescueDate(Date gfnRescueDate) {
        this.gfnRescueDate = gfnRescueDate;
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
        Status status = gfn.getGfnStatus();

        // Set the status of the Ghost Fishing Net
        status.setGfnStatusLost(true);

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
        Status status = gfn.getGfnStatus();

        // Set the status of the Ghost Fishing Net
        status.setGfnStatusLost(true);

        // Commit the transaction
        em.getTransaction().begin();
        em.merge(gfn);
        em.getTransaction().commit();

        // Close the EntityManager
        em.close();
    }

}

