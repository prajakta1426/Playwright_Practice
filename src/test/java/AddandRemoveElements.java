import com.microsoft.playwright.*;

public class AddandRemoveElements {
    public static void main(String[] args) {
        try(Playwright obj_playwright= Playwright.create()){
            Browser obj_broswer= obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context= obj_broswer.newContext();
            Page obj_page= obj_context.newPage();

            obj_page.navigate("https://the-internet.herokuapp.com/");
            System.out.println("PAge title is :"+obj_page.title());
            obj_page.waitForLoadState();

            Locator addremove = obj_page.locator("//a[@href='/add_remove_elements/']");
            addremove.click();

            Thread.sleep(2000);

            if(obj_page.url().contains("add_remove_elements"))
                System.out.println("PASS : Page has opened");
            else
                System.out.println("FAIL : Page has not opened yet");

            Locator clickonadd=obj_page.getByText("Add Element");
            clickonadd.click();

            Thread.sleep(2000);

            Locator deleteBtn=obj_page.getByText("Delete");
            if(deleteBtn.isVisible()){
                System.out.println("PASS : Delete Button is Visible");
            }
            else{
                System.out.println("FAIL : Delete Button is not Visible");
            }
            deleteBtn.click();
            System.out.println("To check delete btn disappeared ");
            if(!deleteBtn.isVisible()){
                System.out.println("PASS : Delete Button is not Visible");
            }
            else{
                System.out.println("FAIL : Delete Button is Visible");
            }


            obj_page.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
