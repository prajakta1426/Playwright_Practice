import com.microsoft.playwright.*;

public class DropAndDownList {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();
            obj_page.navigate("https://the-internet.herokuapp.com/");
            System.out.println("The page is : "+obj_page.title());
            obj_page.waitForLoadState();

            Locator dropdown = obj_page.locator("//a[text()='Dropdown']");
            dropdown.click();
            System.out.println("Clicked on Dropdown");
            obj_page.waitForLoadState();

            Locator dropDownList=obj_page.locator("#dropdown");
            dropDownList.selectOption("Option 1");
            Thread.sleep(2000);

            String dropdownOption = obj_page.getByText("Option 1").getAttribute("selected");
            if(dropdownOption.equals("selected"))
                System.out.println("PASS : Option 1 is selected");
            else
                System.out.println("FAIL : Option 1 is not selected");

            obj_page.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
