/*pure test scripts
		 * Login will inharit all the property of the basetest and it will act as the parent class
		 * need to create the object of the loginPage class use of constructor in the login page and whenever we will create the object of the class
		it will force the user to pass the webdriver instance as the arguement*/

package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);  
        loginPage.login("Iamnehalsingh01@gmail.com", "Neh@l321");
        System.out.println("You have successfully logged in : "+driver.getTitle());
       // Assert.assertEquals(driver.getTitle(), "Jobseeker's Login");
    }
}
