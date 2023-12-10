package example.myapp.model;

import example.myapp.beans.ReportingPersonBean;
import example.myapp.listener.NaturalPersonListener;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import jakarta.persistence.*;

import java.util.List;

@RequestScoped
@Named
@Entity
@EntityListeners(NaturalPersonListener.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")

public class NaturalPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
