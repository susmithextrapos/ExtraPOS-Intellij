package org.acse.pageObjects.common;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PostLoginPage {


    WebDriver driver;
    @FindBy(xpath = "//span[text()='Approval Settings']")
    private WebElement approvalSettings;
    //@FindBy(xpath = "//span[text()='Maintenance']") susmith
    @FindBy(xpath = "//a[text()='Maintenance']")
    private WebElement maintenance;
    //@FindBy(xpath = "//span[text()='Approval Role Code']") susmith

    @FindBy(xpath = "//div[@class='text-sm text-black ']/div[2]/div/div/a[1] //span[text()='Approvals']")
    private WebElement approvals;


    @FindBy(xpath = "//a[text() ='Approval Role Code']")
    private WebElement approvalRoleCode;
    @FindBy(xpath = "//span[text()='Approval Hierarchy']")
    private WebElement approvalHierarchy;
    @FindBy(xpath = "//span[text()='Approval Code']")
    private WebElement approvalCode;
    @FindBy(xpath = "//span[text()='Reports']")
    private WebElement reports;
    @FindBy(xpath = "//span[text()='List Approval Structure for item and branch']")
    private WebElement listApproval;


    // added by Susmith
    //these all are for inventory
    @FindBy(xpath = "//*[text()='Inventory']")
    private WebElement inventory;
    @FindBy(xpath = "//*[@id=\"pr_id_3_content\"]/div/ul/li[1]/div[1]/a")
    private WebElement inventoryMaintenance;

    @CacheLookup
    @FindBy(xpath = "//*[text()='Security']")
    private WebElement security;
    @CacheLookup
    @FindBy(xpath = "//*[@id=\"pr_id_3_content\"]/div/ul/li[1]/div/a")
    private WebElement userManagemet;

    @CacheLookup
    @FindBy (xpath = "//span[contains(text(),'System Administration')]")
    private WebElement systemAdministration;

    @CacheLookup
    @FindBy(xpath = "//*[@id=\"pr_id_3_content\"]/div/ul/li[3]/div/a")
    private WebElement branch;

    @CacheLookup
    @FindBy(xpath = "//ul[@class='p-submenu-list']/li[1]/div/a")
    private WebElement ItemMaster;

    @CacheLookup
    @FindBy(xpath = "//*[@id=\"pr_id_3_content\"]/div/ul/li[1]/div[2]/ul/li[2]/div")
    private WebElement category;

    @FindBy(xpath = "//*[@id=\"pr_id_3_content\"]/div/ul/li[1]/div[2]/ul/li[3]/div/a")
    private WebElement subCategory;

    @FindBy(xpath = "//*[@id=\"pr_id_3_content\"]/div/ul/li[1]/div[2]/ul/li[4]/div/a")
    private WebElement brand;

    @FindBy(xpath = "//*[@id=\"pr_id_3_content\"]/div/ul/li[1]/div[2]/ul/li[5]/div/a")
    private WebElement classs;

    @FindBy(xpath = "//*[@id=\"pr_id_3_content\"]/div/ul/li[1]/div[2]/ul/li[6]/div/a")
    private WebElement line;

    @FindBy(xpath = "//*[@id=\"pr_id_3_content\"]/div/ul/li[1]/div[2]/ul/li[7]/div/a")
    private WebElement type;

    @FindBy(xpath = "//*[@id=\"pr_id_3_content\"]/div/ul/li[1]/div[2]/ul/li[8]/div/a")
    private WebElement LOV;

    @FindBy(xpath = "//*[text()='User Defined Attributes']")
    private WebElement UDA;

    @FindBy(xpath = "//*[text()='Purchase Group']")
    private WebElement purchaseGroup;

    @FindBy(xpath = "//*[text()='UoM']")
    private WebElement UoM;


    @FindBy(xpath = "//button[@buttontype='ADD']")
    private WebElement addButton;

    @FindBy(xpath = "//span[@class='p-dropdown-label p-inputtext']")
    private WebElement clickSelectDropDownForAll;

    @FindBy(xpath = "//li[@aria-label='All']")
    private WebElement clickAll;

    @FindBy(xpath = "//button[@buttontype='SAVE']")
    private WebElement saveButton;

    @FindBy(xpath = "//button[@buttontype='SAVE_CONTINUE']")
    private WebElement saveExitButton;

    @FindBy(xpath = "//button[@buttontype='CANCEL']")
    private WebElement cancelButton;

    @FindBy(xpath = "//button[@buttontype='EDIT']")
    private WebElement clickEdit;

    @FindBy(xpath = "//button[@buttontype='DELETE_NORMAL']")
    private WebElement clickDelete;



    @FindBy(xpath = "//button[@buttontype='TICK']")
    private WebElement clickTICK;
    @FindBy(xpath = "//*[@id=\"root\"]/div[3]/div/div[1]/div[2]/div[1]/div/img")
    private WebElement homeButton;

    @FindBy(xpath = "//button[@buttontype='ADD_ROW']")
    private WebElement clickAdd_Row;



    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchLookUp;

    @FindBy(xpath = "//div[@class='p-dropdown-trigger']")
    private WebElement dropDownArrow_LookUP;

    @FindBy(xpath = "//li[@aria-label='Code']")
    private WebElement selectCODE;

    @FindBy(xpath = "//li[@aria-label='Description']")
    private WebElement selectDescription;

    @FindBy(xpath = "//tbody[@class='p-datatable-tbody']/tr[1]/td")
    private WebElement clickFirstRowInTheLookUp_SearchResult;

    @FindBy(xpath = "//button[@buttontype='SEARCH']")
    private WebElement SEARCH_Button;

    @FindBy(xpath = "//button[@buttontype='CLOSE_NORMAL']")
    private WebElement closeIcon;

    @FindBy(xpath = "//button[@buttontype='CONFIRMATION_CANCEL']")
    private WebElement cancelButtonPopUp;


    @FindBy(css = ".p-accordion-header-link")
    private WebElement searchWidget;

    @FindBy(css = ".p-dropdown-trigger-icon.pi.pi-chevron-down.p-clickable")
    private WebElement status;

    @FindBy(css = "li[aria-label='Active']")
    private WebElement statusActive;

    @FindBy(css = "li[aria-label='Inactive']")
    private WebElement statusInActive;

    @FindBy(css = "li[aria-label='Both']")
    private WebElement statusBoth;

    @FindBy(css = "button[buttontype='SEARCH_WITH_LABEL']")
    private WebElement searchButtonLabel;

    public PostLoginPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }



    public void clickApprovalSettings()
    {
        approvalSettings.click();
    }

    public void clickMaintainence()
    {
        maintenance.click();
    }

    public void clickApprovalRoleCode()
    {
        approvalRoleCode.click();
    }
    public void clickApprovalHierarchy()
    {
        approvalHierarchy.click();
    }

    public void clickReports(WebElement element)
    {
        reports.click();
    }

    public void clickListApproval()
    {
        listApproval.click();
    }

    // susmith added from  here

    public void clickInventory() {

        inventory.click();

    }

    public void clickInventoryMaintenance() {

        inventoryMaintenance.click();
    }

    public void clickSecurity() {

        security.click();
    }
    public void clickUserManagement() {
        userManagemet.click();
    }

    public void clickSystemAdministration() {

        systemAdministration.click();
    }

    public void clickBranch() {
        branch.click();
    }


    public void clickSubCategory() {
        subCategory.click();
    }

    public void clickBrand() {
        brand.click();
    }
    public void clickClass() {
        classs.click();
    }

    public void clickLine() {
        line.click();
    }

    public void clickType() {
        type.click();
    }

    public void clickLOV() {
        LOV.click();
    }
    public WebElement clickLOV_WebElement() {
        return LOV;
    }

    public void clickCategory() {
        category.click();
    }
    public WebElement clickCategory_WebElement() {
        return category;
    }

    public void clickUDA() {
        UDA.click();
    }

    public void clickItemMaster() {
        ItemMaster.click();
    }


    public void clickPurchaseGroup() {
        purchaseGroup.click();
    }

    public void clickUoM() {
        UoM.click();
    }

    public void clickSelectDropDown() {

        clickSelectDropDownForAll.click();
    }

    public WebElement clickSelectDropDown_WebElement() {

        return clickSelectDropDownForAll;
    }

    public By allBy() {

        return By.xpath(
                "//*[@id='root']/div[3]/div/div[3]/div[2]/main/div/div[5]/div[1]/div/div[1]/div/div[2]/table/tbody/tr/td[1]");
    }
    public void clickAll() {
        clickAll.click();
    }
    public WebElement clickAll_WebElement() {
        return clickAll;
    }

    public void clickSave() {
        saveButton.click();
    }


    public WebElement clickSave_WebElement() {
        return saveButton;
    }

    public void clickSaveExit() {
        saveExitButton.click();
    }
    public WebElement clickSaveExit_WebElement() {
        return	saveExitButton;
    }

    public void clickCancel() {
        cancelButton.click();
    }

    public WebElement clickCancel_WebElement() {
        return cancelButton;
    }

    public WebElement clickTICK_WebElement() {
        return clickTICK;
    }

    public void clickTICK() {
        clickTICK.click();
    }


    public void clickHomeButton() {
        homeButton.click();

    }

    public void clickEditButton() {
        clickEdit.click();

    }
    public WebElement clickEditButton_WebElement() {

        return clickEdit;
    }
    public void clickDeleteButton() {
        clickDelete.click();

    }
    public void clickAddRowButton() {
        clickAdd_Row.click();

    }
    public WebElement clickAddRowButton_WebElement() {
        return clickAdd_Row;

    }

    public boolean isAddButtonClickable() {

        return clickAdd_Row.isEnabled();
    }

    public boolean errorMessage() {

        boolean textFound = false;
        try {
            driver.findElement(By.xpath("//div[text()='Error']"));
            textFound = true;
        } catch (Exception e) {
            textFound = false;
        }
        return textFound;
        // return errorPopup.isDisplayed();
    }

    public boolean pleaseConfirm() {

        boolean textFound = false;
        try {
            driver.findElement(By.xpath("//div[text()='Please Confirm !']"));
            textFound = true;
        } catch (Exception e) {
            textFound = false;
        }
        return textFound;
        // return errorPopup.isDisplayed();
    }

    public boolean savedSuccessfullyPopUp() {

        boolean textFound = false;
        try {
            driver.findElement(By.xpath("//*[text()='Saved successfully!']"));
            textFound = true;
        } catch (Exception e) {
            textFound = false;
        }
        return textFound;

    }

    public By savedSuccessfullyPopUp_WebElement() {

        return By.xpath("//*[text()='Saved successfully!']");
    }

    public void clickAddButton() {

        addButton.click();
    }

    public void sendSearchLookUp(String vendorCode) throws InterruptedException {
        // TODO Auto-generated method stub

        Thread.sleep(1000);
        searchLookUp.sendKeys(Keys.chord(Keys.CONTROL,"a"), Keys.BACK_SPACE);
        Thread.sleep(1000);
        searchLookUp.sendKeys(vendorCode);
    }
    public WebElement sendSearchLookUp_WebElement() {
        return searchLookUp;
    }

    public void clickDropDown() {
        dropDownArrow_LookUP.click();

    }
    public void clickCode() {
        selectCODE.click();
    }

    public void clickSearchButton() {
        SEARCH_Button.click();
    }
    public String searchResultCode() {

        return driver.findElement(By.xpath("//table[@role='grid']/tbody/tr/td[1]")).getText();
    }
    public void clickOnSearchResult() {

        driver.findElement(By.xpath("//div[@class='p-datatable-wrapper']/table/tbody/tr/td[1]")).click();
        //clickResult.click();
    }

    public WebElement clickOnSearchResult_WebElement() {

        return	driver.findElement(By.xpath("//div[@class='p-datatable-wrapper']/table/tbody/tr/td[1]"));
        //clickResult.click();
    }

    public void closeIcon() {
        closeIcon.click();
    }

    public void clickDescription() {

        selectDescription.click();
    }
    public String searchResultDescription() {

        return driver.findElement(By.xpath("//table[@role='grid']/tbody/tr/td[2]")).getText();
    }

    public void clickCANCELPopUp() {
        cancelButtonPopUp.click();
    }


    public WebElement clickHomeButton_WebElement() {
        return homeButton;
    }
    public void clickStatus() {

        status.click();
    }

    public void clickSearchWidget() {

        searchWidget.click();
    }

    public void clickActive() {

        statusActive.click();
    }

    public void clickInActive() {

        statusInActive.click();
    }

    public void clickBoth() {

        statusBoth.click();
    }
    public void clickSearchButtonLabel() { // in the search actions

        searchButtonLabel.click();
    }


    // 10-03-2021


    public void clickApprovals() {

        approvals.click();
    }

}
