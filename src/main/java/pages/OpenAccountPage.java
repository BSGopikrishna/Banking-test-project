package pages;

import com.microsoft.playwright.Page;

public class OpenAccountPage extends BasePage {

    public OpenAccountPage(Page page) {
        super(page);
    }

    public void navigate() {
        page.click("a[href*='openaccount.htm']");
        page.waitForSelector("select[name='type']");
    }

    public void openNewAccount(String accountType, String fromAccountId) {
        page.selectOption("select[name='type']", accountType);
        page.selectOption("select[name='fromAccountId']", fromAccountId);
        page.click("input[value='Open New Account']");
    }

    public String getConfirmationMessage() {
        page.waitForSelector("#rightPanel h1.title");
        return page.textContent("#rightPanel h1.title");
    }
    
    public String getNewAccountId() {
        page.waitForSelector("#newAccountId");
        return page.textContent("#newAccountId");
    }
}
