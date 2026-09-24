import com.microsoft.playwright.*;

public class PlayWrightWithDragAndDrop {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();
            obj_page.navigate("https://the-internet.herokuapp.com/");
            System.out.println("The page is : "+obj_page.title());
            obj_page.waitForLoadState();

            Locator dragAndDrop = obj_page.locator("//a[text()='Drag and Drop']");
            dragAndDrop.click();
            System.out.println("Clicked on Drag and Drop");
            obj_page.waitForLoadState();

            Locator columnA = obj_page.locator("#column-a");
            Locator columnB = obj_page.locator("#column-b");

            columnA.dragTo(columnB);

            Locator divSection = obj_page.locator("//div[@id='columns']/div[1]/header");
            if(divSection.textContent().equals("B"))
                System.out.println("PASS : B is dragged to A place.");
            else
                System.out.println("FAIL : B DragTo did not work.");


            obj_page.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
