import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Alert {
   Playwright playwright;
   Browser browser;
   BrowserContext browserContext;
   Page page;

   @BeforeMethod
   public void setUp(){
      playwright=Playwright.create();
      browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
      browserContext=browser.newContext();
      page=browserContext.newPage();
   }

   @Test
   public void handleAlert(){

      page.navigate("https://the-internet.herokuapp.com/");
      page.waitForLoadState();

      page.locator("//a[@href='/javascript_alerts']").click();
      page.waitForLoadState();

      Locator jsAlert = page.locator("//button[text()='Click for JS Alert']");
      Locator jsConfirm = page.locator("//button[text()='Click for JS Confirm']");
      Locator jsPrompt = page.locator("//button[text()='Click for JS Prompt']");
      Locator result = page.locator("#result");

      assertThat(jsAlert).isEnabled();
      assertThat(jsConfirm).isEnabled();
      assertThat(jsPrompt).isEnabled();

      AtomicInteger confirmCount =new AtomicInteger(0);
      AtomicInteger promptCount =new AtomicInteger(0);

      page.onDialog(dialog -> {
         System.out.println("Dialog type "+dialog.type()+" message : "+dialog.message());
         switch (dialog.type()) {
            case "alert":
               dialog.accept();
               break;

            case  "confirm":
               if(confirmCount.getAndIncrement()==0){
                  dialog.accept();
               }
               else {
                  dialog.dismiss();
               }
               break;

            case "prompt":
               if(promptCount.getAndIncrement()==0){
                  dialog.accept("This is Playwright");
               }
               else {
                  dialog.dismiss();
               }
               break;

            default:
               dialog.dismiss();
               break;

         }
      });

      jsAlert.click();
      assertThat(result).hasText("You successfully clicked an alert");

      jsConfirm.click();
      assertThat(result).hasText("You clicked: Ok");

      jsConfirm.click();
      assertThat(result).hasText("You clicked: Cancel");

      jsPrompt.click();
      assertThat(result).hasText("You entered: This is Playwright");

      jsPrompt.click();
      assertThat(result).hasText("You entered: null");

   }

   @AfterMethod
   public void teardown(){
      page.close();
   }
}
