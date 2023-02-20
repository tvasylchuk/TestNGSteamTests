package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import java.util.Arrays;

public class MainMenuItem extends BaseElement{

    public MainMenuItem(By locator, String elementName)
    {
        super(locator, elementName);
    }

    public MainMenuItem(By locator)
    {
        super(locator);
    }

    public void hoveOverMenu()
    {
        try {
            Actions action = new Actions(browser.getDriver());
            action.moveToElement(getElement()).perform();
            logger.info("framework.elements.MainMenuItem.hoveOverMenu.perform");
        }
        catch(Exception e)
        {
            logger.error("framework.elements.MainMenuItem.hoveOverMenu.failed");
            logger.error(e.getMessage());
            logger.error(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }

    }
}
