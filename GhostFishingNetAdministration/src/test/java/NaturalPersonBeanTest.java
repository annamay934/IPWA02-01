import static org.mockito.Mockito.*;

import example.myapp.beans.NaturalPersonBean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@ExtendWith(MockitoExtension.class)
public class NaturalPersonBeanTest {

    @Mock
    private EntityManager em;

    @InjectMocks
    private NaturalPersonBean naturalPersonBean;

    @Test
    public void testIsUsernameAvailable() {
        String testUsername = "testUser";
        TypedQuery<Long> mockQuery = mock(TypedQuery.class);

        when(em.createQuery(anyString(), eq(Long.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter(anyString(), any())).thenReturn(mockQuery);
        when(mockQuery.getSingleResult()).thenReturn(0L);

        assertTrue(naturalPersonBean.isUsernameAvailable(testUsername));
    }

    @Test
    public void testIsUsernameFound() {
        String testUsername = "existingUser";
        TypedQuery<Long> mockQuery = mock(TypedQuery.class);

        when(em.createQuery(anyString(), eq(Long.class))).thenReturn(mockQuery);
        when(mockQuery.setParameter(anyString(), any())).thenReturn(mockQuery);
        when(mockQuery.getSingleResult()).thenReturn(1L);

        assertTrue(naturalPersonBean.isUsernameFound(testUsername));
    }


}
