import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NewPage {
    WebDriver driver;

    public NewPage(WebDriver external_driver){
        driver = external_driver;
    }
    public void GetAttributes(int loop){
        System.out.println();
        System.out.printf("Page №%x: ", loop);
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
    }
    public void GetReferences(){
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
    }
    public void SwitchBack(String main_page) throws InterruptedException{
        Thread.sleep(2000);
        driver.close();
        driver.switchTo().window(main_page);
    }
}
