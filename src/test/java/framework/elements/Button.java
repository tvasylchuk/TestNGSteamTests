package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Button extends BaseElement{

    public Button(By locator, String elementName)
    {
        super(locator, elementName);
    }

    public Button(By locator)  { super(locator);}

    public void sendKey(String key) throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

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
        }
        browser.getWait().until(ExpectedConditions.invisibilityOfElementLocated(getLocator()));
    }
}
