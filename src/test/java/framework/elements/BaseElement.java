package framework.elements;

import framework.driver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public abstract class BaseElement {

    private String name = null;
    private WebElement element = null;
    private List<WebElement> elements = null;
    private By locator = null;
    protected Browser browser = Browser.getInstance();

    protected BaseElement(By locator, String elementName)
    {
        this.locator = locator;
        this.name = elementName;
    }

    protected BaseElement(By locator)
    {
        this.locator = locator;
    }

    public WebElement getElement()
    {
        browser.getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
        element = browser.getDriver().findElement(locator);
        return element;
    }

    public List<WebElement> getElements()
    {
        browser.getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
        elements = browser.getDriver().findElements(locator);
        return elements;
    }

    public boolean isEnabled()
    {
        return element.isEnabled();
    }

    public boolean isDisplayed()
    {
        return element.isDisplayed();
    }

    public By getLocator()
    {
        return locator;
    }

    public void click()
    {
        browser.getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        getElement();
        JavascriptExecutor js = (JavascriptExecutor) browser.getDriver();
        js.executeScript("arguments[0].style.border='3px solid red'", element);

        element.click();
    }

    public abstract void sendKey(String key) throws CloneNotSupportedException;

    public void scrollPageTillElementVisible()
    {
        JavascriptExecutor js = (JavascriptExecutor) browser.getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }
}
