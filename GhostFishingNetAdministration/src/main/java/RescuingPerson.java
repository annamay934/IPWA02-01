public class RescuingPerson extends NaturalPerson {
    GhostFishingNet gemeldetesNetz;

    public RescuingPerson(String firstName, String lastName, String number) {
        super.setFirstName(firstName);
        super.setLastName(lastName);
        super.setNumber(number);
    }

    public void melden(GhostFishingNet netz) {
        this.gemeldetesNetz = netz;
    }
}
