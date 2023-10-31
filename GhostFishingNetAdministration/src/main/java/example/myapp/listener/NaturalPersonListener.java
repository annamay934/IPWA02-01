package example.myapp.listener;

import example.myapp.model.NaturalPerson;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

public class NaturalPersonListener {

    @PostPersist
    public void reportAdd (NaturalPerson p){
        System.out.println("Message: Natural Person with id "+p.getId()+" was added");
    }

    @PostUpdate
    public void reportUpdate (NaturalPerson p){
        System.out.println("Message: Natural Person with id "+p.getId()+" was updated");
    }

    @PostRemove
    public void reportDelete (NaturalPerson p){
        System.out.println("Message: Natural Person with id "+p.getId()+" was deleted");
    }
}
