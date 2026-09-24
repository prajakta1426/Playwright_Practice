import com.microsoft.playwright.*;

public class DynamicControls {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();
            obj_page.navigate("https://the-internet.herokuapp.com/");
            System.out.println("The page is : "+obj_page.title());
            obj_page.waitForLoadState();

            Locator dynamiccontrol = obj_page.locator("//a[@href='/dynamic_controls']");
            dynamiccontrol.click();
            System.out.println("Clicked on Dynamic Controls");
            obj_page.waitForLoadState();

            Locator checkbox= obj_page.locator("#checkbox-example > div > input");
            if(checkbox.isVisible()){
                System.out.println("Checkbox is visible");
            }
            else
                System.out.println("Checkbox is not visible");

            checkbox.check();

            Thread.sleep(2000);
            Locator removeBtn=obj_page.locator("//button[text()='Remove']");
            removeBtn.click();
            obj_page.waitForLoadState();

            Locator addBtn=obj_page.locator("//button[text()='Add']");
            String Btnmessage=obj_page.locator("#message").innerText();
            if(addBtn.isVisible()&&Btnmessage.equals("It's gone!") ){
                System.out.println("PASS : remove btn is deleted");
            }
            else{
                System.out.println("Fail : remove btn is not deleted");
            }

            addBtn.click();
            String addBtnMessage=obj_page.locator("#message").innerText();
            if(!addBtn.isVisible()&&addBtnMessage.equals("It's back!") ){
                System.out.println("PASS : remove btn is now visible ");
            }
            else{
                System.out.println("Fail : remove btn is not visible ");
            }

            //ENABLE/DISABLE BUTTON
            Locator inputLocator = obj_page.locator("#input-example > input");
            if(inputLocator.isDisabled())
            {
                System.out.println("PASS : Input box is disabled before clicking on enable button");
            }
            else
                System.out.println("FAIL : Input box is enabled before clicking on enable button");

            Locator enableButton = obj_page.locator("#input-example > button");
            if(enableButton.isVisible())
                System.out.println("PASS : Enable button is visible");
            else
                System.out.println("FAIL : Enable button is not visible");
            enableButton.click();
            obj_page.waitForTimeout(5000);
            if(inputLocator.isEnabled())
            {
                System.out.println("PASS : Input box is enabled before clicking on enable button");
            }
            else
                System.out.println("FAIL : Input box is disabled before clicking on enable button");


            obj_page.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
