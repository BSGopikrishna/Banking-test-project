package bankTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.microsoft.playwright.*;

public class FundTransferAcToAc {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        page.navigate("https://parabank.parasoft.com/parabank/register.htm");
        page.fill("input[name='customer.firstName']", "Gop");
        page.fill("input[name='customer.lastName']", "Gop");
        page.fill("input[name='customer.address.street']", "123 BGhall St");
        page.fill("input[name='customer.address.city']", "Rolla");
        page.fill("input[name='customer.address.state']", "AP");
        page.fill("input[name='customer.address.zipCode']", "100034");
        page.fill("input[name='customer.phoneNumber']", "1234567890");
        page.fill("input[name='customer.ssn']", "NA");
        page.fill("input[name='customer.username']", "gopikrisha");
        page.fill("input[name='customer.password']", "Alien@#123");
        page.fill("input[name='repeatedPassword']", "Alien@#123");
        page.click("input[value='Register']");

        page.click("a[href*='logout.htm']");
        page.fill("input[name='username']", "gopikrisha");
        page.fill("input[name='password']", "Alien@#123");
        page.click("input[value='Log In']");

        page.waitForSelector("#accountTable td a");

        int accountCount = page.locator("#accountTable td a").count();
        if (accountCount < 2) {
            page.click("a[href*='openaccount.htm']");
            page.waitForSelector("select[name='type']");

            System.out.println(page.innerHTML("select[name='type']"));

            page.selectOption("select[name='type']", "1");

            String existingAccount = page.locator("#accountTable td a").nth(0).textContent().trim();
            page.selectOption("select[name='fromAccountId']", existingAccount);

            page.click("input[value='Open New Account']");

            page.waitForSelector("#accountTable td a:nth-of-type(2)");
        }

        String fromAccount = page.locator("#accountTable td a").nth(0).textContent().trim();
        String toAccount = page.locator("#accountTable td a").nth(1).textContent().trim();
        System.out.println("From Account: " + fromAccount);
        System.out.println("To Account: " + toAccount);

        page.click("a[href*='transfer.htm']");
        page.waitForSelector("input[name='amount']");

        page.fill("input[name='amount']", "300");
        page.selectOption("select[name='fromAccountId']", fromAccount);
        page.selectOption("select[name='toAccountId']", toAccount);
        page.click("input[value='Transfer']");

        page.waitForSelector("#rightPanel h1");
        String confirmation = page.textContent("#rightPanel h1").trim();
        assertEquals("Transfer Complete!", confirmation);
        System.out.println("Transfer Complete: " + confirmation);

        browser.close();
        playwright.close();
    }
}
