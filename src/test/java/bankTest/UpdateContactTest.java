package bankTest;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateContactTest extends BaseTest {

    @Test
    public void testUpdateContactInfo() {
        loginPage.navigate();
        loginPage.login("gopikrisha", "Alien@#123");

        updateProfilePage.navigate();
        updateProfilePage.updateContactInfo(
                "GopUpdated", "Gop", "123 New St", "Rolla", "AP", "100034", "1112223333"
        );

        String confirmation = updateProfilePage.getConfirmationMessage();
        assertEquals("Profile Updated", confirmation, "Profile update confirmation should be shown");
    }
}
