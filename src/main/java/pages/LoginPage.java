package pages;

import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    public LoginPage(Page page) {
        super(page);
    }

    public void navigate() {
        page.navigate("https://parabank.parasoft.com/parabank/index.htm");
    }

    public void login(String username, String password) {
        page.fill("input[name='username']", username);
        page.fill("input[name='password']", password);
        page.click("input[value='Log In']");
    }

    public void logout() {
        page.click("a[href*='logout.htm']");
    }
}
