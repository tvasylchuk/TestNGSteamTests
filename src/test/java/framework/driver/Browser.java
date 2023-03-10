package framework.driver;

import framework.Logger;
import framework.PropertiesResourceManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import steam.model.Languages;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Calendar;

public class Browser {
    private final static String BROWSER_FILE_NAME = "Browser.properties";
    private final static String DEFAULT_BROWSER = "Chrome";
    private final static String DEFAULT_LOC = "EN";

    private static PropertiesResourceManager props;
    private static Logger logger;
    private static Languages loc = null;
    private static WebDriver driver = null;
    private static WebDriverWait wait = null;
    private static Browser instance = null;
    private static BrowserType currentBrowser;
    private static Duration browserTimeout = Duration.ofSeconds(5);
    private static Duration pageLoadTimeout = Duration.ofSeconds(10);
    private static int fileDownloadTimeout;
    private static String download_directory =  "";
    private static int windows = 0;

    private Browser()
    {
        logger = Logger.getInstance();
        logger.logClassInitialization("framework.driver.Browser");
    }

    private static void propertiesInit() {
        props = new PropertiesResourceManager(BROWSER_FILE_NAME);

        try {
            props.getPropertiesFromFile();
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
            logger.error(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }

        browserTimeout = Duration.ofSeconds(Long.parseLong(props.getPropertyValueByKey("ConditionTimeout")));
        pageLoadTimeout = Duration.ofSeconds(Long.parseLong(props.getPropertyValueByKey("PageLoadTimeout")));
        fileDownloadTimeout = Integer.parseInt(props.getPropertyValueByKey("FileDownloadTimeOut"));
        currentBrowser =  getBrowserType(System.getProperty("browser"));
        loc =  getLocale(System.getProperty("locale"));
    }

    public static Browser getInstance()
    {
        if (instance == null)
        {
            propertiesInit();
            driver = DriverFactory.GetBrowser(currentBrowser);
            driver.manage().timeouts().implicitlyWait(browserTimeout);
            wait = new WebDriverWait(driver, browserTimeout);
            instance = new Browser();
            download_directory = DriverFactory.getBrowserDownloadDirectory();
            windows = 1;
            logger.info(String.format("The browser %1s$ has been started", currentBrowser.toString()));
        }
        return instance;
    }

    public void navigate(String uri)
    {
        driver.navigate().to(uri);
        logger.info(String.format("framework.driver.Browser.navigate: %s", uri));
    }

    public void exit()
    {
        driver.quit();
        instance = null;
        windows = 0;
        logger.info(String.format("The browser %1s$ has been closed", currentBrowser.toString()));
    }

    public String getBrowserUri()
    {
        return driver.getCurrentUrl();
    }

    public void maximise()
    {
        driver.manage().window().maximize();
    }

    public void openNewWindow(){
        driver.switchTo().newWindow(WindowType.WINDOW);
        windows++;
    }

    public Languages getLoc(){ return loc; }

    public WebDriver getDriver()
    {
        return driver;
    }

    public WebDriverWait getWait()
    {
        return wait;
    }

    public BrowserType getCurrentBrowser() { return currentBrowser; }

    public int getFDownloadTimeout()
    {
        return fileDownloadTimeout;
    }

    public int getWindowsNumber() { return windows; }

    public String getBrowserDownloadDirectory() { return download_directory; }

    public Boolean isBrowserAlive(){ return instance != null;  }

    public void waitPageToLoad()
    {
        WebDriverWait wait = new WebDriverWait(driver, pageLoadTimeout);
        try
        {
            wait.until((ExpectedCondition<Boolean>) input -> {
                if (!(input instanceof JavascriptExecutor)){
                    logger.info("framework.driver.Browser.waitPageToLoad.completed");
                    return true;
                }
                else {
                    Object result = (((JavascriptExecutor) input)
                            .executeScript("return document['readyState'] ? 'complete' == document.readyState : true"));
                    if (result instanceof Boolean && (Boolean) result){
                        logger.info("framework.driver.Browser.waitPageToLoad.completed");
                        return true;
                    }
                    logger.error("framework.driver.Browser.waitPageToLoad.incomplete");
                    return false;
                }
            });
        }
        catch (Exception e){
            logger.warn("framework.driver.Browser.waitPageToLoad.timeout");
        }
    }

    public static File takeScreenshot() throws Exception
    {
        String timeStamp;
        File screenShotFile;
        byte[] scrImage = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss.SSS").format(Calendar.getInstance().getTime());
        screenShotFile = new File(System.getProperty("user.dir")+"\\Screenshots\\"+timeStamp+".png");
        screenShotFile.getParentFile().mkdirs();
        screenShotFile.createNewFile();
        try{
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(screenShotFile));
            outputStream.write(scrImage);
        }
        catch (Exception e) {
            Logger.loggerInstance.error(e.getMessage());
            Logger.loggerInstance.error(Arrays.toString(e.getStackTrace()));
        }

        return screenShotFile;
    }

    private static BrowserType getBrowserType(String parameter) {
        if (parameter == null) {
            return BrowserType.valueOf(DEFAULT_BROWSER);
        }

        return BrowserType.valueOf(parameter);
    }

    private static Languages getLocale(String parameter) {

        if (parameter == null) {
            return Languages.valueOf(DEFAULT_LOC);
        }

        return Languages.valueOf(parameter);
    }
}
