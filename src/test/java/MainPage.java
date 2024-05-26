import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Random;

public class MainPage {
    WebDriver driver;
    WebElement content;
    List<WebElement> all_links;

    public MainPage(WebDriver external_driver){
        driver = external_driver;
    }
    public void Open(){

        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        content = driver.findElement(By.id("mp-upper"));
        all_links = content.findElements(By.tagName("a"));
    }
    public void OpenRandPage(){
        Actions open_link = new Actions(driver);
        int random_link = new Random().nextInt(all_links.size());
        open_link.keyDown(Keys.CONTROL).click(all_links.get(random_link)).keyUp(Keys.CONTROL).build().perform();
    }
}
