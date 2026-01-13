package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

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
    
    public void login(String user,String Pass) {
    	username.clear();
    	username.sendKeys(user);
    	password.clear();
    	password.sendKeys(Pass);
    	loginButton.click();
    	
    }

}
