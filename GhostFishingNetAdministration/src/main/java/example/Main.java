package example;

import example.myapp.NaturalPerson;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String [] args){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("GhostFishingNetProject");
        EntityManager em = factory.createEntityManager();

        NaturalPerson p = new NaturalPerson();
        p.setFirstName("Anna");
        p.setNumber("01742536217");
        p.setLastName("May");

        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
