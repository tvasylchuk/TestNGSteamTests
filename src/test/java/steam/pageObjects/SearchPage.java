package steam.pageObjects;

import framework.elements.Label;
import org.openqa.selenium.By;

public class SearchPage extends SteamStorePage{

    private By searchItemLocator(String text) {
        return By.xpath(String.format("//div[@id='search_results']//span[text()='%s']", text));
    }

    private Label searchItem(String text) {
        return new Label(searchItemLocator(text));
    }

    public void selectSearchedItem(String text)
    {
        searchItem(text).click();
    }
}
