package org.acse.testCases.inventory;

import org.acse.pageObjects.common.PostLoginPage;
import org.acse.pageObjects.inventory.UomPage;
import org.acse.resources.Base;
import org.acse.resources.Login;
import org.acse.utitities.ConvertionAndValidation;
import org.acse.utitities.ReadConfig;
import org.acse.utitities.XLUtils;
import org.apache.commons.mail.EmailException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.mail.internet.AddressException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TC_UoM extends Base {


    public WebDriver driver;
    PostLoginPage postLoginPage;
    ReadConfig readConfig = new ReadConfig();
    XLUtils xlutils = new XLUtils();
    //String readSheetName = "addUom";
    String readSheetName = "addUomDemo";
    public List<String> allUomList;
    UomPage uomPage;
    int numberOfRecords;
    int flag = 1;
    boolean result = true;
    boolean anyUomIsEmpty_Validation = false, anyUomIsDuplicate_Validation = false,
            anyUomIsSpecialChar_Validation = false, anyCodeIsAboveLimit_validation = false
            ,anyDescriptionIsAboveLimit_validation = false ;
    HashMap<String, Boolean> finalResultMap = new HashMap<String, Boolean>();
    String outPutExcellLocation = readConfig.getInventoryOutputPath();
    //String writeSheetName = "OutputUom";
    String writeSheetName = "inventory";

    String readTwoRows = "addTwoRows";
    String readSearch = "SearchByUom";
    String readReverse = "reverseValidation";
    ConvertionAndValidation convertionAndValidation = new ConvertionAndValidation();
    HashMap<String, ArrayList<String>> finalResultsMap_New = new HashMap<String, ArrayList<String>>();


    @BeforeSuite
    public void createExcelReportExcel() {
        System.out.println("Inside the Before suite");
        outPutExcellLocation =	reportGeneration(outPutExcellLocation, writeSheetName);
        System.out.println("outPutExcellLocation :"+outPutExcellLocation);

    }

    @BeforeTest
    public void BrowserSetUp() throws IOException {
        System.out.println("Inside the BrowserSetUp");
        this.driver = initializeDriver();
        Login login = new Login(driver);
        login.logInExtraPOS();

        System.out.println("BrowserSetUp is completed,Login Successfully");

    }

    @AfterTest
    public void tearDown() {

        driver.quit();
        logger.info("Driver has closed");
    }

    @AfterSuite
    public void sentEmail() throws EmailException, AddressException
    {
        attachEmailReport(outPutExcellLocation,"Inventory Excel Report");
    }

    @Test(priority = 0)
    public void selectALL_forUomCode() throws InterruptedException {

        uomPage = new UomPage(driver);
        postLoginPage = new PostLoginPage(driver);
        postLoginPage.clickInventory();
        logger.info("Clicked succefully Inventory");
        postLoginPage.clickInventoryMaintenance();
        logger.info("Clicked succefully Manintenace");
        postLoginPage.clickUoM();
        logger.info("Clicked succefully UoM");

        try {

            postLoginPage.clickSelectDropDown();

        } catch (Exception e) {
            js.executeScript("arguments[0].click();", postLoginPage.clickSelectDropDown_WebElement());
        }
        WebElement all;
        all = wait.until(ExpectedConditions.visibilityOfElementLocated(postLoginPage.allBy()));

        try {
            postLoginPage.clickAll();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", postLoginPage.clickAll_WebElement());
        }
        allUomList = new ArrayList<>();
        Thread.sleep(1000);
        List<WebElement> allUomCode = uomPage.allUomCodeList();
        logger.info("Size of the UomCode List -->> " + allUomCode.size());
        for (int i = 0; i < allUomCode.size(); i++) {
            allUomList.add(allUomCode.get(i).getText());
        }

    }


    @Test(dataProvider = "addUom" ,priority =1, enabled = true)
    public void createUomCode(String formName,String testCaseNumber,String scenario, String uomCode, String description, String baseUom,
                              String conversion, String baseUomFlag, String activeFlag,String expectedResult) throws InterruptedException, IOException {

        try
        {

            boolean UomEmpty = false, UomDuplicate= false, UomMaxLength= false,
                    descriptionMaxLength = false,  baseUomEmpty = false, conversionEmpty= false,
                    conversionMax = false, negativeConversion = false;
            logger.info(" :: UOMCode is  -> "+uomCode);
            try
            {
                postLoginPage.clickEditButton();

            }
            catch (Exception e)
            {
                js.executeScript("arguments[0].click();", postLoginPage.clickEditButton_WebElement());
            }

            postLoginPage.clickAddButton();


            uomPage.sendUomCode(uomCode);
            Thread.sleep(1000);
            uomPage.sendUomDescription(description);

            if(baseUomFlag.equalsIgnoreCase("active"))
            {
                uomPage.clickBaseUomFlag();
            }
            else
            {
                if(baseUom.isEmpty() == false)
                {
                    try
                    {
                        uomPage.baseUomLookUp();
                    }
                    catch (Exception e)
                    {
                        js.executeScript("arguments[0].click();", uomPage.baseUomLookUp_WebElement());
                    }
                    Thread.sleep(2000);
                    logger.info("Base UOm Is  ----->> "+baseUom);
                    convertionAndValidation.lookUpSearch("code", baseUom, driver, js);

                    if(conversion.isEmpty() == false)
                    {
                        uomPage.sendConversion(conversion);
                        uomPage.conversionText();
                    }
                    else
                    {
                        logger.info("Inside the Concersion is empty");
                        uomPage.conversionText();
                    }


                }
                else
                {
                    logger.info("BAse UOM is Empty, Will hits Tab Key twice");
                    uomPage.baseUomTextBox();

                }
                // Validation for BaseUom and conversion

                if(baseUom.isEmpty() || uomPage.getBaseUoM())
                {
                    try
                    {
                        logger.info("inside the baseUom empty validation");
                        baseUomEmpty = uomPage.validationForEmptyUomCode();
                        if(baseUomEmpty)
                        {
                            logger.info("There is a proper validation for Empty BaseUom");
                        }
                        else
                        {
                            result = false;
                            logger.info("There is Not a proper validation for Empty BaseUom");
                            Assert.assertTrue(false, "There is Not a proper validation for Empty BaseUom" );
                        }
                    }
                    catch (Exception e)
                    {
                        result = false;
                        logger.info("There is an Exception inside the BaseUom Empty validation");
                        e.printStackTrace();
                    }
                }

                if(conversion.isEmpty())
                {
                    try
                    {
                        logger.info("inside the Conversion empty validation");
                        conversionEmpty = uomPage.validationForEmptyConversion();
                        if(conversionEmpty)
                        {
                            logger.info("There is a proper validation for Empty Conversion");
                        }
                        else
                        {
                            result = false;
                            logger.info("There is Not a proper validation for Empty Conversion");
                            Assert.assertTrue(false, "There is Not a proper validation for Empty Conversion" );
                        }
                    }
                    catch (Exception e)
                    {
                        result = false;
                        logger.info("There is an Exception inside the Conversion Empty validation");
                        e.printStackTrace();
                    }
                }

                if((convertionAndValidation.numberOfIntegerPlaces(conversion) > 12 ||
                        convertionAndValidation.numberOfDecimalPlaces(conversion) >8 ))
                {
                    try
                    {
                        logger.info("Inside the Max integer and decimal validation");
                        conversionEmpty = uomPage.validationForMaxLength();
                        if(conversionEmpty)
                        {
                            logger.info("There is a proper validation msg for Max Length values");
                        }
                        else
                        {
                            result = false;
                            logger.info("There is No a proper validation msg for Max Length values");
                            Assert.assertTrue(false, "There is NO proper validation msg for Negative values");
                        }
                    }
                    catch (Exception e)
                    {
                        result = false;
                        logger.info("There is an Exeception in  Max Length validation");
                        Assert.assertTrue(false, "There is an Exeception in  Max Length validation");
                    }

                }

                if(conversion.isEmpty())conversion = "0";

                if(conversion.charAt(0) == '-' )
                {
                    try {
                        logger.info("Inside the negative validation..........");
                        negativeConversion = uomPage.validationForNegative();
                        if(negativeConversion)
                        {
                            logger.info("There is a proper validation msg for Negative values");
                        }
                        else
                        {
                            result = false;
                            logger.info("There is No a proper validation msg for Negative values");
                            Assert.assertTrue(false,
                                    "There is NO proper validation msg for Negative values");
                        }
                    }
                    catch (Exception e)
                    {
                        result = false;
                        logger.info("There is an Exeception in  Negative validation");
                        Assert.assertTrue(false, "There is an Exeception in  Negative validation");
                    }
                }

            }


            //Validation for UomCode and Description

            if(uomCode.isEmpty())
            {
                try
                {
                    logger.info("inside the UomCode empty validation");
                    UomEmpty = uomPage.validationForEmptyUom();
                    if(UomEmpty)
                    {
                        logger.info("There is a proper validation for Empty UomCode");
                    }
                    else
                    {
                        result = false;
                        logger.info("There is Not a proper validation for Empty UomCode");
                        Assert.assertTrue(false, "There is Not a proper validation for Empty UomCode" );
                    }
                }
                catch (Exception e)
                {
                    result = false;
                    logger.info("There is an Exception inside the UomCode Empty validation");
                    e.printStackTrace();
                }
            }


            if(uomCode.length() >20)
            {

                try {
                    logger.info("Inside the Max Length uomCode Validation");
                    UomMaxLength = uomPage.validationForMaxLengthUom();

                    if(UomMaxLength) {
                        logger.info("There is proper validation for Max: Length Uom field");
                    }
                    else {
                        logger.info("There is NO validation for Max: Length Uom field");
                        result = false;
                        Assert.assertTrue(false,"There is NO validation for Max: Length Uom field" );
                    }

                } catch (Exception e) {

                    result =false;
                    e.printStackTrace();
                    logger.info("There is a exception in Max: Lenth Uom Code");
                    Assert.assertTrue(false);
                }

            }

            // Duplicate Uom Code
            for(int i=0; i< allUomList.size(); i++)
            {

                if(allUomList.get(i).equalsIgnoreCase(uomCode))
                {
                    logger.info("Duplication Validation Block");
                    try
                    {
                        UomDuplicate = uomPage.validationForDuplicateUomCode();
                        if(UomDuplicate) {
                            logger.info(" Proper Validation for Duplicate UomCode");
                        }else {
                            logger.info("There is NO validation for Duplicate UomCode field");
                            result = false;
                            Assert.assertTrue(false,"There is NO validation for Duplicate UomCode field" );
                        }
                    }
                    catch (Exception e)
                    {

                        result =false;
                        e.printStackTrace();
                        logger.info("There is a exception in Duplicate Uom Code");
                        Assert.assertTrue(false);
                    }

                }
            }
            //Description Max Length

            if(description.length() > 50) {

                logger.info("Description Exceeds the Character Limit");
                try {

                    descriptionMaxLength = uomPage.validationForDescriptionLimit();
                    if(descriptionMaxLength) {
                        logger.info("Proper validation for Description Limit ");
                        Assert.assertTrue(true);
                    }else {
                        logger.info("No validation for Limit ");
                        result = false;
                        Assert.assertTrue(false, "No validation for Description Limit");
                    }

                } catch (Exception e) {

                    logger.info("Exception in Description Description Limit ");
                    result = false;
                    Assert.assertTrue(false, "Exception in Description Limit ");

                }
            }


            //Save Action

            try
            {
                postLoginPage.clickSave();
                if(UomEmpty == true || UomDuplicate == true || UomMaxLength == true ||
                        descriptionMaxLength == true ||  baseUomEmpty == true || conversionEmpty == true ||
                        conversionMax == true || negativeConversion == true)
                {
                    logger.info("Save Actions, but some errrors are present");
                    if (postLoginPage.errorMessage())
                    {
                        logger.info("proper msg for save action(can not save)");
                        postLoginPage.clickTICK();

                        postLoginPage.clickHomeButton();
                        logger.info("Clicked succefully HomeButton");
                        try {
                            postLoginPage.clickTICK();
                            logger.info("Clicked succefully Tick");
                        } catch (Exception e) {
                            js.executeScript("arguments[0].click();", postLoginPage.clickTICK_WebElement());
                        }
                        postLoginPage.clickUoM();
                        logger.info("Clicked succefully Uom");
                        Assert.assertTrue(true);
                    }
                    else {
                        logger.info("inside the error save");
                        postLoginPage.clickCANCELPopUp();//newly added
                        logger.info("Clicked succefully CANCELButton on the popup****");


                        postLoginPage.clickHomeButton();
                        logger.info("Clicked succefully HomeButton");
                        try {
                            postLoginPage.clickTICK();
                            logger.info("Clicked succefully Tick");
                        } catch (Exception e) {
                            js.executeScript("arguments[0].click();", postLoginPage.clickTICK_WebElement());
                        }
                        postLoginPage.clickUoM();
                        logger.info("Clicked succefully Uom");

                        result = false;
                        logger.info("There is no prper validation msg for save(can not save)");
                        Assert.assertTrue(false, "No Error Message validation for save action");
                    }

                }
                else
                {
                    logger.info("Save action else part( Proper save action)-------------------");

                    postLoginPage.clickTICK();
                    wait.until(ExpectedConditions.elementToBeClickable(postLoginPage.clickEditButton_WebElement()));
                    try {
                        postLoginPage.clickHomeButton();
                        logger.info("Clicked succefully HomeButton");
                    } catch (Exception e) {
                        js.executeScript("arguments[0].click();", postLoginPage.clickHomeButton_WebElement());
                    }


                    postLoginPage.clickUoM();
                    logger.info("Clicked succefully Uom");

                }


            }
            catch (Exception e)

            {

                result = false;
                e.printStackTrace();
                Assert.assertTrue(false, "Exception is happened inside saveCategory");
            }



        }// header try

        catch (Exception e)
        {

            result = false;
            e.printStackTrace();
            Assert.assertTrue(false,"Exception in createUomCode() Method");
        }
        finally
        {
            ArrayList<String> testDetails = new ArrayList<String>();
            testDetails.add(formName);
            testDetails.add(scenario);
            testDetails.add(expectedResult);
            testDetails.add(String.valueOf(result));
            finalResultsMap_New.put(testCaseNumber, testDetails);
            xlutils.printOutPutExcel(finalResultsMap_New, outPutExcellLocation, writeSheetName);

            result = true;
            logger.info("Inside the finally :: UOMCode is  -> "+uomCode);




        }

    }//createUomCode




    @DataProvider(name = "addUom")
    Object[] getData() throws IOException {

        String path = readConfig.getInventoryInputPath();
        int rowCount = XLUtils.getRowCount(path, readSheetName);
        int colCount = XLUtils.getCellCount(path, readSheetName, 0);
        numberOfRecords = rowCount - 1;
        System.out.println("Row count " + rowCount);
        System.out.println("col count " + colCount);
        String userdata[][] = new String[rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) {

            for (int j = 0; j < colCount; j++) {

                userdata[i - 1][j] = XLUtils.getCellData(path, readSheetName, i, j).trim();
            }
        }

        return userdata;
    }


}
