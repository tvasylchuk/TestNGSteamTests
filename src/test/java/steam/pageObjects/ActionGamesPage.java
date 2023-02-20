package steam.pageObjects;

import org.testng.Assert;
import steam.pageComponents.SpecialOfferCarouselPageComponent;
import steam.model.SpecialOfferGamePane;
import framework.elements.Label;
import org.openqa.selenium.By;
import steam.pageComponents.AcceptCookiesPage;

import java.util.ArrayList;

public class ActionGamesPage extends SteamStorePage {

    private final String specialOfferText;

    private final Label title = new Label(By.xpath("//div[contains(@class, 'contenthubmaincarousel_ContentHubTitle') and text()='Action']"), "Title");

    private final By specialOfferGamesLocator = By.xpath(".//div[@tabindex='0' and @aria-selected='true']//div[contains(@class, 'SpecialsItem')]");

    public AcceptCookiesPage coockiesPage = new AcceptCookiesPage();

    public ActionGamesPage(ArrayList<String> args)
    {
        super();
        specialOfferText = args.get(0);
        Assert.assertTrue(browser.getBrowserUri().contains("store.steampowered.com/category/action/"));
        logger.info("steam.pageObjects.ActionGamesPage.ctr()");
    }

    @Override
    public String GetTitle() { return title.getTextFromElement(); }

    public SpecialOfferGamePane findCheapestActionGame() {
        SpecialOfferCarouselPageComponent page =  SpecialOfferCarouselPageComponent.initCarouselElements(specialOfferText);
        logger.info("steam.pageObjects.ActionGamesPage.findCheapestActionGame");
        return page.getCheapestGame(specialOfferGamesLocator);
    }

    public void selectGameFromOffered(SpecialOfferGamePane game)
    {
        SpecialOfferCarouselPageComponent page =  SpecialOfferCarouselPageComponent.initCarouselElements(specialOfferText);
        logger.info("steam.pageObjects.ActionGamesPage.selectGameFromOffered");
        page.selectGame(game);
        logger.info(String.format("steam.pageObjects.ActionGamesPage.selectGameFromOffered: %s", game.getGameName()));
    }
}
