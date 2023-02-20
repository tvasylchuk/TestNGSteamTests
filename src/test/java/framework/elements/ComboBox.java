package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ComboBox extends BaseElement{

    public ComboBox(By locator, String elementName)
    {
        super(locator, elementName);
    }

    public ComboBox(By locator)  { super(locator);}

    public void sendKey(String key) {
        browser.getWait().until(ExpectedConditions.visibilityOfElementLocated(getLocator()));

        super.getElement().clear();
        super.getElement().sendKeys(key);
    }

    public void selectItemByValue(String value)
    {
        browser.getWait().until(ExpectedConditions.visibilityOfElementLocated(getLocator()));

        var selector = new Select(getElement());
        selector.selectByValue(value);
    }

    public void selectItemByText(String text)
    {
        browser.getWait().until(ExpectedConditions.visibilityOfElementLocated(getLocator()));

        var selector = new Select(getElement());
        selector.selectByVisibleText(text);
    }
}
