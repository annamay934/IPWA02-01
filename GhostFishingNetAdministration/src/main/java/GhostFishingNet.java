import java.util.Date;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@RequestScoped
@Named

public class GhostFishingNet {
    private Double gfnLocation;
    private Integer gfnEstimatedSize;
    private Status gfnStatus;
    private Date gfnReportDate;
    private Date gfnRescueDate;

   /* @Inject
    public GhostFishingNet(Double gfnLocation, Integer gfnEstimatedSize, Status gfnStatus, Date gfnReportDate, Date gfnRescueDate) {
        this.gfnLocation = gfnLocation;
        this.gfnEstimatedSize = gfnEstimatedSize;
        this.gfnStatus = gfnStatus;
        this.gfnReportDate = gfnReportDate;
        this.gfnRescueDate = gfnRescueDate;
    }
    */

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
}
