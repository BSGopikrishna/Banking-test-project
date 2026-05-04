package bankTest;
import com.microsoft.playwright.*;
//import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BillPaymentValidation {

 //   @Test
    public void testBillPaymentAndHistoryValidation() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            page.navigate("https://parabank.parasoft.com/parabank/index.htm");
            page.fill("input[name='username']", "gopikrisha"); 
            page.fill("input[name='password']", "Alien@#123");
            page.click("input[value='Log In']");

            page.click("a[href*='billpay.htm']");
            page.waitForSelector("input[name='payee.name']");

            page.fill("input[name='payee.name']", "Electric Company");
            page.fill("input[name='payee.address.street']", "456 Utility St");
            page.fill("input[name='payee.address.city']", "Bengaluru");
            page.fill("input[name='payee.address.state']", "KA");
            page.fill("input[name='payee.address.zipCode']", "560002");
            page.fill("input[name='payee.phoneNumber']", "9876543211");
            page.fill("input[name='payee.accountNumber']", "98765");
            page.fill("input[name='verifyAccount']", "98765");
            page.fill("input[name='amount']", "250");

            String fromAccount = page.locator("#accountTable td a").nth(0).textContent().trim();
            page.selectOption("select[name='fromAccountId']", fromAccount);

            page.click("input[value='Send Payment']");
            page.waitForSelector("#rightPanel h1");

            String confirmation = page.textContent("#rightPanel h1").trim();
            assertTrue(confirmation.contains("Bill Payment Complete"));

            page.click("a[href*='activity.htm?id=" + fromAccount + "']");
            page.waitForSelector("#transactionTable");

            String transactionTable = page.textContent("#transactionTable").trim();
            assertTrue(transactionTable.contains("250"), "Bill payment amount not found in history");
            assertTrue(transactionTable.contains("Bill Payment"), "Bill payment entry not found in history");

            browser.close();
        }
    }
}

