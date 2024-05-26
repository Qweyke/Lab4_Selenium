import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class RandLinkTest {
    static WebDriver driver;
    static WebDriverWait wait;
    static Random random = new Random();
    @BeforeTest
    public void SetDriver(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }
    @Test
    @Parameters({"loops"})
    public void OpenEventsId(int loops) throws InterruptedException{
        //int loops = 5;
        int loop = 1;
        String main_page = driver.getWindowHandle();

        WebElement content = driver.findElement(By.id("mp-upper"));
        List<WebElement> all_links = content.findElements(By.tagName("a"));

        Actions open_link = new Actions(driver);
        while (loop <= loops) {
            int random_link = random.nextInt(all_links.size());
            open_link.keyDown(Keys.CONTROL).click(all_links.get(random_link)).keyUp(Keys.CONTROL).build().perform();

            wait.until(numberOfWindowsToBe(2));

            boolean switch_success = false;
            for (String window_handle : driver.getWindowHandles()) {
                if(!main_page.contentEquals(window_handle)) {
                    driver.switchTo().window(window_handle);
                    switch_success = true;
                    break;
                }
            }
            if (switch_success) {
                System.out.println();
                System.out.printf("Page №%x: ", loop);
                System.out.println(driver.getTitle());
                System.out.println(driver.getCurrentUrl());

                try {
                    WebElement refs = driver.findElement(By.className("references"));
                    if (refs != null) {
                        System.out.println("References:");
                        List<WebElement> all_refs = refs.findElements(By.tagName("a"));
                        for (int i = 0; i < all_refs.size(); i++) {
                            if (i > 3) {break;}
                            String href = all_refs.get(i).getAttribute("href");
                            if (href != null && !href.isEmpty()) {
                                System.out.println(href);
                            }
                        }
                        System.out.println();
                    }
                }
                catch (org.openqa.selenium.NoSuchElementException e) {
                    System.out.println("There are no references on this page");
                }
                Thread.sleep(2000);
                driver.close();
                driver.switchTo().window(main_page);
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
