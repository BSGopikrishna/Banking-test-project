package bankTest;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FundTransferTest extends BaseTest {

    @Test
    public void testFundTransferAndHistory() {
        loginPage.navigate();
        loginPage.login("gopikrisha", "Alien@#123");

        accountOverviewPage.navigate();
        
        int accountCount = accountOverviewPage.getAccountCount();
        if (accountCount < 2) {
            String existingAccount = accountOverviewPage.getAccountIdByIndex(0);
            openAccountPage.navigate();
            openAccountPage.openNewAccount("1", existingAccount); // 1 is checking
            accountOverviewPage.navigate();
        }

        String fromAccount = accountOverviewPage.getAccountIdByIndex(0);
        String toAccount = accountOverviewPage.getAccountIdByIndex(1);

        transferFundsPage.navigate();
        transferFundsPage.transferFunds("300", fromAccount, toAccount);

        String confirmation = transferFundsPage.getConfirmationMessage();
        assertEquals("Transfer Complete!", confirmation, "Fund transfer should be complete");

        // Validate history
        transactionHistoryPage.navigateToAccountHistory(fromAccount);
        String history = transactionHistoryPage.getTransactionTableText();
        assertTrue(history.contains("Funds Transfer Sent"), "Transfer out should be in history");

        transactionHistoryPage.navigateToAccountHistory(toAccount);
        String toHistory = transactionHistoryPage.getTransactionTableText();
        assertTrue(toHistory.contains("Funds Transfer Received"), "Transfer in should be in history");
    }
}
