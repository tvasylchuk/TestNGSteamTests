package framework.driver;

import framework.PropertiesResourceManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.util.Strings;

import java.io.FileNotFoundException;
import java.time.Duration;

public class Browser {
    private static WebDriver driver = null;
    private static WebDriverWait wait = null;
    private final static String FILE_NAME = "Browser.properties";
    private static PropertiesResourceManager props;
    private static Browser instance = null;
    private static BrowserType currentBrowser;
    private final static String DEFAULT_BROWSER = "Firefox";

    private static Duration browserTimeout = Duration.ofSeconds(5);

    public Browser()
    {
    }

    private static void propertiesInit() {
        props = new PropertiesResourceManager(FILE_NAME);

        try {
            props.getPropertiesFromFile();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        browserTimeout = Duration.ofSeconds(Long.parseLong(props.getPropertyValueByKey("ConditionTimeout")));
            if (!Strings.isNotNullAndNotEmpty(props.getPropertyValueByKey("BrowserType")))
            {
                currentBrowser =  BrowserType.valueOf(DEFAULT_BROWSER);
            }
            else {
                currentBrowser = BrowserType.valueOf(props.getPropertyValueByKey("BrowserType"));
            }
    }

    public static Browser getInstance()
    {
        if (instance == null)
        {
            propertiesInit();
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
        instance = null;
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
