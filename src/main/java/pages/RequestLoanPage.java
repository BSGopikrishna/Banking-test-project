package pages;

import com.microsoft.playwright.Page;

public class RequestLoanPage extends BasePage {

    public RequestLoanPage(Page page) {
        super(page);
    }

    public void navigate() {
        page.click("a[href*='requestloan.htm']");
        page.waitForSelector("input[name='amount']");
    }

    public void requestLoan(String amount, String downPayment, String fromAccountId) {
        page.fill("input[name='amount']", amount);
        page.fill("input[name='downPayment']", downPayment);
        page.selectOption("select[name='fromAccountId']", fromAccountId);
        page.click("input[value='Apply Now']");
    }

    public String getConfirmationMessage() {
        page.waitForSelector("#rightPanel h1.title");
        return page.textContent("#rightPanel h1.title");
    }
    
    public String getLoanStatus() {
        page.waitForSelector("#loanStatus");
        return page.textContent("#loanStatus");
    }
}
