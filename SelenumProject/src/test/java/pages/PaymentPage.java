package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage {

    private static WebDriver driver;
    //constructor
    public PaymentPage(WebDriver driver){this.driver=driver;}


    //locators
    private By cardOptionLocator=By.cssSelector("[id=card_option]");
    private By inputCardNumberLocator=By.cssSelector("[id=cardNumberLabel]");
    private By inputExpMonthLocator=By.cssSelector("[id=expiryMonth]");
    private By inputExpYearLocator=By.className("textbox expiry expiry-year");
    private By inputCvvLocator=By.className("textbox pin security-code");
    private By payNowLocator=By.xpath("//*[@id=\"submitButton\"]");


    //methods
    public void cardOptionClick(){driver.findElement(cardOptionLocator).click();}
    //card number
    public void inputCardNumberClick(String cardNumber){driver.findElement(inputCardNumberLocator).sendKeys(cardNumber);}
    //exp month
    public void inputExpMonthBtn(String month){driver.findElement(inputExpMonthLocator).sendKeys(month);}
    //exp month
    public void inputExpYearBtn(String year){driver.findElement(inputExpYearLocator).sendKeys(year);}
    //Cvv
    public void inputCvvBtn(String cvv){driver.findElement(inputCvvLocator).sendKeys(cvv);}
    public void payNowBtn(){driver.findElement(payNowLocator).click();}

    public boolean isSelectedCardPaymentMethod(){return driver.findElement(cardOptionLocator).isSelected();}

}
