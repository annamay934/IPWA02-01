package example;

import example.myapp.model.GhostFishingNet;
import example.myapp.model.ReportingPerson;
import example.myapp.model.RescuingPerson;
import example.myapp.model.Status;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String [] args){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("GhostFishingNetProject");
        EntityManager em = factory.createEntityManager();

        //new reporting Person with ghost fishing net 1, 2, 3
        ReportingPerson rep = new ReportingPerson();
        rep.setFirstName("Erika");
        rep.setPhoneNumber("01742536678");
        rep.setLastName("Musterfrau");

        GhostFishingNet g1 = new GhostFishingNet();
        g1.setGfnLocationLatitude(25.678);
        g1.setGfnLocationLongitude(124.456);
        g1.calculatedGfnEstimatedSize(3,1);
        g1.addStatus();

        g1.getStatus();
        g1.setGfnReportDate();

        rep.addGhostFishingNet(g1);

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
        g2.setGfnStatus(status2);
        g2.setGfnReportDate();
        g2.setGfnRescueDate();

        rep.addGhostFishingNet(g2);
        res.setRescuingGfn(g2);

        GhostFishingNet g3 = new GhostFishingNet();
        g3.setGfnLocationLatitude(34.567);
        g3.setGfnLocationLongitude(132.789);
        g3.calculatedGfnEstimatedSize(2,4);
        Status status3 = new Status();
        status3.setGfnStatusReported(true);
        g3.setGfnStatus(status3);
        g3.setGfnReportDate();

        rep.addGhostFishingNet(g3);

        em.getTransaction().begin();
        em.persist(rep);
        em.persist(res);
        em.persist(g1);
        em.persist(g2);
        em.persist(g3);
        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
