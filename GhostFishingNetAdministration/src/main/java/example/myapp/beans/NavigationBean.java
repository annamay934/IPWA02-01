package example.myapp.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class NavigationBean {

    public String goToPageRegisterYourselfAsRescuingPerson() {
        return "RegisterYourselfAsRescuingPerson.xhtml";
    }

    public String goToPageRegisterYourselfAsReportingPerson() {
        return "RegisterYourselfAsReportingPerson.xhtml";
    }

}