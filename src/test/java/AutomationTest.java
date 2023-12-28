import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class AutomationTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        // Povecava prozor na maksimum
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }


    @AfterMethod
    public void tearDown() {

        driver.quit();
    }


    @Test
    @Parameters({"firstname","lastname", "email", "password", "expectedResultFirst","elementorSelectorFirst",
            "expectedResultLast","elementorSelectorLast",
            "expectedResultPass","elementorSelectorPass",
            "expectedResultEmail","elementorSelectorEmail",
            "expectedResultError","elementorSelectorError"})
    public void registrationTest(@Optional String firstname,@Optional String lastname,@Optional String email,@Optional String password,
                                 @Optional String expectedResultFirst,@Optional String elementorSelectorFirst,@Optional String expectedResultLast,
                                 @Optional String elementorSelectorLast,@Optional String expectedResultPass,@Optional String elementorSelectorPass,
                                 @Optional String expectedResultEmail,@Optional String elementorSelectorEmail,@Optional String expectedResultError,@Optional String elementorSelectorError) throws InterruptedException {

        driver.get("http://www.automationpractice.pl/index.php");


        //Click on Sign in
        driver.findElement(By.className("login")).click();

        //Fills in the email address
        driver.findElement(By.cssSelector("#email_create")).sendKeys("test" + System.currentTimeMillis() + "@test.com");

        //Click on Create an account
        driver.findElement(By.id("SubmitCreate")).click();


        //We select the radio button Mr.
        driver.findElement(By.id("id_gender1")).click();


        //We enter the First Name
        driver.findElement(By.cssSelector("#customer_firstname")).sendKeys(firstname);

        //We enter the Last Name
        driver.findElement(By.cssSelector("#customer_lastname")).sendKeys(lastname);

        //We clear the old email address

        driver.findElement(By.id("email")).clear();


        //We enter the new email address
        driver.findElement(By.id("email")).sendKeys(email);

        //We enter the password
        driver.findElement(By.id("passwd")).sendKeys(password);


        //Select Register
        driver.findElement(By.id("submitAccount")).click();

        if (elementorSelectorFirst != null && expectedResultFirst != null) {
            // Odradi ako su obe vrednosti unete
            Assert.assertEquals(driver.findElement(By.xpath(elementorSelectorFirst)).getText(), expectedResultFirst);
        }
        if (elementorSelectorLast != null && expectedResultLast != null) {
            // Odradi ako su obe vrednosti unete
            Assert.assertEquals(driver.findElement(By.xpath(elementorSelectorLast)).getText(), expectedResultLast);
        }
        if (elementorSelectorPass != null && expectedResultPass != null) {
            // Odradi ako su obe vrednosti unete
            Assert.assertEquals(driver.findElement(By.xpath(elementorSelectorPass)).getText(), expectedResultPass);
        }
        if (elementorSelectorEmail != null && expectedResultEmail != null) {
            // Odradi ako su obe vrednosti unete
            Assert.assertEquals(driver.findElement(By.xpath(elementorSelectorEmail)).getText(), expectedResultEmail);
        }
        if (elementorSelectorError != null && expectedResultError != null) {
            // Odradi ako su obe vrednosti unete
            Assert.assertEquals(driver.findElement(By.xpath(elementorSelectorError)).getText(), expectedResultError);
        }
        Thread.sleep(3000);
    }
}
