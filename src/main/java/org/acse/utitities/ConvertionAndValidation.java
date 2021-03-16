package org.acse.utitities;

import org.acse.pageObjects.common.PostLoginPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ConvertionAndValidation {

    public void lookUpSearch(String SearchBy, String value, WebDriver driver, JavascriptExecutor js) throws InterruptedException {
        PostLoginPage postLoginPage = new PostLoginPage(driver);
        boolean result;
        if(SearchBy.equalsIgnoreCase("code"))
        {

            try
            {
                postLoginPage.sendSearchLookUp(value);
            }
            catch (Exception e)
            {

                js.executeScript("arguments[0].click();", postLoginPage.sendSearchLookUp_WebElement());

            }
            System.out.println("Search by code");
            postLoginPage.clickDropDown();
            System.out.println("........... clicked dropdown");
            postLoginPage.clickCode();
            Thread.sleep(2000);

            postLoginPage.clickSearchButton();
            Thread.sleep(3000);
            String codeValueIs = postLoginPage.searchResultCode();
            System.out.println("codeValueIs.........." + codeValueIs);

            if (codeValueIs.equalsIgnoreCase(value))
            {
                System.out.println("found search result by code");
                try {
                    postLoginPage.clickOnSearchResult();
                } catch (Exception e) {

                    js.executeAsyncScript("arguments[0].click();",postLoginPage.clickOnSearchResult_WebElement());
                }

            }
            else
            {
                postLoginPage.closeIcon();
                result = false;
                Assert.assertTrue(false, "Search Code is not found");
            }
        }
        else //this is for search by description
        {

            try
            {
                postLoginPage.sendSearchLookUp(value);
            }
            catch (Exception e)
            {

                js.executeScript("arguments[0].click();", postLoginPage.sendSearchLookUp_WebElement());

            }
            System.out.println("Search by description ");
            postLoginPage.clickDropDown();
            System.out.println("........... clicked dropdown");
            postLoginPage.clickDescription();

            postLoginPage.clickSearchButton();
            Thread.sleep(3000);

            if (postLoginPage.searchResultDescription().equalsIgnoreCase(value))
            {
                System.out.println("found search result by description");
                try {
                    postLoginPage.clickOnSearchResult();
                } catch (Exception e) {
                    js.executeAsyncScript("arguments[0].click();",postLoginPage.clickOnSearchResult_WebElement());

                }

            } else {
                postLoginPage.closeIcon();
                result = false;
                Assert.assertTrue(false, "Search Description is not found");

            }
        }

    }

    public int numberOfIntegerPlaces(String text) {

        int integerPlaces = text.indexOf('.');
        int decimalPlaces = text.length() - integerPlaces - 1;
        return integerPlaces;
    }

    public int numberOfDecimalPlaces(String text) {

        int integerPlaces = text.indexOf('.');
        int decimalPlaces = text.length() - integerPlaces - 1;
        return decimalPlaces;
    }
}
