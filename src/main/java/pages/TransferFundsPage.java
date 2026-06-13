package pages;

import com.microsoft.playwright.Page;

public class TransferFundsPage extends BasePage {

    public TransferFundsPage(Page page) {
        super(page);
    }

    public void navigate() {
        page.click("a[href*='transfer.htm']");
        page.waitForSelector("input[name='amount']");
    }

    public void transferFunds(String amount, String fromAccountId, String toAccountId) {
        page.fill("input[name='amount']", amount);
        page.selectOption("select[name='fromAccountId']", fromAccountId);
        page.selectOption("select[name='toAccountId']", toAccountId);
        page.click("input[value='Transfer']");
    }

    public String getConfirmationMessage() {
        page.waitForSelector("#rightPanel h1.title");
        return page.textContent("#rightPanel h1.title");
    }
}
