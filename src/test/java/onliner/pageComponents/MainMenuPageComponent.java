package onliner.pageComponents;

import framework.Logger;
import framework.driver.Browser;
import framework.elements.MainMenuItem;
import org.openqa.selenium.By;

import java.util.Arrays;

public class MainMenuPageComponent {
    private final Browser browser = Browser.getInstance();
    private final Logger logger = Logger.getInstance();

    public MainMenuPageComponent()
    {
    }

    public void ClickMenuItem(String text)
    {
        try {
            By locator = By.linkText(text);
            MainMenuItem menuItem = new MainMenuItem(locator);
            menuItem.click();
        }
        catch (Exception e){
            logger.error("onliner.pageComponents.MainMenuPageComponent.ClickMenuItem");
            logger.error(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }

    }
}
