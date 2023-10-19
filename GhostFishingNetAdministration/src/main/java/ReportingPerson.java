public class ReportingPerson extends NaturalPerson {
    GhostFishingNet reportedGhostFishingNet;
    public ReportingPerson(String firstName, String lastName, String number) {
        super.setFirstName(firstName);
        super.setLastName(lastName);
        super.setNumber(number);
    }

    public void reporting(GhostFishingNet reportedNet) {
        this.reportedGhostFishingNet = reportedNet;
    }
}

