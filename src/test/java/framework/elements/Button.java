package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

public class Button extends BaseElement{

    public Button(By locator, String elementName)
    {
        super(locator, elementName);
    }
    public Button(By locator)  { super(locator);}

    public void doubleClick()
    {
        Actions action = new Actions(browser.getDriver());
        action.doubleClick(getElement()).perform();
    }

    public String getButtonName()
    {
        return getElement().getText();
    }

    public void waitTillButtonAnimated()
    {
        try
        {
            browser.getWait().until(ExpectedConditions.visibilityOfElementLocated(getLocator()));
        }
        catch (TimeoutException ignored)
        {
           Reporter.log("Animation on the button is too fast.");
        }
        browser.getWait().until(ExpectedConditions.invisibilityOfElementLocated(getLocator()));
    }
}
