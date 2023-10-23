package example.myapp;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@RequestScoped
@Named

@Entity
public class NaturalPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String firstName;
    private String lastName;
    private String number;

//@Inject
   // public com.example.myapp.NaturalPerson(String firstName, String lastName, String number) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
