package framework.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public abstract class DriverFactory {

    public static WebDriver GetBrowser(BrowserType type)
    {
        switch (type) {
            case Firefox:
                return new FirefoxDriver();
            case Chrome:
                return new ChromeDriver();
            case Safari:
                return new SafariDriver();
            case Edge:
                return new EdgeDriver();
            default:
                throw new IllegalStateException("Invalid browser type: " + type);
        }
    }
}
