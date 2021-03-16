package org.acse.pageObjects.inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UomPage {

    WebDriver driver;

    @FindBy(xpath = "//input[@placeholder='Uom Code']")
    private WebElement uomCode;

    @FindBy(xpath = "//*[@id=\"root\"]/div[3]/div/div[3]/div[2]/main/div/div[5]/div[1]/div[2]/div[1]/div/div[2]/table/tbody[1]/tr/td[2]/div/input")
    private WebElement uomDescription;

    @FindBy(xpath = "//*[@id=\"root\"]/div[3]/div/div[3]/div[2]/main/div/div[5]/div[1]/div[2]/div[1]/div/div[2]/table/tbody[1]/tr/td[3]/div/div[2] //div[@class='flex flex-col align-center relative']/button")
    private WebElement baseUomLookUp;

    @FindBy(xpath = "//input[@placeholder ='baseUom']")
    private WebElement baseUomText;

    @FindBy(xpath = "//input[@placeholder='Conversion']")
    private WebElement conversion;

    @FindBy(xpath = "//*[@id=\"root\"]/div[3]/div/div[3]/div[2]/main/div/div[5]/div[1]/div[2]/div[1]/div/div[2]/table/tbody[1]/tr/td[5]/div/div[1]/div[1]/div[2]")
    private WebElement baseUomFlag;

    @FindBy(xpath = "//*[@id=\"root\"]/div[3]/div/div[3]/div[2]/main/div/div[5]/div[1]/div[2]/div[1]/div/div[2]/table/tbody[1]/tr/td[6]/div/div[1]/div[1]/div[2]")
    private WebElement activeFlag;



    public UomPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }




    public List<WebElement> allUomCodeList() {
        // TODO Auto-generated method stub
        return driver.findElements(By.xpath("//*[@id=\"root\"]/div[3]/div/div[3]/div[2]/main/div/div[5]/div[1]/div/div[1]/div/div[2]/table/tbody/tr/td[1]/div/div"));
    }

    public void sendUomCode (String uom) {
        uomCode.sendKeys(uom);
    }

    public void sendUomDescription(String description) {
        uomDescription.sendKeys(description);
    }

    public void baseUomLookUp() {
        baseUomLookUp.click();
    }

    public WebElement baseUomLookUp_WebElement() {
        return baseUomLookUp;
    }

    public void sendConversion(String conv) {
        conversion.sendKeys(conv);
    }

    public void clickBaseUomFlag() {
        baseUomFlag.click();
    }
    public void clickActiveFlag() {
        activeFlag.click();
    }

    public boolean validationForEmptyUomCode() {


        boolean textFound = false;
        try {
            driver.findElement(By.xpath("//div[contains(text(),'The field must be a valid selected value.')]"));
            textFound = true;
        } catch (Exception e) {
            textFound = false;
        }
        return textFound;

    }
    public boolean validationForEmptyConversion() {


        boolean textFound = false;
        try {
            driver.findElement(By.xpath("//div[contains(text(),'This is a mandatory field.')]"));
            textFound = true;
        } catch (Exception e) {
            textFound = false;
        }
        return textFound;

    }


    public void baseUomTextBox() {
        baseUomText.sendKeys(Keys.TAB,Keys.TAB);
    }

    public void conversionText() {
        conversion.sendKeys(Keys.TAB);
    }
    public boolean validationForMaxLength() {

        boolean textFound = false;
        try {
            driver.findElement(By.xpath("//div[text()='Max 12 digits and 8 decimals allowed']"));
            textFound = true;
        } catch (Exception e) {
            textFound = false;
        }
        return textFound;

    }

    public boolean validationForNegative() {

        boolean textFound = false;
        try {
            driver.findElement(By.xpath("//div[text()='Negative values are not allowed']"));
            textFound = true;
        } catch (Exception e) {
            textFound = false;
        }
        return textFound;

    }

    public boolean validationForMaxLengthUom() {


        boolean textFound = false;
        try {
            driver.findElement(By.xpath("//div[text()='Max length 20 chars ']"));
            textFound = true;
        } catch (Exception e) {
            textFound = false;
        }
        return textFound;

    }




    public boolean validationForDuplicateUomCode() {

        boolean textFound = false;
        try {
            driver.findElement(By.xpath("//div[text()='The  uom already exits.']"));
            textFound = true;
        } catch (Exception e) {
            textFound = false;
        }
        return textFound;

    }

    public boolean validationForDescriptionLimit() {


        boolean textFound = false;
        try {
            driver.findElement(By.xpath("//div[contains(text(),'Max length 50 chars ')]"));
            textFound = true;
        } catch (Exception e) {
            textFound = false;
        }
        return textFound;


    }




    public boolean validationForEmptyUom() {
        boolean textFound = false;
        try {
            driver.findElement(By.xpath("//div[contains(text(),'The  uom field is required.')]"));
            textFound = true;
        } catch (Exception e) {
            textFound = false;
        }
        return textFound;

    }




    public boolean getBaseUoM() {

        System.out.println("The BaseUom getAttribute value is :"+baseUomText.getAttribute("value"));
        return baseUomText.getAttribute("value").isEmpty();
    }




    public void sendUomCodeForSearch(String uomCode) {
        // TODO Auto-generated method stub
        driver.findElement(By.xpath("//input[@label='Uom']")).sendKeys(uomCode);
    }




    public void sendUomDescriptionForSearch(String description) {
        // TODO Auto-generated method stub
        driver.findElement(By.xpath("//input[@label='Uom Description']")).sendKeys(description);
    }




    public void clickEnterKeyOnUomCode() {
        // TODO Auto-generated method stub
        driver.findElement(By.xpath("//input[@label='Uom']")).sendKeys(Keys.ENTER);
    }




    public String uomCodeRow() {
        // TODO Auto-generated method stub
        return driver.findElement(By.xpath("//tbody[@class='p-datatable-tbody']/tr[1]/td[1]/div/div")).getText();
    }




    public String UomDescriptionRow() {
        return driver.findElement(By.xpath("//tbody[@class='p-datatable-tbody']/tr[1]/td[2]/div")).getText();
    }

    public void clearUomCode() {

        driver.findElement(By.xpath("//input[@label='Uom']")).sendKeys(Keys.chord(Keys.CONTROL,"a"),Keys.BACK_SPACE);
    }




    public void clearUomDescription() {

        driver.findElement(By.xpath("//input[@label='Uom Description']")).sendKeys(Keys.chord(Keys.CONTROL,"a"),Keys.BACK_SPACE);
    }
}
