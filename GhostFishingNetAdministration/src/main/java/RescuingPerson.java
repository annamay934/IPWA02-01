public class RescuingPerson extends NaturalPerson {
    GhostFishingNet rescuedGhostFishingNet;

    public RescuingPerson(String firstName, String lastName, String number) {
        super.setFirstName(firstName);
        super.setLastName(lastName);
        super.setNumber(number);
    }

    public void rescuing(GhostFishingNet rescuedNet) {
        this.rescuedGhostFishingNet = rescuedNet;
    }
}
