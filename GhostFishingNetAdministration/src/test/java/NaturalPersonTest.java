import example.myapp.model.NaturalPerson;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mindrot.jbcrypt.BCrypt;

public class NaturalPersonTest {

    @Test
    public void testPasswordHashing() {
        NaturalPerson person = new NaturalPerson();
        String rawPassword = "TestPassword123";
        String hashedPassword = person.hashPassword(rawPassword);

        assertNotNull(hashedPassword);
        assertNotEquals(rawPassword, hashedPassword);
        assertTrue(BCrypt.checkpw(rawPassword, hashedPassword));
    }

    @Test
    public void testPasswordVerification() {
        NaturalPerson person = new NaturalPerson();
        String rawPassword = "TestPassword123";
        String hashedPassword = person.hashPassword(rawPassword);

        assertTrue(person.checkPasswordLogIn(rawPassword, hashedPassword));
        assertFalse(person.checkPasswordLogIn("WrongPassword", hashedPassword));
    }
}