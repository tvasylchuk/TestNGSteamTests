package steam.pageComponents;

import framework.elements.Button;
import framework.elements.List;
import framework.elements.ScrollBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import steam.model.SpecialOfferGamePane;

import java.util.ArrayList;

public class GameCarouselPageComponent {

    private final String locator = "//div[text()='%s']/ancestor::div[starts-with(@id, 'SaleSection')]";
    private By discountGamesList(String arg) {
        return By.xpath(String.format(locator+"//div[@class='carousel']", arg));
    }
    private By leftButtonLocator(String arg) {
        return By.xpath(String.format(locator+"//div[contains(@class,'SliderBody')]//button[@aria-label='previous']", arg));
    }
    private By rightButtonLocator(String arg)    {
        return By.xpath(String.format(locator+"//div[contains(@class,'SliderBody')]//button[@aria-label='next']", arg));
    }
    private By scrollBarLocator(String arg){
        return By.xpath(String.format(locator+"//div[starts-with(@class, 'carousel_scrollForeground')]", arg));
    }
    private Button leftButton;

    private Button rightButton;

    private ScrollBar scrollingBar;

    private List listCarousel;

    private GameCarouselPageComponent() {
    }

    public static GameCarouselPageComponent initCarouselElements(String arg)
    {
        var page = new GameCarouselPageComponent();
        page.setCarouselList(arg);
        page.setCarouselScrollBar(arg);
        page.setLeftButton(arg);
        page.setRightButton(arg);
        return page;
    }

    private void setRightButton(String arg) { rightButton = new Button(rightButtonLocator(arg)); }

    private void setLeftButton(String arg) { leftButton = new Button(leftButtonLocator(arg)); }

    private void setCarouselScrollBar(String arg) { scrollingBar = new ScrollBar(scrollBarLocator(arg)); }
    private void setCarouselList(String arg) { listCarousel = new List(discountGamesList(arg), "Special offer games list"); }

    public Button getRightButton() { return rightButton; }

    public Button getLeftButton() { return leftButton; }

    public ScrollBar getCarouselScrollBar() { return scrollingBar; }

    public ArrayList<SpecialOfferGamePane> getGameFromCarousel(By gameLocator)
    {
        float count;
        ArrayList<SpecialOfferGamePane> result = new ArrayList<>();
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
        games.sort(SpecialOfferGamePane.comparator);

        var maxDiscount = games.get(games.size()-1);

        java.util.List<SpecialOfferGamePane> chipGames = games.stream()
                .filter(c -> c.getLbDiscount() == maxDiscount.getLbDiscount())
                .toList();

        int index = (int)(Math.random() * chipGames.size());
        return chipGames.get(index);
    }

    public void selectGame(SpecialOfferGamePane game)
    {
        while(!(game.getScrollBar().equals(scrollingBar.getElement().getAttribute("style"))))
        {
            leftButton.click();
        }

        game.getLinkGame().click();
    }

}
