package bankTest;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OpenNewAccountTest extends BaseTest {

    @Test
    public void testOpenNewAccount() {
        loginPage.navigate();
        loginPage.login("gopikrisha", "Alien@#123");

        accountOverviewPage.navigate();
        String existingAccount = accountOverviewPage.getAccountIdByIndex(0);

        openAccountPage.navigate();
        openAccountPage.openNewAccount("1", existingAccount); // 1 = Checking

        String confirmation = openAccountPage.getConfirmationMessage();
        assertEquals("Account Opened!", confirmation, "Account opening confirmation should be shown");

        String newAccountId = openAccountPage.getNewAccountId();
        assertNotNull(newAccountId, "New account ID should be generated");
    }
}
