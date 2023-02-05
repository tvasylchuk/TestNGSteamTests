package framework.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Browser {
    private static WebDriver driver = null;
    private static WebDriverWait wait = null;

    private static Browser instance = null;
    private static BrowserType currentBrowser = BrowserType.Chrome;

    private static Duration browserTimeout = Duration.ofSeconds(5);

    public Browser()
    {
    }

    public static Browser getInstance()
    {
        if (instance == null)
        {
            driver = DriverFactory.GetBrowser(currentBrowser);
            wait = new WebDriverWait(driver, browserTimeout);
            instance = new Browser();
        }
        return instance;
    }

    public void navigate(String uri)
    {
        driver.navigate().to(uri);
    }

    public void exit()
    {
        driver.quit();
    }

    public String getBrowserUri()
    {
        return driver.getCurrentUrl();
    }

    public void maximise()
    {
        driver.manage().window().maximize();
    }

    public WebDriver getDriver()
    {
        return driver;
    }

    public WebDriverWait getWait()
    {
        return wait;
    }
}
