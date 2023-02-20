package steam.pageObjects;

import steam.pageComponents.SearchPageComponent;
import steam.pageComponents.StoreNavigationPageComponent;

public abstract class SteamStorePage extends SteamBasePage{

    public SearchPageComponent searchPC;
    public StoreNavigationPageComponent navigationPC;

    public SteamStorePage()
    {
        super();
        searchPC = new SearchPageComponent();
        navigationPC = new StoreNavigationPageComponent();
    }
}
