package page.factory;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4200/posts/all";
    private final WebDriver webDriver;

    public HomePage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public boolean isUrlLoaded() {
        WebDriverWait explicitWait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        try {
            // Wait until the current URL matches the expected PAGE_URL
            explicitWait.until(ExpectedConditions.urlToBe(PAGE_URL));
        } catch (TimeoutException ex) {
            // Return false if the URL does not load within the specified time
            return false;
        }
        // Return true if the expected URL is successfully loaded
        return true;
    }


    public void navigateTo(){
        this.webDriver.get(PAGE_URL);
    }
}