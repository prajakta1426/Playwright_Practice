import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Hover {
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

        page.locator("//a[@href='/hovers']").click();
        page.waitForLoadState();

        Locator hoverImage1 = page.locator(".figure").nth(0);
        Locator hoverText1 = hoverImage1.locator(".figcaption");

        Locator hoverImage2 = page.locator(".figure").nth(1);
        Locator hoverText2 = hoverImage2.locator(".figcaption");

        Locator hoverImage3 = page.locator(".figure").nth(2);
        Locator hoverText3 = hoverImage3.locator(".figcaption");

        hoverImage1.hover();
        assertThat(hoverText1).containsText("name: user1");

        hoverImage1.hover();
        assertThat(hoverText2).containsText("name: user2");

        hoverImage1.hover();
        assertThat(hoverText3).containsText("name: user3");

    }

    @AfterMethod
    public void teardown(){
        page.close();
    }
}
