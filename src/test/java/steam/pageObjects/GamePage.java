package steam.pageObjects;

import framework.elements.Label;
import org.openqa.selenium.By;
import org.testng.Assert;

public class GamePage extends SteamStorePage {

    private Label lbTitle = new Label(By.id("appHubAppName"));
    @Override
    public String GetTitle() { return lbTitle.getTextFromElement(); }

    public GamePage(String gameName)
    {
        super();
        Assert.assertTrue(this.GetTitle().contains(gameName));
        logger.info("steam.pageObjects.GamePage.ctr()");
    }

}
