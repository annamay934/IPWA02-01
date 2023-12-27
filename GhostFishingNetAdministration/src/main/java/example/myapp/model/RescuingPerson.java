package example.myapp.model;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<GhostFishingNet> getRescuingGfnList() {
        return rescuingGfnList;
    }
}
