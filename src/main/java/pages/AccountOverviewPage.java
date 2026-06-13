package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

public class AccountOverviewPage extends BasePage {

    public AccountOverviewPage(Page page) {
        super(page);
    }

    public void navigate() {
        page.click("a[href*='overview.htm']");
    }

    public int getAccountCount() {
        page.waitForSelector("#accountTable td a");
        return page.locator("#accountTable td a").count();
    }

    public String getAccountIdByIndex(int index) {
        page.waitForSelector("#accountTable td a");
        return page.locator("#accountTable td a").nth(index).textContent().trim();
    }
    
    public void clickAccountByIndex(int index) {
        page.waitForSelector("#accountTable td a");
        page.locator("#accountTable td a").nth(index).click();
    }
}
