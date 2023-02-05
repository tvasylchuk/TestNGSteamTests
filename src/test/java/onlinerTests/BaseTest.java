package onlinerTests;

import framework.driver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

@Test
public class BaseTest {

    protected static Browser browser = null;

    @BeforeMethod()
    public void setUp()
    {
        browser = Browser.getInstance();
        browser.navigate("https://www.onliner.by");
    }


    @AfterMethod()
    public void tearDown()
    {
        browser.exit();
    }
}
