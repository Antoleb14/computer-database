package selenium;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {

    private String baseUrl;
    private WebDriver driver;
    private ScreenshotHelper screenshotHelper;

    @Before
    public void openBrowser() {
        // baseUrl = System.getProperty("webdriver.base.url");
        baseUrl = "http://localhost:8080/projetloic/home?p=1&l=10";
        driver = new FirefoxDriver();
        driver.get(baseUrl);
        screenshotHelper = new ScreenshotHelper();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void saveScreenshotAndCloseBrowser() throws IOException {
        screenshotHelper.saveScreenshot("screenshots/screenshot" + LocalDateTime.now() + ".png");
        driver.quit();
    }

    @Test
    public void pageTest() throws IOException {
        assertNotNull(driver.findElement(By.id("actions")));

        // make a search
        driver.findElement(By.id("searchbox")).clear();
        driver.findElement(By.id("searchbox")).sendKeys("amiga");
        driver.findElement(By.id("searchsubmit")).click();

        // go back home
        driver.findElement(By.className("navbar-brand")).click();

        // pagination test
        driver.findElement(By.id("nextPage")).click();

        // assertEquals("The page title should equal Google at the start of the
        // test.", "Google", driver.getTitle());
        // WebElement searchField = driver.findElement(By.name("q"));
        // searchField.sendKeys("Drupal!");
        // searchField.submit();
        // assertTrue("The page title should start with the search string after
        // the search.",
        // (new WebDriverWait(driver, 10)).until(new ExpectedCondition() {
        // public Boolean apply(WebDriver d) {
        // return d.getTitle().toLowerCase().startsWith("drupal!");
        // }
        // }));
    }

    private class ScreenshotHelper {

        public void saveScreenshot(String screenshotFileName) throws IOException {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(screenshotFileName));
        }
    }

}
