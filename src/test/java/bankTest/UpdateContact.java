package bankTest;
import com.microsoft.playwright.*;

public class UpdateContact {
    public static void main(String[] args) {
        	Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
            Page page = browser.newPage();

            page.navigate("https://parabank.parasoft.com/parabank/index.htm");
            page.fill("input[name='username']", "gopikrisha"); 
            page.fill("input[name='password']", "Alien@#123");
            page.click("input[value='Log In']");

            page.click("a[href*='updateprofile.htm']");
            page.waitForSelector("input[name='customer.address.street']");

            page.fill("input[name='customer.address.street']", "789 New Street");
            page.fill("input[name='customer.address.city']", "Hyderabad");
            page.fill("input[name='customer.address.state']", "TS");
            page.fill("input[name='customer.address.zipCode']", "500001");
            page.fill("input[name='customer.phoneNumber']", "9123456789");

            page.click("input[value='Update Profile']");
            page.waitForSelector("#rightPanel h1");

            String confirmation = page.textContent("#rightPanel h1").trim();
            System.out.println("Update Confirmation: " + confirmation);

            page.click("a[href*='updateprofile.htm']");
            page.waitForSelector("input[name='customer.address.street']");
            String updatedStreet = page.inputValue("input[name='customer.address.street']");
            System.out.println("Updated Street: " + updatedStreet);

            browser.close();
        }
    }
}
