import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class SeleniumTest {
    private WebDriver webDriver;

       @Before
        public void setUp() {
        // REQUIRED by old extension – do not change
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");

        //get working directory
        String workingDir = System.getProperty("user.dir");
        System.out.println("Working directory: " + workingDir);

        // Get file
        File file = new File(workingDir + File.separator + "src/main/index.html");
        String path = "file://" + file.getAbsolutePath();

        // Create a new ChromeDriver instance
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        webDriver = new ChromeDriver(options);

        // Open the HTML file
        webDriver.get(path);
    }

    @After
    public void teardown() {
        webDriver.quit();
    }

    @Test
    public void testPageText() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement speechPart1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("opening")));
        String opening = "To be, or not to be, that is the question:";

        WebElement speechPart2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("theRest")));
        String theRest = "Whether 'tis nobler in the mind to suffer";

        Thread.sleep(1000);

        Assert.assertEquals(opening, speechPart1.getText());
        Assert.assertEquals(theRest, speechPart2.getText().substring(0, 41));
    }
}



