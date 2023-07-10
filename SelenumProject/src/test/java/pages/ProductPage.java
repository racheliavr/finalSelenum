package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    private static WebDriver driver;
    //constructor
    public ProductPage(WebDriver driver){this.driver=driver;}

    //locators
      //-select color
    //לחיצה על שדה רשימה צבע
    private By colorOptionLocator=By.cssSelector("[id*='dk_container_Colour-']");
    //בחירת צבע
    private By specificColorLocator=By.xpath("//*[@id=\"dk_container_Colour-73495\"]/div/ul/li[3]/a") ;

      //-select size
      //לחיצה על שדה רשימה מידה
  private By sizeOptionLocator=By.cssSelector("[id*='dk_container_Size-']");
    //לחיצה על מידה 80-86
   //My "//*[@id=\"dk_container_Size-D71-682\"]/div/ul/li[5]/a"
    private By specificSizeLocator=By.xpath("//*[@id=\"dk_container_Size-C03-381\"]/div/ul/li[5]/a") ;
    private By addItemToBagLocator=By.linkText("הוסף לסל");
    private By viewOrEditLocator=By.linkText("הצג/ערוך סל");
    private By cashLocator=By.xpath("//*[@id=\"title\"]/a[1]");

//method

    //-color
  public void colorOptionClick(){driver.findElement(colorOptionLocator).click();}
  public void specificColorClick(){driver.findElement(specificColorLocator).click();}
   // public void colorOptionClick(){driver.findElement(By.cssSelector("[id*='dk_container_Colour-']")).click();}
   // public void specificColorClick(){ driver.findElement(By.xpath("//*[@id=\"dk_container_Colour-73495\"]/div/ul/li[3]/a")).click();}

//-size


    //לחיצה על שדה רשימה מידה
    public void sizeOptionClick(){driver.findElement(sizeOptionLocator).click();}
    //לחיצה על מידה 80-86

   public void specificSizeClick(){driver.findElement(specificSizeLocator).click();}

    //----שלב ההוספה לסל
    public void addItemToBagClick(){driver.findElement(addItemToBagLocator).click();}
    public void viewOrEditClick(){driver.findElement(viewOrEditLocator).click();}
    public void cashClick(){driver.findElement(cashLocator).click();}

    public String getSelectedColour(){
        return driver.findElement(specificColorLocator).getText();
    }
    public String getSelectedSize(){
        return driver.findElement(specificSizeLocator).getText();
    }
}
