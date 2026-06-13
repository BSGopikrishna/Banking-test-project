package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.*;

public class BaseTest {
    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;

    // Page Objects
    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    protected AccountOverviewPage accountOverviewPage;
    protected OpenAccountPage openAccountPage;
    protected TransferFundsPage transferFundsPage;
    protected BillPayPage billPayPage;
    protected RequestLoanPage requestLoanPage;
    protected UpdateProfilePage updateProfilePage;
    protected TransactionHistoryPage transactionHistoryPage;

    @BeforeAll
    public static void setUpClass() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setSlowMo(500));
    }

    @BeforeEach
    public void setUp() {
        context = browser.newContext();
        page = context.newPage();

        // Initialize Page Objects
        registerPage = new RegisterPage(page);
        loginPage = new LoginPage(page);
        accountOverviewPage = new AccountOverviewPage(page);
        openAccountPage = new OpenAccountPage(page);
        transferFundsPage = new TransferFundsPage(page);
        billPayPage = new BillPayPage(page);
        requestLoanPage = new RequestLoanPage(page);
        updateProfilePage = new UpdateProfilePage(page);
        transactionHistoryPage = new TransactionHistoryPage(page);
    }

    @AfterEach
    public void tearDown() {
        if (context != null) {
            context.close();
        }
    }

    @AfterAll
    public static void tearDownClass() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
