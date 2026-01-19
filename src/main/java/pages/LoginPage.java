package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utils.Log;
import utils.Operations;  

public class LoginPage {
	
	private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
    @FindBy(how = How.ID, using = "usernameField")
    private WebElement username;

    @FindBy(how = How.ID, using = "passwordField")
    private WebElement password;

    @FindBy(how = How.XPATH, using = "//button[text()='Login']")
    private WebElement loginButton;
    
    @FindBy(how = How.XPATH, using = "//div[contains(text(), 'Invalid details')]")
    private WebElement invalidLoginPopUp;

    
    public void login(String user, String pass) {
    	
    	username.clear();
    	Log.info("Username entered.");  
    	username.sendKeys(user);
    	
    	password.clear();
      	Log.info("Password entered.");  
    	password.sendKeys(pass);
    	
    	Log.info("Clicked on the login button");
    	loginButton.click();
    }

    public boolean isInvalidLoginPopupDisplayed() {
        return Operations.isDisplayed(driver, invalidLoginPopUp);
    }
}
