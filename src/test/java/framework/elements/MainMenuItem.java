package framework.elements;

import org.openqa.selenium.By;

public class MainMenuItem extends BaseElement{

    public MainMenuItem(By locator, String elementName)
    {
        super(locator, elementName);
    }

    public MainMenuItem(By locator)
    {
        super(locator);
    }
    @Override
    public void sendKey(String key) throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
