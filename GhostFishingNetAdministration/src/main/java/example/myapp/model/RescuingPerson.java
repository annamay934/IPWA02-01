package example.myapp.model;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.mindrot.jbcrypt.BCrypt;

import jakarta.persistence.*;

import java.io.Serializable;

@RequestScoped
@Named

@Entity
@DiscriminatorValue("RES")
public class RescuingPerson extends NaturalPerson {

    @OneToOne(mappedBy = "rescuingPerson", cascade = CascadeType.MERGE)
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

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

}
