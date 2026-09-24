import com.microsoft.playwright.*;
import com.microsoft.playwright.options.MouseButton;

import java.util.List;

public class CheckedBox {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();
            obj_page.navigate("https://the-internet.herokuapp.com/");
            System.out.println("The page is : "+obj_page.title());
            obj_page.waitForLoadState();
            Locator checkboxes = obj_page.locator("//a[@href='/checkboxes']");
            checkboxes.click();
            Thread.sleep(1000);

            List<Locator> checkboxs= obj_page.locator("#checkboxes > input").all();
            Thread.sleep(1000);
            if (checkboxs.get(1).isChecked())
                System.out.println("PASS : Checkbox 1 is checked");
            else
                System.out.println("FAIL : Checkbox 1 is not checked");

            obj_page.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
