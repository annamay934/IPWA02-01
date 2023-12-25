package example.myapp.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.mindrot.jbcrypt.BCrypt;

import java.io.Serializable;

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