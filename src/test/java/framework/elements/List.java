package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class List extends BaseElement{

    public List(By locator, String elementName) {
        super(locator, elementName);
    }

    public List(By locator, WebElement root) {
        super(locator, root);
    }

    public java.util.List<WebElement> getAllItems(By locator)
    {
        return getElement().findElements(locator);
    }

    public WebElement getItem(By locator, int index)
    {
        var items = getAllItems(locator);
        return items.get(index);
    }
}
