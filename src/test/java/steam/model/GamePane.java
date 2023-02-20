package steam.model;

import framework.elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class GamePane {

    protected final By gameLinkLocator = By.xpath(".//a");
    protected final By gameNameLocator = By.xpath(".//img");
    protected Label linkGame;
    protected String gameName;
    protected WebElement rootElement;

    protected GamePane(WebElement element)
    {
        this.rootElement = element;
    }

    public void setLinkGame(){
        linkGame = new Label(gameLinkLocator, rootElement);
    }

    public void setName(){
        var element =  new Label(gameNameLocator, rootElement).getElement();
        gameName = element.getAttribute("alt");
    }

    public Label getLinkGame() { return linkGame; }

    public String getGameName() { return gameName; }
}
