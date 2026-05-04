package bankTest;
import com.microsoft.playwright.*;

public class OpenNewAc {
    public static void main(String[] args) {
    		Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
            Page page = browser.newPage();

            page.navigate("https://parabank.parasoft.com/parabank/index.htm");
            page.fill("input[name='username']", "gopikrisha"); 
            page.fill("input[name='password']", "Alien@#12");
            page.click("input[value='Log In']");

            page.click("a[href*='openaccount.htm']");
            page.waitForSelector("select[name='type']");

            page.selectOption("select[name='type']", "1");

            String fromAccount = page.locator("#accountTable td a").nth(0).textContent().trim();
            page.selectOption("select[name='fromAccountId']", fromAccount);

            page.click("input[value='Open New Account']");
            page.waitForSelector("#rightPanel h1");

            String confirmation = page.textContent("#rightPanel h1").trim();
            System.out.println("Open Account Confirmation: " + confirmation);

            page.click("a[href*='overview.htm']");
            page.waitForSelector("#accountTable");
            int accountCount = page.locator("#accountTable td a").count();
            System.out.println("Total accounts after opening new one: " + accountCount);

            browser.close();
        }
    }
