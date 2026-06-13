package bankTest;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransactionHistoryTest extends BaseTest {

    @Test
    public void testTransactionHistory() {
        loginPage.navigate();
        loginPage.login("gopikrisha", "Alien@#123");

        accountOverviewPage.navigate();
        String accountId = accountOverviewPage.getAccountIdByIndex(0);

        transactionHistoryPage.navigateToAccountHistory(accountId);
        String historyTableText = transactionHistoryPage.getTransactionTableText();
        
        assertNotNull(historyTableText, "Transaction history table should not be null");
        // We can assert specific data if we know it exists, but validating the table loads is a good first step
    }
}
