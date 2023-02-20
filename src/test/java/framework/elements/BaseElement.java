package framework.elements;

import framework.Logger;
import framework.driver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.List;

public abstract class BaseElement {

    private String name = null;
    private WebElement root = null;
    private WebElement element = null;
    private final By locator;
    protected Browser browser = Browser.getInstance();
    protected Logger logger = Logger.getInstance();

    protected BaseElement(By locator, String elementName)
    {
        this.locator = locator;
        this.name = elementName;
    }

    protected BaseElement(By locator)
    {
        this.locator = locator;
    }

    protected BaseElement(By locator, WebElement rootElement)
    {
        this.locator = locator;
        this.root = rootElement;
    }

    public WebElement getElement()
    {
        if (root == null) {
            browser.getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
            element = browser.getDriver().findElement(locator);
        }
        else {
              browser.getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(root, locator));
              element = root.findElement(locator);
        }
        return element;
    }

    public List<WebElement> getElements()
    {
        List<WebElement> elements;
        if (root == null)
        {
            browser.getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
            elements = browser.getDriver().findElements(locator);
        }
        else {
            browser.getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(root, locator));
            elements = root.findElements(locator);
        }

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
        try {
            browser.getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
            getElement();
            JavascriptExecutor js = (JavascriptExecutor) browser.getDriver();
            js.executeScript("arguments[0].style.border='3px solid red'", element);

            element.click();
            logger.info("framework.elements.BaseElement.click.perform");
        }
        catch(Exception e)
        {
            logger.error("framework.elements.BaseElement.click.failed");
            logger.error(e.getMessage());
            logger.error(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }

    }

    public String getTextFromElement()
    {
        getElement();
        return element.getText();
    }

    public void scrollPageTillElementVisible()
    {
        JavascriptExecutor js = (JavascriptExecutor) browser.getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public boolean exists()
    {
        try
        {
            return getElement().isEnabled();
        }
        catch(TimeoutException e)
        {
            return false;
        }
    }
}
