package example.myapp.model;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
@Named
public class NavigationBean {

    public String goToPageReportNewGhostFishingNet() {
        return "ReportNewGhostFishingNet.xhtml";
    }

    public String goToPageRegisterYourselfAsRescuingPerson() {
        return "RegisterYourselfAsRescuingPerson.xhtml";
    }

    public String goToPageReportAGhostFishingNetAsLost() {
        return "ReportAGhostFishingNetAsLost.xhtml";
    }
    public String goToPageReportAGhostFishingNetAsRescued() {
        return "ReportAGhostFishingNetAsRescued.xhtml";
    }
}