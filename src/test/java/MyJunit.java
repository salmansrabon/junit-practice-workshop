import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyJunit {
    WebDriver driver;
    @BeforeAll
    public void setup(){
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--headed");
        driver=new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //driver.get("https://demoqa.com/");
    }
    @DisplayName("Visit website")
    @Test
    public void visitWebsite(){
        driver.get("https://demoqa.com/");
        String title= driver.getTitle();
        String currentUrl= driver.getCurrentUrl();

        System.out.println(title);
        System.out.println(currentUrl);

        Assertions.assertTrue(title.contains("DEMOQA"));
    }
    @Test
    public void fillupForm(){
        driver.get("https://demoqa.com/text-box");
//        driver.findElement(By.id("userName")).sendKeys("Salman Rahman");
//        driver.findElement(By.id("userEmail")).sendKeys("salman@test.com");

//        driver.findElements(By.className("form-control")).get(0).sendKeys("Salman Rahman");
//        driver.findElements(By.className("form-control")).get(1).sendKeys("salman@test.com");
//        driver.findElements(By.className("form-control")).get(2).sendKeys("Gulshan");
//        driver.findElements(By.className("form-control")).get(3).sendKeys("Dhaka");

        Utils.doScroll(driver,800);

        List<WebElement> txtBox= driver.findElements(By.className("form-control"));
        txtBox.get(0).sendKeys("Salman Rahman");
        txtBox.get(1).sendKeys("salman@test.com");
        txtBox.get(2).sendKeys("Gulshan");
        txtBox.get(3).sendKeys("Dhaka");

        Utils.doScroll(driver,200);

        driver.findElement(By.id("submit")).click();

    }

    @Test
    public void buttonClick(){
        driver.get("https://demoqa.com/buttons");
        List<WebElement> btnElements= driver.findElements(By.cssSelector("[type=button]"));
        Actions actions=new Actions(driver);
        actions.doubleClick(btnElements.get(1)).perform();
        actions.contextClick(btnElements.get(2)).perform();
        //btnElements.get(3).click();
        actions.click(btnElements.get(3)).perform();

        List<WebElement> textElem= driver.findElements(By.tagName("p"));
        String message1=textElem.get(0).getText();
        String message2=textElem.get(1).getText();
        String message3=textElem.get(2).getText();

        Assertions.assertTrue(message1.contains("double click"));
        Assertions.assertTrue(message2.contains("right click"));
        Assertions.assertTrue(message3.contains("dynamic click"));
    }

    @Test
    public void handleAlert() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");
        driver.findElement(By.id("confirmButton")).click();
        Thread.sleep(6000);
        driver.switchTo().alert().accept();
    }

    @Test
    public void selectDatePicker(){
        driver.get("https://demoqa.com/date-picker");
        WebElement element= driver.findElement(By.id("datePickerMonthYearInput"));
        element.click();
        element.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        element.sendKeys("04/10/2024");
        element.sendKeys(Keys.ENTER);

    }
    @Test
    public void selectMenu(){
        driver.get("https://demoqa.com/select-menu");
        Select select=new Select(driver.findElement(By.id("oldSelectMenu")));
        select.selectByVisibleText("Green");
    }
    @AfterAll
    public void closeDriver(){
        //driver.quit();
    }
}
