package framework.pageComponents;

import framework.driver.Browser;
import framework.elements.MainMenuItem;
import org.openqa.selenium.By;

public class MainMenuPageComponent {
    private final Browser browser = Browser.getInstance();

    public MainMenuPageComponent()
    {
    }

    public void ClickMenuItem(String text)
    {
        By locator = By.linkText(text);
        MainMenuItem menuItem = new MainMenuItem(locator);
        menuItem.click();
    }
}
