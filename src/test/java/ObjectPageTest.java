import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class ObjectPageTest {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeTest
    public void SetDriver(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }
    @Test
    @Parameters({"loops"})
    public void OpenEventsId(int loops) throws InterruptedException {
        int loop = 1;

        MainPage main_page = new MainPage(driver);
        main_page.Open();

        String main_page_handle = driver.getWindowHandle();
        while (loop <= loops) {

            main_page.OpenRandPage();
            wait.until(numberOfWindowsToBe(2));

            boolean switch_success = false;
            for (String window_handle : driver.getWindowHandles()) {
                if(!main_page_handle.contentEquals(window_handle)) {
                    driver.switchTo().window(window_handle);
                    switch_success = true;
                    break;
                }
            }
            if (switch_success) {
                NewPage rand_page = new NewPage(driver);
                rand_page.GetAttributes(loop);
                rand_page.GetReferences();
                rand_page.SwitchBack(main_page_handle);
            }
            loop++;
        }
    }
    @AfterTest
    public void CloseDriver() throws InterruptedException{
        Thread.sleep(2000);
        driver.quit();
    }
}
