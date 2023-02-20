package steam.pageObjects;

import framework.pageObjects.BasePage;
import steam.pageComponents.AcceptCookiesPage;
import steam.pageComponents.MainMenuPageComponent;
import steam.pageComponents.SearchPageComponent;

public abstract class SteamBasePage extends BasePage {
    @Override
    public String GetTitle() { return null; }

    public MainMenuPageComponent mainMenuPC;

    public AcceptCookiesPage coockiesPC;

    protected SteamBasePage() {
        super();
        mainMenuPC = new MainMenuPageComponent();
    }
}
