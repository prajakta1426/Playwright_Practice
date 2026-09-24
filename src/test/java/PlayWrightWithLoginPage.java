import com.microsoft.playwright.*;

public class PlayWrightWithLoginPage {
    public static void main(String[] args) {
        try(Playwright obj_playwright= Playwright.create()){
            Browser obj_broswer= obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context= obj_broswer.newContext();
            Page obj_page= obj_context.newPage();

            obj_page.navigate("C:/Users/CCST/Desktop/Playwright%20Material/PlaywrightMaterial/login.html");
            System.out.println("PAge title is :"+obj_page.title());
            obj_page.waitForLoadState();

            Locator obj_username= obj_page.locator("#username");
            obj_username.fill("ABC");
            Thread.sleep(1000);
            Locator obj_password=obj_page.getByPlaceholder("Enter password");
            obj_password.fill("xyz");
            Thread.sleep(1000);
            Locator obj_signin= obj_page.getByText("Sign In");
            Page obj_newPage = obj_context.waitForPage(()->{
                        obj_signin.click();
                    });
            System.out.println(obj_newPage.title());
            if (obj_newPage.title().contains("Student Performance Report"))
                System.out.println("PASS");
            else
                System.out.println("SHIYT");
            Thread.sleep(1000);
            obj_page.waitForLoadState();

            System.out.println(obj_newPage.url());


            obj_page.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
