import com.microsoft.playwright.*;

public class OpenPAge {
    public static void main(String[] args) {
        try(Playwright obj_playwright= Playwright.create()){
            Browser obj_broswer= obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context= obj_broswer.newContext();
            Page obj_page= obj_context.newPage();

            obj_page.navigate("https://www.google.com");
            System.out.println("PAge title is :"+obj_page.title());
            obj_page.waitForLoadState();

            Locator obj_input=obj_page.locator("//textarea[@id='ti6dpd']");
            //obj_input.fill("Hello");
//            Thread.sleep(2000);
//            obj_page.keyboard().type(" World");
//            Thread.sleep(2000);
//            obj_page.keyboard().press("Enter");
//            Thread.sleep(2000);
            obj_page.keyboard().insertText("who is  adele");
            Thread.sleep(2000);
            obj_page.keyboard().press("Enter");
            Thread.sleep(5000);
            obj_page.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
