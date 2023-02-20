package framework.driver;

import framework.Logger;
import framework.PropertiesResourceManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class DriverFactory {

    private final static String FILE_NAME = "Driver.properties";
    private static String downloadFileDirectory = "";
    private static String browserLanguage = null;

    public static WebDriver GetBrowser(BrowserType type)
    {
        Logger logger = Logger.getInstance();
        getBrowserArguments();

        switch (type) {
            case Firefox -> {
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("browser.download.folderList", 2);
                profile.setPreference("browser.download.dir", downloadFileDirectory);
                profile.setPreference("fission.webContentIsolationStrategy", 0);
                profile.setPreference("fission.bfcacheInParent", false);
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                        "text/csv,application/java-archive, application/x-msexcel,application/excel,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/vnd.microsoft.portable-executable");
                profile.setPreference("intl.accept_languages",browserLanguage);
                FirefoxOptions option = new FirefoxOptions();
                option.setProfile(profile);
                return new FirefoxDriver(option);
            }
            case Chrome -> {
                try {
                    HashMap<String, Object> chromePrefs = new HashMap<>();
                    chromePrefs.put("profile.default_content_settings.popups", 2);
                    chromePrefs.put("download.default_directory", downloadFileDirectory);
                    chromePrefs.put("download.prompt_for_download", false);
                    chromePrefs.put("download.extensions_to_open", "application/xml");
                    chromePrefs.put("safebrowsing.enabled", true);

                    ChromeOptions options = new ChromeOptions();
                    options.setExperimentalOption("prefs", chromePrefs);
                    options.addArguments(String.format("--lang=%s", browserLanguage));
                    options.addArguments("--safebrowsing-disable-download-protection");
                    options.addArguments("safebrowsing-disable-extension-blacklist");
                    options.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));

                    return new ChromeDriver(options);
                } catch (Exception e) {
                    logger.error("The driver wasn't initialized");
                    logger.error(Arrays.toString(e.getStackTrace()));
                    throw new RuntimeException(e);
                }
            }
            case Safari -> {
                return new SafariDriver();
            }
            case Edge -> {
                return new EdgeDriver();
            }
            default -> throw new IllegalStateException("Invalid browser type: " + type);
        }
    }

    public static String getBrowserDownloadDirectory()
    {
        return downloadFileDirectory;
    }

    private static void getBrowserArguments()
    {
        PropertiesResourceManager resourceManager = new PropertiesResourceManager(FILE_NAME);
        try{
            resourceManager.getPropertiesFromFile();
        }
        catch(Exception e) {
           throw  new RuntimeException(e);
        }
        downloadFileDirectory = System.getProperty("user.dir")+ resourceManager.getPropertyValueByKey("DownloadFileFolder");
        browserLanguage = resourceManager.getPropertyValueByKey("browserLanguage");
    }
}
