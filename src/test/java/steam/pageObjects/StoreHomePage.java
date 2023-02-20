package steam.pageObjects;

import org.testng.Assert;

public class StoreHomePage extends SteamStorePage {
    @Override
    public String GetTitle() {
        return null;
    }

    public StoreHomePage(){
        super();
        Assert.assertTrue(browser.getBrowserUri().contains("store.steampowered.com/"));
        logger.info("steam.pageObjects.StoreHomePage.ctor()");
    }
}
