package example;

import example.myapp.model.GhostFishingNet;
import example.myapp.model.ReportingPerson;
import example.myapp.model.RescuingPerson;
import example.myapp.model.Status;


import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory factory = null;
        EntityManager em = null;

        try {
            factory = Persistence.createEntityManagerFactory("GhostFishingNetProject");
            em = factory.createEntityManager();

            //new reporting Person with ghost fishing net 1, 2, 3
            ReportingPerson rep = new ReportingPerson();
            rep.setFirstName("Erika");
            rep.setPhoneNumber("01742536678");
            rep.setLastName("Musterfrau");

            GhostFishingNet g1 = new GhostFishingNet();
            g1.setGfnLocationLatitude(25.678);
            g1.setGfnLocationLongitude(124.456);
            g1.calculatedGfnEstimatedSize(3,1);
            Status status1 = new Status();
            status1.setGfnStatusReported(true);


            //new rescuing person with ghost fishing net g2
            RescuingPerson res = new RescuingPerson();
            res.setFirstName("Maximilian");
            res.setPhoneNumber("01742536678");
            res.setLastName("Mustermann");
            res.setUserName("Max");
            res.setPassword("Mustermann");

            GhostFishingNet g2 = new GhostFishingNet();
            g2.setGfnLocationLatitude(26.345);
            g2.setGfnLocationLongitude(45.123);
            g2.calculatedGfnEstimatedSize(1,2);
            Status status2 = new Status();
            status2.setGfnStatusRescued(true);

            res.setRescuingGfn(g2);

            GhostFishingNet g3 = new GhostFishingNet();
            g3.setGfnLocationLatitude(34.567);
            g3.setGfnLocationLongitude(132.789);
            g3.calculatedGfnEstimatedSize(2,4);
            Status status3 = new Status();
            status3.setGfnStatusReported(true);


            em.getTransaction().begin();
            em.persist(rep);
            em.persist(res);
            em.persist(g1);
            em.persist(g2);
            em.persist(g3);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (factory != null) {
                factory.close();
            }
        }
    }
}
