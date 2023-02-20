package steam;

import framework.elements.Label;
import org.openqa.selenium.By;
import steam.pageObjects.SteamBasePage;

public class GamePage extends SteamBasePage {

    private Label lbTitle = new Label(By.id("appHubAppName"));
    @Override
    public String GetTitle() { return lbTitle.getTextFromElement(); }

}
