package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomeCategory {
private static WebDriver driver;
    //constructor
    public HomeCategory(WebDriver driver){this.driver=driver ;}

    //locator
private By homeCategoryLocator=By.linkText("HOME");
  //  private By homeCategoryLocator=By.cssSelector("[id*='meganav-link-6']");
    //choose garden link(right side page)
    private By gardenLinkLocator=By.linkText("Garden") ;
    //choose garden accessorise banner
    private By gardenAccessoriseBannerLocator=By.xpath("//*[@id=\"ctasAndCopyTemplate\"]/div/div/div[2]/div[2]/a");

    //method
    public void doubleClickHomeCategory(){
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(homeCategoryLocator)).perform();
    }
    public void gardenLinkClick(){driver.findElement(gardenLinkLocator).click();}
    public void gardenAccessoriseBannerClick(){driver.findElement(gardenAccessoriseBannerLocator).click();}
    //return to ome page
    public void returnToTheHomePage(){
    driver.navigate().to(Constans.HOME_PAGE_URL);
}



}
