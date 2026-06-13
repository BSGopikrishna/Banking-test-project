package pages;

import com.microsoft.playwright.Page;

public class RegisterPage extends BasePage {

    public RegisterPage(Page page) {
        super(page);
    }

    public void navigate() {
        page.navigate("https://parabank.parasoft.com/parabank/register.htm");
    }

    public void fillRegistrationForm(String firstName, String lastName, String street, String city, String state, String zip, String phone, String ssn, String username, String password) {
        page.fill("input[name='customer.firstName']", firstName);
        page.fill("input[name='customer.lastName']", lastName);
        page.fill("input[name='customer.address.street']", street);
        page.fill("input[name='customer.address.city']", city);
        page.fill("input[name='customer.address.state']", state);
        page.fill("input[name='customer.address.zipCode']", zip);
        page.fill("input[name='customer.phoneNumber']", phone);
        page.fill("input[name='customer.ssn']", ssn);
        page.fill("input[name='customer.username']", username);
        page.fill("input[name='customer.password']", password);
        page.fill("input[name='repeatedPassword']", password);
    }

    public void submitRegistration() {
        page.click("input[value='Register']");
    }

    public String getWelcomeMessage() {
        page.waitForSelector("#rightPanel h1.title");
        return page.textContent("#rightPanel h1.title");
    }
    
    public String getErrorMessage() {
        page.waitForSelector("span.error");
        return page.textContent("span.error");
    }
}
