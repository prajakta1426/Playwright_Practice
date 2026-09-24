import com.microsoft.playwright.*;

import java.util.List;

public class BasicAuth {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();
            obj_page.navigate("https://the-internet.herokuapp.com");
            System.out.println("The page is : "+obj_page.title());
            obj_page.waitForLoadState();
            Locator basicAuth = obj_page.locator("//a[@href='/basic_auth']");
            Thread.sleep(1000);
            obj_page.onceDialog(dialog -> {
                dialog.accept("admin");
                dialog.accept("pass");
            });
            basicAuth.click();

            obj_page.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
