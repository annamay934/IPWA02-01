package example.myapp.model;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.mindrot.jbcrypt.BCrypt;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@RequestScoped
@Named

@Entity
@DiscriminatorValue("RES")
public class RescuingPerson extends NaturalPerson {
    @OneToMany(mappedBy = "rescuingPerson", fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<GhostFishingNet> rescuingGfnList = new ArrayList<>();


    public void addGhostFishingNet(GhostFishingNet reportedNet) {
        this.rescuingGfnList.add(reportedNet);
        reportedNet.setRescuingPerson(this);
    }

    public void removeGhostFishingNet(GhostFishingNet reportedNet) {
        this.rescuingGfnList.remove(reportedNet);
        reportedNet.setRescuingPerson(null);
    }

    public List<GhostFishingNet> getRescuingGfnList() {
        return rescuingGfnList;
    }
}
