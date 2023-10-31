package example.myapp.model;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@RequestScoped
@Named

@Entity
@DiscriminatorValue("RES") // Set the discriminator value for RescuingPerson
public class RescuingPerson extends NaturalPerson {
    @OneToOne
    private GhostFishingNet rescuingGfn;
    private String userName;
    private String password;

    public void rescue(GhostFishingNet rescuedNet) {
        this.rescuingGfn= rescuedNet;
    }

    public GhostFishingNet getRescuingGfn() {
        return rescuingGfn;
    }

    public void setRescuingGfn(GhostFishingNet rescuingGfn) {
        this.rescuingGfn = rescuingGfn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
