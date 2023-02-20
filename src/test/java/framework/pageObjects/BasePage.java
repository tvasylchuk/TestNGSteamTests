package framework.pageObjects;

import framework.Logger;
import framework.driver.Browser;
import onliner.pageComponents.MainMenuPageComponent;

public abstract class BasePage {

    protected static Browser browser = null;
    protected static Logger logger = Logger.getInstance();

    protected MainMenuPageComponent _mainMenuPC = null;

    protected BasePage()
    {
        _mainMenuPC = new MainMenuPageComponent();
        browser = Browser.getInstance();
    }

    public void SelectMenu(String menuName)
    {
        _mainMenuPC.ClickMenuItem(menuName);
        logger.info("framework.pageObjects.BasePage.SelectMenu");
    }

    public abstract String GetTitle() throws CloneNotSupportedException;

}
