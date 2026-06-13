package bankTest;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountCreationTest extends BaseTest {

    @Test
    public void testValidAccountCreation() {
        registerPage.navigate();
        
        // Use a unique username to avoid conflicts on repeated runs, or simply test the flow
        String uniqueUser = "gopiUser" + System.currentTimeMillis();
        
        registerPage.fillRegistrationForm(
                "Gop", "Gop", "123 BGhall St", "Rolla", "AP", "100034",
                "1234567890", "NA", uniqueUser, "Alien@#12"
        );
        registerPage.submitRegistration();

        String welcomeMsg = registerPage.getWelcomeMessage();
        assertTrue(welcomeMsg.contains("Welcome"), "Welcome message should be displayed");
    }

    @Test
    public void testPasswordMismatch() {
        registerPage.navigate();
        
        registerPage.fillRegistrationForm(
                "Gop", "Gop", "123 BGhall St", "Rolla", "AP", "100034",
                "1234567890", "NA", "gopikrisha", "Alien@#12"
        );
        // Overwrite the repeated password to simulate mismatch
        page.fill("input[name='repeatedPassword']", "WrongPass");
        registerPage.submitRegistration();

        String errorMsg = registerPage.getErrorMessage();
        assertEquals("Passwords did not match.", errorMsg, "Password mismatch error should be shown");
    }
}
