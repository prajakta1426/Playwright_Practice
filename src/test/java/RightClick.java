import com.microsoft.playwright.*;
import com.microsoft.playwright.options.MouseButton;

public class RightClick {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();
            obj_page.navigate("https://the-internet.herokuapp.com/");
            System.out.println("The page is : "+obj_page.title());
            obj_page.waitForLoadState();

            Locator contextMenu = obj_page.locator("//a[@href='/context_menu']");
            contextMenu.click();
            System.out.println("Clicked on Context Menu");
            obj_page.waitForLoadState();

            Locator contextBox = obj_page.locator("#hot-spot");
            System.out.println(contextBox.isVisible());
            contextBox.click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));

            Thread.sleep(2000);
            obj_page.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
