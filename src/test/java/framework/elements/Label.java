package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Label extends BaseElement
{
    public Label(By locator, String elementName)
    {
        super(locator, elementName);
    }

    public Label(By locator)  { super(locator);}

    public Label(By locator, WebElement rootElement)  { super(locator, rootElement);}

    public String getChildElementText(By locator)
    {
        browser.getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
        WebElement element = getElement().findElement(locator);
        return element.getText();
    }
}
