package bankTest;

import com.microsoft.playwright.*;

public class RequestLoan {
    public static void main(String[] args) {
       Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000) );
            Page page = browser.newPage();

            page.navigate("https://parabank.parasoft.com/parabank/index.htm");
            page.fill("input[name='username']", "gopikrisha"); 
            page.fill("input[name='password']", "Alien@#123");
            page.click("input[value='Log In']");
           
            page.click("a[href*='requestloan.htm']");
            page.waitForSelector("input[name='amount']");

            page.fill("input[name='amount']", "1000");
            page.fill("input[name='downPayment']", "100");
            String fromAccount = page.locator("#accountTable td a").nth(0).textContent().trim();
            page.selectOption("select[name='fromAccountId']", fromAccount);

            page.click("input[value='Apply Now']");
            page.waitForSelector("#rightPanel h1");

            String resultHeader = page.textContent("#rightPanel h1").trim();
            System.out.println("Result Header: " + resultHeader);

            String statusMessage = page.textContent("#rightPanel").trim();
            System.out.println("Loan Status Message: " + statusMessage);

            if (statusMessage.contains("Approved")) {
                System.out.println("Loan Approved");
            } else if (statusMessage.contains("Denied")) {
                System.out.println(" Loan Denied");
            } else {
                System.out.println(" Loan status not found");
            }

            browser.close();
        }
    }
}

