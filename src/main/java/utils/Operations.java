package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Operations {

    private static final int DEFAULT_TIMEOUT = 10;

    private Operations() {
        
    }

    public static void waitForVisibility(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

   
    public static void click(WebDriver driver, WebElement element) {
        waitForClickable(driver, element);
        element.click();
    }

    public static void type(WebDriver driver, WebElement element, String text) {
        waitForVisibility(driver, element);
        element.clear();
        element.sendKeys(text);
    }

    public static boolean isDisplayed(WebDriver driver, WebElement element) {
        try {
            waitForVisibility(driver, element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
