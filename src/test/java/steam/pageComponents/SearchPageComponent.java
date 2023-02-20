package steam.pageComponents;

import framework.Logger;
import framework.elements.Button;
import framework.elements.TextBox;
import org.openqa.selenium.By;

public class SearchPageComponent {

    private Logger logger = Logger.getInstance();
    private TextBox txtSearch = new TextBox(By.xpath("//div[@class='searchbox']//input"));
    private Button btnSearch = new Button(By.xpath("//div[@class='searchbox']//img"));

    public void searchForGame(String gameName)
    {
        logger.info("steam.pageComponents.SearchPageComponent.searchForGame");
        txtSearch.sendKey(gameName);
        btnSearch.click();
    }
}
