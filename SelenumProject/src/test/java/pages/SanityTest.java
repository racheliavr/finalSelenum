package testCases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
 import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import pages.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathEvaluationResult;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
//Run tests in ascending order
@FixMethodOrder(MethodSorters.NAME_ASCENDING)


public class SanityTest {
    private static WebDriver driver;

    private static ExtentSparkReporter spark=new ExtentSparkReporter(Constans.REPORT_LOCATION);//הגדרת דף הדוח
    private static ExtentReports report=new ExtentReports();
//extentReports.attachReporter(reporter);
    SignInPage signInPage=new SignInPage(driver);
    HomePage homePage=new HomePage(driver);
    HomeCategory homeCategory=new HomeCategory(driver);
    ProductPage productPage=new ProductPage(driver);
    PaymentPage paymentPage=new PaymentPage(driver);
    @BeforeClass
    public static void beforeClass() throws ParserConfigurationException, IOException, SAXException {
        System.out.println("before class");
        System.setProperty("webdriver.chrome.driver", Constans.CRM_DRIVER);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        //enter to the site by xml file
        driver.get(getDataItem("URL",0));
        spark.config().setReportName("mySelenumReport");
        //מקשר בין הדוח (report)לקובץ html
        report.attachReporter(spark);
        waiting();
    }

