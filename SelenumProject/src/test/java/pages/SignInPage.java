package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignInPage {

    //מקשר בין כל הקלאסים לטסט
  private static  WebDriver driver;
  //contstructor

    public SignInPage(WebDriver driver){this.driver=driver;}


//locator
private By signInBtnLocator= By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[4]/div[2]/div/a/img");
private By EmailInputLocator=By.id("EmailOrAccountNumber");

//remove checkbox not to  remember Email
private By chckBoxRememerLocator=By.xpath("//*[@id=\"passwordLogin\"]/div[2]/div[1]/div/div/span");
private By passwordLocator=By.id("Password");
private By loginBtnLocator=By.id("SignInNow");

    //method
    public void clickSignInBtn() { driver.findElement(signInBtnLocator).click();}
    public void inputEmail(String emailAddress )
    {
    driver.findElement(EmailInputLocator).sendKeys(emailAddress);
    }
    public void clickRemoveSign() {driver.findElement(chckBoxRememerLocator).click();}
    public void inputPassword(String password){ driver.findElement(passwordLocator).sendKeys(password);}
    public void loginBtn(){ driver.findElement(loginBtnLocator).click();}















}
