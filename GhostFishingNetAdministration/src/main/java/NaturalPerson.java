import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@ManagedBean
@RequestScoped
@Named

public class NaturalPerson {
    private String firstName;
    private String lastName;
    private String number;

    public NaturalPerson(){
    }

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

    public void setNumber(String telefonnummer) {
        this.number = telefonnummer;
    }
}
