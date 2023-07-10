package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private static WebDriver driver;

    //constractor
    public HomePage(WebDriver driver){this.driver=driver ;}

    //locators
    //לחיצה על סמל הארצות
    private By changeLanguageLocator=By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[9]/button");
    //לחיצה על החץ לבחירת ארץ
    private By chooseListLocator=By.id("mui-component-select-country-selector-select");
    //choose israel from the list
    private By israelOptionLocator=By.xpath("//*[@id=\"menu-country-selector-select\"]/div[3]/ul/li[31]");
    private By shopNowLocator=By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[10]/div/div[3]/div/div[5]/button");
    //search
    private By searchPlaceHolderLocator=By.xpath("//*[@id=\"header-big-screen-search-box\"]");
    private By searchBtnLocator= By.xpath("//*[@id=\"header-search-form\"]/button");

    //methods
    public void changeLanguageBtnClick(){driver.findElement(changeLanguageLocator).click();}
    public void  chooseListClick(){driver.findElement(chooseListLocator).click();}
    public void israelOptionBtnClick(){driver.findElement(israelOptionLocator).click();}
    public void shopNowClick(){driver.findElement(shopNowLocator).click();}
    public void searchPlaceHolderClick(String prodName)
    {
        driver.findElement(searchPlaceHolderLocator).click();
        driver.findElement(searchPlaceHolderLocator).sendKeys(prodName);
    }
    public void searchBtnClick(){driver.findElement(searchBtnLocator).click();}





}
