import com.microsoft.playwright.*;

import java.security.Key;

public class DynamicLoading {
    public static void main(String[] args) {
    try(Playwright obj_playwright = Playwright.create())
    {
        Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext obj_context = obj_browser.newContext();
        Page obj_page = obj_context.newPage();
        obj_page.navigate("https://the-internet.herokuapp.com/");
        System.out.println("The page is : "+obj_page.title());
        obj_page.waitForLoadState();

        Locator dynamicLoading = obj_page.locator("//a[@href='/dynamic_loading']");
        dynamicLoading.click();
        System.out.println("Clicked on Dynamic Loading");
        obj_page.waitForLoadState();

        Locator hiddenElement = obj_page.locator("//a[@href='/dynamic_loading/1']");
        hiddenElement.click();
        System.out.println("Clicked on 'Example 1: Element on page that is hidden'");

        Locator startBtn = obj_page.locator("#start > button");
        if (startBtn.isVisible())
            System.out.println("PASS : Start button is visible");
        else
            System.out.println("FAIL : Start button is NOT visible");

        String ishiddenElement = obj_page.locator("#finish").getAttribute("style");
        if(ishiddenElement.equals("display:none"))
            System.out.println("PASS : Element is hidden");
        else
            System.out.println("FAIL : Element is not hidden");
        startBtn.click();
        System.out.println("Clicked on start button");
        obj_page.waitForTimeout(5000);

        String hiddenMessage = obj_page.locator("#finish").innerText();
        if (hiddenMessage.equals("Hello World!"))
            System.out.println("PASS : Hello world message is displayed");
        else
            System.out.println("FAIL : Incorrect message is displayed");

        obj_page.goBack();
        obj_page.waitForLoadState();
        obj_page.waitForTimeout(2000);

        //Rendering element
        Locator renderElement = obj_page.locator("//a[@href='/dynamic_loading/2']");
        renderElement.click();
        System.out.println("Clicked on 'Example 2: Element rendered after the fact'");
        obj_page.waitForLoadState();

        if(!hiddenElement.isVisible())
            System.out.println("PASS : Element is hidden");
        else
            System.out.println("FAIL : Element is not hidden");

        if (startBtn.isVisible())
            System.out.println("PASS : Start button is visible");
        else
            System.out.println("FAIL : Start button is NOT visible");
        startBtn.click();

        if (hiddenMessage.equals("Hello World!"))
            System.out.println("PASS : Hello world message is displayed");
        else
            System.out.println("FAIL : Incorrect message is displayed");

        obj_page.close();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
}
