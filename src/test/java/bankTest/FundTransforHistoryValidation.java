package bankTest;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FundTransforHistoryValidation {

    @Test
    public void testFindTransactionsByAmountAndId() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000)
            );
            Page page = browser.newPage();

            // Step 1: Login with an existing user (from previous test)
            page.navigate("https://parabank.parasoft.com/parabank/index.htm");
            page.fill("input[name='username']", "gopikrisha"); // replace with your dynamic username
            page.fill("input[name='password']", "Alien@#12323");
            page.click("input[value='Log In']");
          //  page.waitForNavigation();

            // Step 2: Capture account number
            page.click("a[href*='overview.htm']");
         //   page.waitForNavigation();
            String accountNumber = page.locator("#accountTable td a").nth(0).textContent().trim();

            // Step 3: Navigate to Find Transactions
            page.click("a[href*='findtrans.htm']");
            page.waitForSelector("input[name='accountId']");

            // Step 4: Find by Amount
            page.fill("input[name='accountId']", accountNumber);
            page.fill("input[name='amount']", "500"); // amount used in transfer
            page.click("input[value='Find Transactions']");
            page.waitForSelector("#transactionTable");
            String transactionsByAmount = page.textContent("#transactionTable").trim();
            assertTrue(transactionsByAmount.contains("500"), "Transaction by amount not found");

            // Step 5: Find by Transaction ID
            page.click("a[href*='findtrans.htm']"); // reload Find Transactions page
            page.waitForSelector("input[name='transactionId']");

            // Example: use a known transaction ID (capture from previous transfer)
            String transactionId = page.locator("#transactionTable tbody tr td").nth(0).textContent().trim();
            page.fill("input[name='transactionId']", transactionId);
            page.click("input[value='Find Transactions']");
            page.waitForSelector("#transactionTable");
            String transactionsById = page.textContent("#transactionTable").trim();
            assertTrue(transactionsById.contains(transactionId), "Transaction by ID not found");

            browser.close();
        }
    }
}