  @Test
 public void A_signIn() throws ParserConfigurationException, IOException, SAXException, InterruptedException {
        //create report for this test and info of his function
      ExtentTest signInTest= report.createTest("sign-In");
      signInTest.log(Status.INFO,"This test login to the site with email&password");
      signInPage.clickSignInBtn();
      waiting();
      //check if pass/fail ,send concurrent message
     String actualResult= driver.getTitle();
    try{
        Assert.assertEquals(Constans.SIGN_IN_PAGE_TITLE,actualResult);
        signInTest.log(Status.PASS,"you reach the sign in page");
       }
    catch (AssertionError error)
    {
        //screenshot
        signInTest.fail("There was an error reaching the Sign in page",MediaEntityBuilder.createScreenCaptureFromPath
         (takeScreenShot("C:\\Users\\אברהמי\\Desktop\\WINNERIT\\finalSelenum\\SelenumProject\\target\\screenshots\\"+"report"+" signInTestStep1")).build());
       // signInTest.log(Status.FAIL,"There was an error reaching the Sign-in page",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\"+"report")).build());
        signInTest.log(Status.ERROR,"you can't inside to the login page-your title's are not eqal");
    }

    //sign in with existing account
    signInPage.inputEmail((getDataItem("EMAIL",0)));
    signInPage.clickRemoveSign();
    signInPage.inputPassword((getDataItem("PASSWORD",0)));
    signInPage.loginBtn();
    Thread.sleep(6000);
//check if success ente to my accout or realized its automation
      String loginResult =driver.getTitle();
try {
    Assert.assertEquals(Constans.MY_ACCOUNT_LOGIN_PAGE_TITLE,loginResult);
signInTest.log(Status.PASS,"your login has been success");
}catch (AssertionError error)
{
 signInTest.fail("there is an error with your login",MediaEntityBuilder.createScreenCaptureFromPath
(takeScreenShot("C:\\Users\\אברהמי\\Desktop\\WINNERIT\\finalSelenum\\SelenumProject\\target\\screenshots\\"+"myAccount"+"signInTestStep2")).build());
 signInTest.log(Status.ERROR,"the site realized its automation-you are navigate now to the home page");
 //if realized its navigate to thehome page
    driver.navigate().to(Constans.HOME_PAGE_URL);
}

}

@Test
public void B_homeCategory() throws IOException {
    //create report for this test and info of his function
    ExtentTest homeCategoryTest= report.createTest("Home Category");
    homeCategoryTest.log(Status.INFO,"This test choose category named 'HOME' ,select garden linkText and garden Accessorise banner");
    waiting();
    System.out.println("Home Category Test");
    //enter to home category banner with double click
    homeCategory.doubleClickHomeCategory();
    waiting();
    try {
        Assert.assertEquals(Constans.HOME_CATEGORY_NAV_TITLE,driver.getTitle());
        homeCategoryTest.log(Status.PASS,"you success to reach HOME category-step 1 success ");
    }catch (AssertionError error)
    {
        homeCategoryTest.fail("you can't reach HOME category-step 1 failed",MediaEntityBuilder.createScreenCaptureFromPath
        (takeScreenShot("C:\\Users\\אברהמי\\Desktop\\WINNERIT\\finalSelenum\\SelenumProject\\target\\screenshots\\"+"homecategory "+"testStep1")).build());
        homeCategoryTest.log(Status.ERROR,"try locate another element ");
    }
    //garden link
    homeCategory.gardenLinkClick();
    waiting();
    try{
Assert.assertEquals(Constans.GARDEN_LINK_TEXT_TITLE,driver.getTitle());
homeCategoryTest.log(Status.PASS,"you success to reach garden link-step 2 success");
    }
    catch (AssertionError error)
    {
        homeCategoryTest.fail("you can't reach garden link-step 2 failed",MediaEntityBuilder.createScreenCaptureFromPath
                (takeScreenShot("C:\\Users\\אברהמי\\Desktop\\WINNERIT\\finalSelenum\\SelenumProject\\target\\screenshots\\"+"homecategory "+"testStep2")).build());
        homeCategoryTest.log(Status.ERROR,"try locate another element ");
    }
    //garden accessorise banner
    homeCategory.gardenAccessoriseBannerClick();
    waiting();
    try{
        Assert.assertEquals(Constans.GARDEN_ACCESSORIES_BANNER_TITLE,driver.getTitle());
        homeCategoryTest.log(Status.PASS,"you success to reach garden Accessorise Banner-step 3 success");
    }
    catch (AssertionError error)
    {
homeCategoryTest.fail("you can't reach garden link-step 3 failed",MediaEntityBuilder.createScreenCaptureFromPath
        (takeScreenShot("C:\\Users\\אברהמי\\Desktop\\WINNERIT\\finalSelenum\\SelenumProject\\target\\screenshots\\"+"homecategory "+"testStep3")).build());
    }
    homeCategory.returnToTheHomePage();
    try {
        Assert.assertEquals(Constans.HOME_PAGE_TITLE,driver.getTitle());
        homeCategoryTest.log(Status.PASS,"you success to reach HOME page-step 4 success");
    }catch (AssertionError error)
    {
homeCategoryTest.fail("you can't reach HOME Page-step 4 failed",MediaEntityBuilder.createScreenCaptureFromPath
        (takeScreenShot("C:\\Users\\אברהמי\\Desktop\\WINNERIT\\finalSelenum\\SelenumProject\\target\\screenshots\\"+"homecategory "+"testStep4")).build());
    }
}
//change language from english to hebrew
  @Test
  public void C_changeLanguage() throws InterruptedException, IOException {
      //create report for this test and info of his function
      ExtentTest changeLanguageTest= report.createTest("change Language");
      changeLanguageTest.log(Status.INFO,"This test change sit's Language from english to hebrew");
    System.out.println("change language site from english to hebrew");
    homePage.changeLanguageBtnClick();
    waiting();
    homePage.chooseListClick();
    waiting();
    homePage.israelOptionBtnClick();
    waiting();
    homePage.shopNowClick();
    try{
        Assert.assertEquals(Constans.HOME_PAGE_HEBREW_URL,driver.getCurrentUrl());
        changeLanguageTest.log(Status.PASS,"you success to change your language site from english to hebrew");
    }catch (AssertionError error)
    {
     changeLanguageTest.fail("in attempt to change your language site from english to hebrew-it's failed",
             MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot
                ("C:\\Users\\אברהמי\\Desktop\\WINNERIT\\finalSelenum\\SelenumProject\\target\\screenshots\\"+"report changeLanguage")).build());

    }
   // driver.navigate().to(Constans.HOME_PAGE_HEBREW_URL);
    Thread.sleep(10000);
}


