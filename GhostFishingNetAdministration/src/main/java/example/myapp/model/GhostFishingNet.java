package example.myapp.model;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import jakarta.persistence.*;

import java.util.Date;

@RequestScoped
@Named

@Entity
public class GhostFishingNet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double gfnLocationLatitude;
    private Double gfnLocationLongitude;
    private Integer gfnEstimatedSize;
    private Integer gfnEstimatedSizeLength;
    private Integer gfnEstimatedSizeWidth;

    @Temporal(TemporalType.DATE)
    private Date gfnReportDate;
    @Temporal(TemporalType.DATE)
    private Date gfnRescueDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STATUS_ID")
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "RESCUINGPERSON_ID")
    private RescuingPerson rescuingPerson;

    @ManyToOne(fetch = FetchType.EAGER)
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

    public void addReportingPerson(ReportingPerson reportingPerson) {
        if (reportingPerson != null) {
            this.reportingPerson = reportingPerson;
            reportingPerson.addGhostFishingNet(this);
        }
    }

    public void setRescuingPerson(RescuingPerson rescuingPerson) {
        this.rescuingPerson = rescuingPerson;
    }

    public RescuingPerson getRescuingPerson() {
        return rescuingPerson;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

