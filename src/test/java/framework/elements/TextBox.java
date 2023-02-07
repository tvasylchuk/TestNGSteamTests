package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TextBox extends BaseElement{

    public TextBox(By locator, String elementName)
    {
        super(locator, elementName);
    }

    public TextBox(By locator)  { super(locator);}
    
    @Override
    public void sendKey(String key) {
        browser.getWait().until(ExpectedConditions.visibilityOfElementLocated(getLocator()));

        super.getElement().clear();
        super.getElement().sendKeys(key);
    }
}
