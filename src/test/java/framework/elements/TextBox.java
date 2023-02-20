package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TextBox extends BaseElement{

    public TextBox(By locator, String elementName)
    {
        super(locator, elementName);
    }

    public TextBox(By locator)  { super(locator);}

    public void sendKey(String key) {
        browser.getWait().until(ExpectedConditions.visibilityOfElementLocated(getLocator()));
        logger.info(String.format("framework.elements.TextBox.sendKey : %s", key));
        super.getElement().clear();
        super.getElement().sendKeys(key);
    }
}
