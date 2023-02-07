package framework.elements;

import model.TVCriterias;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class TextBoxGroup extends BaseElement {

    public TextBoxGroup(By locator, String elementName)
    {
        super(locator, elementName);
    }

    public TextBoxGroup(By locator)
    {
        super(locator);
    }
    @Override
    public void sendKey(String key) throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
