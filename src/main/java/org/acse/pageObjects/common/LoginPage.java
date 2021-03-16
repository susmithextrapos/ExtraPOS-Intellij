package org.acse.pageObjects.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    @CacheLookup
    @FindBy(xpath = "//input[@id='userName']")
    private WebElement txtuserName;

    @CacheLookup
    @FindBy(xpath = "//input[@type='password']")
    private WebElement txtPassword;

    @CacheLookup
    @FindBy(xpath = "//button[@id='loginButton']")
    private WebElement submitButton;

    @CacheLookup
    @FindBy(xpath = "//*[@id='root']/div[3]/div/div[3]/div[2]/main/div/div/div[2]/h1")
    private WebElement homePageValidationTxt;


    public LoginPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Action Methods

    public void setUserName(String uname) {

        txtuserName.sendKeys(uname);
    }

    public void setPassword(String pswrd) {

        txtPassword.sendKeys(pswrd);
    }


    public void clickLogInButton() {

        submitButton.click();
    }

    public String homePageSuccessValidationText() {

        return homePageValidationTxt.getText().trim();
    }


}// class

