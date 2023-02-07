package framework.pageObjects;

import framework.driver.Browser;
import onliner.pageComponents.MainMenuPageComponent;

public abstract class BasePage {

    protected static Browser browser = null;

    protected MainMenuPageComponent _mainMenuPC = null;

    protected BasePage()
    {
        _mainMenuPC = new MainMenuPageComponent();
        browser = Browser.getInstance();
    }

    public void SelectMenu(String menuName)
    {
        _mainMenuPC.ClickMenuItem(menuName);
    }

    public abstract String GetTitle() throws CloneNotSupportedException;
}
