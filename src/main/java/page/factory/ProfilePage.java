package page.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage{
    public static final String PAGE_URL_WITHOUT_USER_ID = "http://training.skillo-bg.com:4200/users/";
    private final WebDriver webDriver;

    @FindBy(tagName = "h2")
    private WebElement usernameText;

    public ProfilePage(WebDriver webDriver){
        this.webDriver = webDriver;
        // Initialization - to gather the elements and let us work with them.
        // With Page Factory, we need to tell the constructor to load elements. To use them.
        PageFactory.initElements(webDriver, this);
    }

    public boolean isUrlLoaded(int userId){
        WebDriverWait explicitWait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        try {
            // Wait until the URL matches the expected one with the given userId
            explicitWait.until(ExpectedConditions.urlToBe(this.PAGE_URL_WITHOUT_USER_ID + userId));
        } catch (TimeoutException ex) {
            // If timeout occurs, return false indicating that the URL did not load in time
            return false;
        }
        return true; // Return true if the expected URL is loaded successfully
    }

    public boolean isUsernameAsExpected(String username){
        WebDriverWait profileUsernameWait = new WebDriverWait(this.webDriver, Duration.ofSeconds(3));
        try {
            // Wait until the expected username text is present in the usernameText element
            profileUsernameWait.until(ExpectedConditions.textToBePresentInElement(this.usernameText, username));
        } catch (TimeoutException exception) {
            // Log a message if the expected username is not found within the given time
            System.out.println("Username text is not present on profile page. Inner exception: " + exception);
            return false; // Return false if the expected username is not found
        }
        return true; // Return true if the username is as expected
    }

}