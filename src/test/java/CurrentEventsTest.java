import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class CurrentEventsTest {
    WebDriver driver;
    @BeforeMethod
    public void SetDriver(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }
    @Test
    public void OpenEventsId(){
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        WebElement pop_up = driver.findElement(By.id("vector-main-menu-dropdown-checkbox"));
        pop_up.click();
        WebElement current_events = driver.findElement(By.id("n-currentevents"));
        current_events.click();
    }
    @Test
    public void OpenEventsTitle(){
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        WebElement pop_up = driver.findElement(By.id("vector-main-menu-dropdown-checkbox"));
        pop_up.click();
        WebElement current_events = driver.findElement(By.xpath("//a[@title='Articles related to" +
                " current events']"));
        System.out.println(current_events);
        current_events.click();
    }
    @AfterMethod
    public void CloseDriver(){
        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            driver.close();
        }
    }
}
