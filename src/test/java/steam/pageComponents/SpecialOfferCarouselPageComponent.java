package steam.pageComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import steam.model.SpecialOfferGamePane;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class SpecialOfferCarouselPageComponent extends CarouselPageComponent {

    @Override
    protected String baseLocator(String arg){
        return String.format("//div[text()='%s']/ancestor::div[starts-with(@id, 'SaleSection')]", arg);
    }

    public void selectGame(SpecialOfferGamePane game) {
        int count =0;
        while(!(game.getScrollBar().equals(scrollingBar.getElement().getAttribute("style"))) && count<10)
        {
            logger.info("Scrollbar: "+scrollingBar.getElement().getAttribute("style"));
            logger.info("Game scrollbar position: "+game.getScrollBar());
            rightButton.click();
            count+=1;
        }
        logger.info("steam.pageComponents.SpecialOfferCarouselPageComponent.selectGame.getLinkGame");
        game.getLinkGame().click();
    }

    private SpecialOfferCarouselPageComponent() {
        super();
    }

    public static SpecialOfferCarouselPageComponent initCarouselElements(String arg)
    {
        var page = new SpecialOfferCarouselPageComponent();
        page.setCarouselList(arg);
        page.setCarouselScrollBar(arg);
        page.setLeftButton(arg);
        page.setRightButton(arg);
        return page;
    }

    @Override
    public ArrayList<SpecialOfferGamePane> getGameFromCarousel(By gameLocator)
    {
        float count;
        ArrayList<SpecialOfferGamePane> result = new ArrayList<>();
        logger.info("steam.pageComponents.SpecialOfferCarouselPageComponent.getGameFromCarousel");

        do
        {
            count = scrollingBar.getScrollRightStyle();
            var items = listCarousel.getAllItems(gameLocator);
            for (WebElement item : items) {
                result.add(initGame(item));
            }
            rightButton.click();
        }
        while(count>0);
        return result;
    }

    private SpecialOfferGamePane initGame(WebElement parentElement)
    {
        var game = new SpecialOfferGamePane(parentElement);

        logger.info("steam.pageComponents.SpecialOfferCarouselPageComponent.initGame");
        game.setLbPrice();
        game.setDiscount();
        game.setLinkGame();
        game.setName();
        game.setScrollBarPosition(scrollingBar.getElement().getAttribute("style"));
        return game;
    }

    public SpecialOfferGamePane getCheapestGame(By gameLocator)
    {
        var games = getGameFromCarousel(gameLocator);
        logger.info("steam.pageComponents.SpecialOfferCarouselPageComponent.getCheapestGame");
        games.sort(SpecialOfferGamePane.comparator);
        var maxDiscount = games.get(games.size()-1);

        java.util.List<SpecialOfferGamePane> chipGames = games.stream()
                .filter(c -> c.getLbDiscount() == maxDiscount.getLbDiscount())
                .toList();

        int index = (int)(Math.random() * chipGames.size());
        return chipGames.get(index);
    }

}
