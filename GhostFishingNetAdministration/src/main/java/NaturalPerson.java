import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@RequestScoped
@Named

public class NaturalPerson {
    private String firstName ="anna";
    private String lastName = "may";
    private String number = "0174-3927152";

//@Inject
   // public NaturalPerson(String firstName, String lastName, String number) {
    //    this.firstName = firstName;
    //     this.lastName = lastName;
    //     this.number = number;
    // }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
