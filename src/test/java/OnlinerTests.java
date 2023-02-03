import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.time.Duration;

@Test
public class OnlinerTests {

    public ChromeDriver driver;

    public WebDriverWait wait;

    @BeforeMethod()
    public void setUp()
    {
        this.driver = new ChromeDriver();
        this.driver.navigate().to("https://www.onliner.by");
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
    }

    @Test()
    public void navigateToCatalogTest()
    {
        this.driver.findElement(By.linkText("Каталог")).click();
        String url = this.driver.getCurrentUrl();

        Assert.assertEquals(url, "https://catalog.onliner.by/");

    }

    @AfterMethod()
    public void tearDown()
    {
        this.driver.quit();
    }
}
