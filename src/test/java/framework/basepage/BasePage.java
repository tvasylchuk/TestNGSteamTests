package framework.basepage;

import framework.driver.Browser;

public abstract class BasePage {

    private static Browser browser = null;

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
