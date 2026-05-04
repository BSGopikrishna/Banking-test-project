package bankTest;

import com.microsoft.playwright.*;

public class TranscationHistoryValidation {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://parabank.parasoft.com/parabank/index.htm");
        page.fill("input[name='username']", "gopikrisha");
        page.fill("input[name='password']", "Alien@#123");
        page.click("input[value='Log In']");

        page.waitForSelector("#accountTable td a");

        String accountNumber = page.locator("#accountTable td a").nth(0).textContent().trim();
        System.out.println("Checking transactions for account: " + accountNumber);

        page.click("a[href*='findtrans.htm']");
        page.waitForSelector("input[name='accountId']");

        page.fill("input[name='accountId']", accountNumber);
        page.fill("input[name='amount']", "300"); 
        page.click("input[value='Find Transactions']");
        page.waitForSelector("#transactionTable");

        int amountRows = page.locator("#transactionTable tbody tr").count();
        System.out.println("Transactions found by amount: " + amountRows);
        for (int i = 0; i < amountRows; i++) {
            System.out.println("Row " + (i + 1) + ": " +
                page.locator("#transactionTable tbody tr").nth(i).textContent().trim());
        }

        page.click("a[href*='findtrans.htm']"); 
        page.waitForSelector("input[name='transactionId']");

        page.fill("input[name='transactionId']", "12345"); 
        page.click("input[value='Find Transactions']");
        page.waitForSelector("#transactionTable");

        int idRows = page.locator("#transactionTable tbody tr").count();
        System.out.println("Transactions found by ID: " + idRows);
        for (int i = 0; i < idRows; i++) {
            System.out.println("Row " + (i + 1) + ": " +
                page.locator("#transactionTable tbody tr").nth(i).textContent().trim());
        }

        browser.close();
        playwright.close();
    }
}
