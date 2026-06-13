package bankTest;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BillPaymentTest extends BaseTest {

    @Test
    public void testBillPaymentAndHistoryValidation() {
        loginPage.navigate();
        loginPage.login("gopikrisha", "Alien@#123");

        accountOverviewPage.navigate();
        String fromAccount = accountOverviewPage.getAccountIdByIndex(0);

        billPayPage.navigate();
        billPayPage.payBill(
                "Electric Company", "456 Utility St", "Bengaluru", "KA", "560002",
                "9876543211", "98765", "98765", "250", fromAccount
        );

        String confirmation = billPayPage.getConfirmationMessage();
        assertTrue(confirmation.contains("Bill Payment Complete"), "Bill payment confirmation should be shown");

        transactionHistoryPage.navigateToAccountHistory(fromAccount);
        String history = transactionHistoryPage.getTransactionTableText();
        
        assertTrue(history.contains("250"), "Bill payment amount not found in history");
        assertTrue(history.contains("Bill Payment"), "Bill payment entry not found in history");
    }
}
