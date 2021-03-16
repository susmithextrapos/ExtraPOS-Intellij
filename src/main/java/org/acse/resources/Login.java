package org.acse.resources;

import org.acse.pageObjects.common.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Login extends Base {

    public static WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
    }


    public void  logInExtraPOS()
    {

        logger.info("driver has been initialized in userManagement class");

        WebDriverWait wait = new WebDriverWait(driver, 20);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickLogInButton();

        String homPageValidationText = loginPage.homePageSuccessValidationText(); // in the homepage there should
        // contain the text "Welcome to
        // ExtraPOS!", this text is here
        // validating as Success.
        boolean homePage = driver.getPageSource().contains(homPageValidationText);

        if (homePage) {

            logger.info("Login Successfully");
            Assert.assertTrue(true);
        } else {

            logger.info("login failed");
            // getScreenShotPath("loginTest", driver);
            logger.info("Screenshot Taken");
            Assert.assertTrue(false);
        }




    }
}
