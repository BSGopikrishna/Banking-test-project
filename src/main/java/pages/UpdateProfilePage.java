package pages;

import com.microsoft.playwright.Page;

public class UpdateProfilePage extends BasePage {

    public UpdateProfilePage(Page page) {
        super(page);
    }

    public void navigate() {
        page.click("a[href*='updateprofile.htm']");
        page.waitForSelector("input[name='customer.firstName']");
    }

    public void updateContactInfo(String firstName, String lastName, String street, String city, String state, String zip, String phone) {
        // Wait for fields to be populated
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        page.fill("input[name='customer.firstName']", firstName);
        page.fill("input[name='customer.lastName']", lastName);
        page.fill("input[name='customer.address.street']", street);
        page.fill("input[name='customer.address.city']", city);
        page.fill("input[name='customer.address.state']", state);
        page.fill("input[name='customer.address.zipCode']", zip);
        page.fill("input[name='customer.phoneNumber']", phone);
        page.click("input[value='Update Profile']");
    }

    public String getConfirmationMessage() {
        page.waitForSelector("#rightPanel h1.title");
        return page.textContent("#rightPanel h1.title");
    }
}
