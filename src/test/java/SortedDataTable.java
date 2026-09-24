import com.microsoft.playwright.*;
import java.util.*;

public class SortedDataTable {
    public static void main(String[] args) {
        try(Playwright obj_playwright = Playwright.create())
        {
            Browser obj_browser = obj_playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();
            obj_page.navigate("https://the-internet.herokuapp.com/");
            System.out.println("The page is : "+obj_page.title());
            obj_page.waitForLoadState();

            Locator contextMenu = obj_page.locator("//a[@href='/tables']");
            contextMenu.click();
            System.out.println("Clicked on Sortable Data Tables Menu");
            obj_page.waitForLoadState();

            Locator table1 = obj_page.locator("#table1 > tbody > tr");
            int rowCount = table1.count();

            List<String> lastNames = new ArrayList<>();
            System.out.println(rowCount);
            for(int i=0;i<rowCount;i++){
                String lastName = table1.nth(i).locator("td").first().textContent();
                lastNames.add(lastName);
            }

            String lastName = table1.nth(0).locator("td").first().textContent();
            Collections.sort(lastNames, (a, b) -> a.compareTo(b));
            System.out.println("Before clicking on last name column : "+lastName+" "+lastNames.get(0));

            obj_page.locator("//table[@id='table1']/thead/tr/th/span[text()='Last Name']").click();
            obj_page.waitForTimeout(1000);
            lastName = table1.nth(0).locator("td").first().textContent();

            System.out.println("After clicking on last name column : "+lastName+" "+lastNames.get(0));

            if(lastName.equals(lastNames.get(0)))
                System.out.println("PASS : Table is sorted");
            else
                System.out.println("FAIL : Table is not sorted");

            obj_page.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
