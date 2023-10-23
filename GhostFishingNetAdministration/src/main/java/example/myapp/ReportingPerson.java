package example.myapp;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@RequestScoped
@Named

public class ReportingPerson extends NaturalPerson {
   private GhostFishingNet reportingGfn;

  // @Inject
    // public com.example.myapp.ReportingPerson(String firstName, String lastName, String number) {
    //   super(firstName, lastName, number);
    //   super.setFirstName(firstName);
    //   super.setLastName(lastName);
    //   super.setNumber(number);
    //   }

    public void report(GhostFishingNet reportedNet) {
        this.reportingGfn = reportedNet;
    }

    public GhostFishingNet getReportingGfn() {
        return reportingGfn;
    }

    public void setReportingGfn(GhostFishingNet reportingGfn) {
        this.reportingGfn = reportingGfn;
    }
}

