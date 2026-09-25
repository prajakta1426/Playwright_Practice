import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class KeyPresses {
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

        page.locator("//a[@href='/key_presses']").click();
        page.waitForLoadState();

        Locator input = page.locator("#target");
        Locator result = page.locator("#result");
        input.click();

        page.keyboard().press("A");
        assertThat(result).hasText("You entered: A");

        page.keyboard().press("Delete");
        assertThat(result).hasText("You entered: DELETE");

        page.keyboard().press("`");
        assertThat(result).hasText("You entered: BACK_QUOTE");

    }

    @AfterMethod
    public void teardown(){
        page.close();
    }
}
