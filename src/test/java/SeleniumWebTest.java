import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SeleniumWebTest {
    WebDriver driver;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void writeSomething() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        driver.findElement(By.id("edit-name")).sendKeys("Sazid");
        driver.findElement(By.id("edit-number")).sendKeys("01546878057");
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        driver.findElements(By.className("ui-checkboxradio-label")).get(0).click();


        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dateString = currentDate.format(formatter);
        WebElement dateInput = driver.findElement(By.id("edit-date"));
        dateInput.clear();
        dateInput.sendKeys(dateString);
        Utils.scroll(driver);

        driver.findElement(By.id("edit-email")).sendKeys("Sazid@gmail.com");

        Utils.scroll(driver);
        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("Bangladesh, to the east of India on the Bay of Bengal, is a South Asian country marked by lush greenery and many waterways. Its Padma (Ganges), Meghna and Jamuna rivers create fertile plains, and travel by boat is common. On the southern coast, the Sundarbans, an enormous mangrove forest shared with Eastern India, is home to the royal Bengal tiger.");

        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys(System.getProperty("user.dir") + "./src/test/resources/win.jpg");
        Thread.sleep(1500);
        Utils.scroll(driver);
        driver.findElement(By.className("option")).click();
        driver.findElement(By.id("edit-submit")).click();
        Thread.sleep(500);
        String text = driver.findElement(By.className("alert-dismissible")).getText();
        System.out.println(text);
        Assertions.assertTrue(text.contains("There was a problem with your form submission. Please wait 300 seconds and try again."));

    }

    @AfterAll
    public void closeDriver() {
        // driver.quit();
    }
}
