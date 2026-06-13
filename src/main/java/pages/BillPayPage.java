package pages;

import com.microsoft.playwright.Page;

public class BillPayPage extends BasePage {

    public BillPayPage(Page page) {
        super(page);
    }

    public void navigate() {
        page.click("a[href*='billpay.htm']");
        page.waitForSelector("input[name='payee.name']");
    }

    public void payBill(String payeeName, String street, String city, String state, String zip, String phone, String accountNum, String verifyAccount, String amount, String fromAccountId) {
        page.fill("input[name='payee.name']", payeeName);
        page.fill("input[name='payee.address.street']", street);
        page.fill("input[name='payee.address.city']", city);
        page.fill("input[name='payee.address.state']", state);
        page.fill("input[name='payee.address.zipCode']", zip);
        page.fill("input[name='payee.phoneNumber']", phone);
        page.fill("input[name='payee.accountNumber']", accountNum);
        page.fill("input[name='verifyAccount']", verifyAccount);
        page.fill("input[name='amount']", amount);
        page.selectOption("select[name='fromAccountId']", fromAccountId);
        
        page.click("input[value='Send Payment']");
    }

    public String getConfirmationMessage() {
        page.waitForSelector("#rightPanel h1");
        return page.textContent("#rightPanel h1").trim();
    }
}
