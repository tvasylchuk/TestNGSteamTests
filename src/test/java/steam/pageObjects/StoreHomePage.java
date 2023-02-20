package steam.pageObjects;

import org.testng.Assert;

public class StoreMainPage extends SteamBasePage {
    @Override
    public String GetTitle() {
        return null;
    }

    public StoreMainPage(){
        super();
        Assert.assertTrue(browser.getBrowserUri().contains("store.steampowered.com/"));
        logger.info("steam.pageObjects.StoreMainPage.ctor()");
    }
}
