package org.acse.resources;

import org.acse.pageObjects.common.LoginPage;
import org.acse.utitities.ReadConfig;
import org.acse.utitities.XLUtils;
import org.apache.commons.io.FileUtils;

import org.apache.commons.mail.EmailException;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.apache.logging.log4j.Logger;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;




public class Base {


    ReadConfig readConfig = new ReadConfig();

    public String baseURL = readConfig.getApplicationURL();
    public String userName = readConfig.getUsername();
    public String password = readConfig.getPassword();
    public static JavascriptExecutor js;
    public static WebDriver driver;
    public  static Logger logger ;
    public WebDriverWait wait;

    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/java/org/acse/resources/data.properties");
        prop.load(fis);
        String browserName = prop.getProperty("browser");
        logger = LogManager.getLogger(Base.class.getName());

        if(browserName.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver",readConfig.getChromePath());
             driver = new ChromeDriver();

        }
        else if(browserName.equals("firefox"))
        {

            System.setProperty("webdriver.gecko.driver",readConfig.getFirefoxPath());
           driver = new FirefoxDriver();
        }
        else if(browserName.equals("IE"))
        {

        }
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        wait= new WebDriverWait(driver, 8);
        js=(JavascriptExecutor) driver;

        return driver;

    }


    public  void logIn() throws IOException {


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
             getScreenShotPath("loginTest", driver);
            logger.info("Screenshot Taken");
            Assert.assertTrue(false);
        }


    }

    public String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException
    {
        TakesScreenshot ts=(TakesScreenshot) driver;
        File source =ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
        FileUtils.copyFile(source,new File(destinationFile));
        return destinationFile;


    }



    public String reportGeneration(String excellLocation, String sheetName) {

        String location ="";
        try
        {
            XLUtils xlUtils = new XLUtils();
            location =	XLUtils.createOutPutExcel(excellLocation, sheetName);
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return location;
    }

    public void attachEmailReport(String attachmentPath, String formName) throws EmailException, AddressException {


        //authentication info
        final String username = "susmith.surendran@acsesolutions.com";
        final String password = "susmithacse123@";
        String fromEmail = "susmith.surendran@acsesolutions.com";
        String toEmail = "mailtosusmith@gmail.com";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.acsesolutions.com");
        properties.put("mail.smtp.port", "587");


        Address[] cc = new Address[] {new InternetAddress("mailtosusmith@gmail.com"),
                new InternetAddress("s.susmith08@gmail.com"),
                new InternetAddress("abinandh.krishnan@acsesolutions.com")};




        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });

        //Start our mail message
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipients(Message.RecipientType.TO, cc);
            msg.setSubject("Automation Test Results");

            Multipart emailContent = new MimeMultipart();

            //Text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(formName);

            //Attachment body part.
            MimeBodyPart excelAttachment = new MimeBodyPart();
            excelAttachment.attachFile(attachmentPath);

            //Attach body parts
            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(excelAttachment);

            //Attach multipart to message
            msg.setContent(emailContent);

            Transport.send(msg);
            System.out.println("Sent Mail");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




    }





}
