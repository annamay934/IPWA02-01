package example.myapp.model;

import example.myapp.beans.ReportingPersonBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named


@Entity
@DiscriminatorValue("REP") // Set the discriminator value for RescuingPerson
public class ReportingPerson extends NaturalPerson {


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "reportingPerson", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<GhostFishingNet> reportingGfnList = new ArrayList<>();


    public void addGhostFishingNet(GhostFishingNet reportedNet) {
        this.reportingGfnList.add(reportedNet);
        reportedNet.setReportingPerson(this);
    }

    public void removeGhostFishingNet(GhostFishingNet reportedNet) {
        this.reportingGfnList.remove(reportedNet);
        reportedNet.setReportingPerson(null);
    }

    public List<GhostFishingNet> getReportingGfnList() {
        return reportingGfnList;
    }
}

