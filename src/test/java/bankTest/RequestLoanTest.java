package bankTest;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestLoanTest extends BaseTest {

    @Test
    public void testRequestLoan() {
        loginPage.navigate();
        loginPage.login("gopikrisha", "Alien@#123");

        accountOverviewPage.navigate();
        String existingAccount = accountOverviewPage.getAccountIdByIndex(0);

        requestLoanPage.navigate();
        requestLoanPage.requestLoan("1000", "100", existingAccount);

        String confirmation = requestLoanPage.getConfirmationMessage();
        assertEquals("Loan Request Processed", confirmation, "Loan request confirmation should be shown");
        
        String status = requestLoanPage.getLoanStatus();
        assertEquals("Approved", status, "Loan status should be Approved");
    }
}
