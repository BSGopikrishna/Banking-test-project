package pages;

import com.microsoft.playwright.Page;

public class TransactionHistoryPage extends BasePage {

    public TransactionHistoryPage(Page page) {
        super(page);
    }

    public void navigateToAccountHistory(String accountId) {
        page.click("a[href*='activity.htm?id=" + accountId + "']");
        page.waitForSelector("#transactionTable");
    }

    public String getTransactionTableText() {
        page.waitForSelector("#transactionTable");
        return page.textContent("#transactionTable").trim();
    }
}
