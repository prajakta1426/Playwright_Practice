import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PopUp {
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

        page.locator("//a[@href='/entry_ad']").click();
        page.waitForLoadState();

        Page newpage = page.waitForPopup(()->{
            page.getByText("click here").click();
        });

        Locator newPopUp = newpage.locator(".modal > .modal-title");
        assertThat(newPopUp).isVisible();

    }

    @AfterMethod
    public void teardown(){
        page.close();
    }
}
