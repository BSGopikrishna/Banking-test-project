package bankTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
public class AccountCreatePassMisMatch {
	public static void main(String[]args)throws InterruptedException {
		Playwright playwright = Playwright.create();
		BrowserType browserType =playwright.chromium();
		Browser browser =browserType.launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chromium").setSlowMo(2000));
		BrowserContext browserContext=browser.newContext();
		Page page =browserContext.newPage();
		page.navigate("https://parabank.parasoft.com/parabank/register.htm");
		page.click("input[value='Register']");
		page.fill("input[name='customer.firstName']","Gop");
		page.fill("input[name='customer.lastName']", "Gop");
		page.fill("input[name='customer.address.street']", "123 BGhall St");
		page.fill("input[name='customer.address.city']", "Rolla");
		page.fill("input[name='customer.address.state']", "AP");
		page.fill("input[name='customer.address.zipCode']", "100034");
		page.fill("input[name='customer.phoneNumber']", "1234567890");
		page.fill("input[name='customer.ssn']", "NA");
		page.fill("input[name='customer.username']", "gopikrisha");
		page.fill("input[name='customer.password']", "Alien@#12");
		page.fill("input[name='repeatedPassword']", "Alien@#12");
		page.click("input[value='Register']");
		Thread.sleep(2000);
		
		
		System.out.println(page.title());
		browser.close();
		
		
	}

}
