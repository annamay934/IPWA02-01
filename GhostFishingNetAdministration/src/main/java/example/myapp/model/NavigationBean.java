package example.myapp.model;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
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

    public String goToPageLogInForRescuingPerson() {
        return "LogIn.xhtml";
    }

}