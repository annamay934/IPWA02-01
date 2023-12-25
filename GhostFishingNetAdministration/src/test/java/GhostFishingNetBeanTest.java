import ejb.*;
import example.myapp.model.*;
import example.myapp.beans.*;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentCaptor;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GhostFishingNetBeanTest {

    @Mock
    private EntityManager em;
    @Mock
    private GhostFishingNetService ghostFishingNetService;
    @Mock
    private ReportingPersonService reportingPersonService;
    @Mock
    private RescuingPersonService rescuingPersonService;
    @Mock
    private NaturalPersonBean naturalPersonBean;
    @Mock
    private StatusService statusService;

    @InjectMocks
    private GhostFishingNetBean ghostFishingNetBean;

    @Test
    public void testSaveGhostFishingNet() {
        GhostFishingNet gfn = new GhostFishingNet();
        ReportingPerson reportingPerson = new ReportingPerson();
        when(naturalPersonBean.getLoggedInReportingPerson()).thenReturn(reportingPerson);

        ghostFishingNetBean.saveGhostFishingNet(gfn);

        // Überprüfen Sie, ob die richtigen Methoden auf den Services aufgerufen wurden
        verify(reportingPersonService).merge(any(ReportingPerson.class));
        verify(ghostFishingNetService).persist(any(GhostFishingNet.class));

        // Optional: Überprüfen Sie, ob bestimmte Eigenschaften richtig gesetzt wurden
        assertEquals(reportingPerson, gfn.getReportingPerson());
    }

    @Test
    public void testSaveGhostFishingNetAsLost() {
        // Erstellen einer ReportingPerson mit einer Telefonnummer
        ReportingPerson reportingPerson = new ReportingPerson();
        reportingPerson.setPhoneNumber("1234567890");

        // Mocken der NaturalPersonBean, um eine eingeloggte ReportingPerson zurückzugeben
        when(naturalPersonBean.getLoggedInPerson()).thenReturn(reportingPerson);

        // Erstellen und Initialisieren des GhostFishingNet Objekts
        GhostFishingNet gfn = new GhostFishingNet();
        gfn.setId(1L);

        Status status = new Status();
        gfn.setStatus(status);
        status.setGhostFishingNet(gfn);

        // Setzen des ausgewählten GhostFishingNet in der Bean
        ghostFishingNetBean.setSelectedGfn(gfn);

        // Aufrufen der zu testenden Methode
        ghostFishingNetBean.saveGhostFishingNetAsLost();

        // Verifizieren der Service-Aufrufe und Überprüfen der Assertions
        verify(statusService).merge(any(Status.class));
        assertTrue(status.getGfnStatusLost());
    }

    @Test
    public void testSaveGhostFishingNetAsRescued() {
        GhostFishingNet gfn = new GhostFishingNet();
        gfn.setId(1L);
        Status status = new Status();
        RescuingPerson rescuingPerson = new RescuingPerson();

        gfn.setStatus(status);
        when(naturalPersonBean.getLoggedInRescuingPerson()).thenReturn(rescuingPerson);

        ghostFishingNetBean.setSelectedGfn(gfn);
        ghostFishingNetBean.setGfnRescueDate(new Date());

        ghostFishingNetBean.saveGhostFishingNetAsRescued();

        verify(rescuingPersonService).merge(any(RescuingPerson.class));
        verify(ghostFishingNetService).merge(any(GhostFishingNet.class));
        assertTrue(status.getGfnStatusRescued());
        assertNotNull(gfn.getGfnRescueDate());
    }

    @Test
    public void testSaveGhostFishingNetAsRescuePending() {
        GhostFishingNet gfn = new GhostFishingNet();
        gfn.setId(1L);
        Status status = new Status();
        RescuingPerson rescuingPerson = new RescuingPerson();

        gfn.setStatus(status);
        when(naturalPersonBean.getLoggedInRescuingPerson()).thenReturn(rescuingPerson);

        ghostFishingNetBean.setSelectedGfn(gfn);

        ghostFishingNetBean.saveGhostFishingNetAsRescuePending();

        verify(rescuingPersonService).merge(any(RescuingPerson.class));
        verify(ghostFishingNetService).merge(any(GhostFishingNet.class));
        assertTrue(status.getGfnStatusRescuePending());
    }

    @Test
    public void testLoadSelectedGfnWhenIdIsNotNull() {
        Long gfnId = 1L;
        GhostFishingNet mockGfn = new GhostFishingNet();
        mockGfn.setId(gfnId);

        when(em.find(GhostFishingNet.class, gfnId)).thenReturn(mockGfn);
        ghostFishingNetBean.setSelectedGfnId(gfnId);

        ghostFishingNetBean.loadSelectedGfn();

        verify(em).find(GhostFishingNet.class, gfnId);
        assertEquals(gfnId, ghostFishingNetBean.getSelectedGfn().getId());
    }

    @Test
    public void testLoadSelectedGfnWhenIdIsNull() {
        ghostFishingNetBean.setSelectedGfnId(null);

        ghostFishingNetBean.loadSelectedGfn();

        verify(em, never()).find(eq(GhostFishingNet.class), anyLong());
        GhostFishingNet result = ghostFishingNetBean.getSelectedGfn();
        assertNotNull(result);
        assertNull(result.getId());
        assertNotNull(result.getStatus());
        assertTrue(result.getStatus().getGfnStatusReported());
    }
}
