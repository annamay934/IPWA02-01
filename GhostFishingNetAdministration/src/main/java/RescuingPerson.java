import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@RequestScoped
@Named

public class RescuingPerson extends NaturalPerson {
    private GhostFishingNet rescuingGfn;

    //  @Inject
    //  public RescuingPerson(String firstName, String lastName, String number) {
    //    super(firstName, lastName, number);
    //    super.setFirstName(firstName);
    //    super.setLastName(lastName);
    //    super.setNumber(number);
    // }

    public void rescue(GhostFishingNet rescuedNet) {
        this.rescuingGfn= rescuedNet;
    }

    public GhostFishingNet getRescuingGfn() {
        return rescuingGfn;
    }

    public void setRescuingGfn(GhostFishingNet rescuingGfn) {
        this.rescuingGfn = rescuingGfn;
    }
}
