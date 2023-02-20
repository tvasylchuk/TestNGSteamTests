package framework.elements;

import org.openqa.selenium.By;

public class TextBoxGroup extends BaseElement {

    public TextBoxGroup(By locator, String elementName)
    {
        super(locator, elementName);
    }

    public TextBoxGroup(By locator)
    {
        super(locator);
    }
}
