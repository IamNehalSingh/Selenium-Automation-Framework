package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.Log;
import utils.LoginDataProvider;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() {

        Log.info("Starting testValidLogin");

        LoginPage loginPage = new LoginPage(driver);

        test.info("Entering username and password");
        loginPage.login("Iamnehalsingh01@gmail.com", "Neh@l321");

        test.info("Login submitted");
        Log.info("Logged in successfully");

        System.out.println("Page Title: " + driver.getTitle());
    }
    
    @Test
    public void testInvalidLogin() {

        LoginPage loginPage = new LoginPage(driver);

        test.info("Attempting invalid login");
        loginPage.login("abc@gmail.com", "123");

        Assert.assertTrue(
            loginPage.isInvalidLoginPopupDisplayed(),
            "Invalid login popup should be displayed"
        );
    }
    
   
    @Test(dataProvider = "Logindata", dataProviderClass = LoginDataProvider.class)
    public void testDataLogin(String username, String password) {

        Log.info("Starting testDataLogin");

        LoginPage loginPage = new LoginPage(driver);

        test.info("Entering username and password");
        loginPage.login(username, password);

        Log.info("Login attempted with user: " + username);
        System.out.println("Page Title: " + driver.getTitle());
    }
}