//search test
    @Test
    public void D_search() throws ParserConfigurationException, IOException, SAXException {
        //create report for this test and info of his function
        ExtentTest searchTest= report.createTest("search item");
        searchTest.log(Status.INFO,"This test let us option to search items");
        System.out.println("search product test");
        homePage.searchPlaceHolderClick(getDataItem("PRODUCT",1));
        waiting();
        homePage.searchBtnClick();
        try {
            Assert.assertEquals(Constans.SEARCH_TOP_BOYS_URL,driver.getCurrentUrl());
            searchTest.log(Status.PASS,"your search have been succeed");
        }
        catch (AssertionError error)
        {
            searchTest.fail("sorry, we can't find what you look for",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot
                    ("C:\\Users\\אברהמי\\Desktop\\WINNERIT\\finalSelenum\\SelenumProject\\target\\screenshots\\"+"reportSearch")).build());
            searchTest.log(Status.ERROR,"try to change your language");
        }

    }

  @Test
public void E_productPage() throws InterruptedException, IOException {
      //create report for this test and info of his function
      ExtentTest productPageTest= report.createTest("product-Page");
      productPageTest.log(Status.INFO,"in This test we can select color/size and add to our Bag ");
      System.out.println("select and add to bag test");
    //navigate to product page
    driver.get(Constans.PRODUCT_URL);
    waiting();
    try{
        Assert.assertEquals(Constans.PRODUCT_URL,driver.getCurrentUrl());
        productPageTest.log(Status.PASS,"you reach to your request page-test 1 succeed");
    }
    catch (AssertionError error)
    {
        productPageTest.fail("you can't reach to your request page",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot
                ("C:\\Users\\אברהמי\\Desktop\\WINNERIT\\finalSelenum\\SelenumProject\\target\\screenshots\\"+"productPage "+"testStep1")).build());
    }
    //select color
     productPage.colorOptionClick();
     waiting();
     productPage.specificColorClick();
     waiting();
      String colorResult=productPage.getSelectedColour();
      try {
          Assert.assertEquals(Constans.expectedColor,colorResult);
          productPageTest.log(Status.PASS,"the choose color like the expected-test 2 succeed");
      }
      catch (AssertionError error)
      {
          productPageTest.fail("the choose color is not like the expected-test 2 failed",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot
                  ("C:\\Users\\אברהמי\\Desktop\\WINNERIT\\finalSelenum\\SelenumProject\\target\\screenshots\\"+"productColor "+"testStep2")).build());
      }
     //select size
    productPage.sizeOptionClick();
     waiting();
     productPage.specificSizeClick();
     waiting();
     String sizeResult= productPage.getSelectedSize();
      try {
          Assert.assertEquals(Constans.expectedSize,sizeResult);
          productPageTest.log(Status.PASS,"the choose size like the expected-test 3 succeed");
      }
      catch (AssertionError error)
      {
          productPageTest.fail("the choose size is not like the expected-test 3 failed",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot
                  ("C:\\Users\\אברהמי\\Desktop\\WINNERIT\\finalSelenum\\SelenumProject\\target\\screenshots\\"+"productSize "+"testStep3")).build());
      }
      waiting();
     //add bag
     productPage.addItemToBagClick();
     waiting();
     //add a second item
     driver.navigate().to(Constans.SECOND_PRODUCT_URL);
      try{
          Assert.assertEquals(Constans.SECOND_PRODUCT_URL,driver.getCurrentUrl());
          productPageTest.log(Status.PASS,"you reach to your request page-test 4 succeed");
      }
      catch (AssertionError error)
      {
          productPageTest.fail("you can't reach to your request page-test 4 failed",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot
           ("C:\\Users\\אברהמי\\Desktop\\WINNERIT\\finalSelenum\\SelenumProject\\target\\screenshots\\"+"secondProductPage "+"testStep4")).build());
      }
     productPage.addItemToBagClick();
     waiting();
     //view bag
     productPage.viewOrEditClick();
     waiting();
     try {
         Assert.assertEquals(Constans.SHOPPING_BAG_URL_TITLE,driver.getCurrentUrl());
         productPageTest.log(Status.PASS,"you're now visit your shoppingBag-test 5 succeed");
     }
     catch (AssertionError error)
     {
      productPageTest.fail("you're not visit your shoppingBag-test 5 failed",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot
              ("C:\\Users\\אברהמי\\Desktop\\WINNERIT\\finalSelenum\\SelenumProject\\target\\screenshots\\"+"shoppingBag "+"testStep5")).build());
     }
     //check out to cash
     productPage.cashClick();
     Thread.sleep(20000);
     try {
         Assert.assertEquals(Constans.PAYMENT_PAGE_TITLE,driver.getTitle());
         productPageTest.log(Status.PASS,"Step 6 passed - You successfully reached the Payment Page");
     }
     catch (AssertionError error)
     {
productPageTest.fail("Step 6 failed - There was a problem reaching the Payment Page",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot
        ("C:\\Users\\אברהמי\\Desktop\\WINNERIT\\finalSelenum\\SelenumProject\\target\\screenshots\\"+"checkout"+"testStep6")).build());
     }
    driver.navigate().to("https://www.next.co.il/he");

}
@Ignore
@Test
public void F_paymentPage() throws ParserConfigurationException, IOException, SAXException {
 //create report for this test and info of his function
ExtentTest paymentPageTest= report.createTest("payment-Page");
    paymentPageTest.log(Status.INFO,"in This test we can pay  ");
    driver.navigate().to("https://www.next.co.il/he/secure/checkout/payment?bagChanged=False");
    System.out.println("payment test");
    paymentPage.cardOptionClick();
    waiting();
    if(paymentPage.isSelectedCardPaymentMethod())
        paymentPageTest.pass("Step 1 passed - Card option is selected");
    else
        paymentPageTest.fail("Step 1 failed - There was a problem selecting the card option",
       MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("C:\\Users\\אברהמי\\Desktop\\WINNERIT\\finalSelenum\\SelenumProject\\target\\screenshots\\"+"paymentTestStep1")).build());
    paymentPage.inputCardNumberClick((getDataItem("CARDNUMBER",2)));
    waiting();
    paymentPage.inputExpMonthBtn((getDataItem("EXPMONTH",2)));
    waiting();
    paymentPage.inputExpYearBtn((getDataItem("EXPYEAR",2)));
    waiting();
    paymentPage.inputCvvBtn((getDataItem("CVV",2)));
    waiting();
    paymentPage.payNowBtn();
    waiting();
}















    public static void waiting()
    {
        try {
            Thread.sleep(4000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }






    @AfterClass
    public static void afterClass() throws InterruptedException {

        System.out.println("after class");
        Thread.sleep(6000);
        driver.quit();
         report.flush();//סוגר את הדוח
    }



    private static String getDataItem (String keyName, int index) throws ParserConfigurationException, IOException, SAXException, SAXException, SAXException
    {
        File configXmlFile = new File(Constans.CONFIG_URL_XML);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        dBuilder = dbFactory.newDocumentBuilder();

        Document doc = null;

        assert dBuilder != null;
        doc = dBuilder.parse(configXmlFile);

        if (doc != null) {
            doc.getDocumentElement().normalize();
        }
        assert doc != null;
        return doc.getElementsByTagName(keyName).item(0).getTextContent();

    }

    private static String takeScreenShot(String ImagesPath) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath+".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath+".png";
    }







}