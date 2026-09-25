import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FormAuthentication {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    @BeforeMethod
    public void setUp(){
        playwright=Playwright.create();
        browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext=browser.newContext();
        page=browserContext.newPage();
    }

    @Test
    public void handleAlert(){

        page.navigate("https://the-internet.herokuapp.com/");
        page.waitForLoadState();

        page.locator("//a[@href='/login']").click();
        page.waitForLoadState();


    }

    @AfterMethod
    public void teardown(){
        page.close();
    }
}
