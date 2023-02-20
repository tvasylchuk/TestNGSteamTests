package steam.pageComponents;

import framework.Logger;
import framework.elements.Button;
import framework.elements.List;
import framework.elements.ScrollBar;
import org.openqa.selenium.By;
import steam.model.GamePane;
import steam.model.SpecialOfferGamePane;

import java.util.ArrayList;

public abstract class CarouselPageComponent {

    protected static Logger logger = Logger.getInstance();
    protected abstract String baseLocator(String str);

    protected By gamesList(String arg) {
        return By.xpath(baseLocator(arg)+"//div[@class='carousel']");
    }

    protected By leftButtonLocator(String arg) {
        return By.xpath(baseLocator(arg)+"//div[contains(@class,'SliderBody')]//button[@aria-label='previous']");
    }
    protected By rightButtonLocator(String arg)    {
        return By.xpath(baseLocator(arg)+"//div[contains(@class,'SliderBody')]//button[@aria-label='next']");
    }
    protected By scrollBarLocator(String arg){
        return By.xpath(baseLocator(arg)+"//div[starts-with(@class, 'carousel_scrollForeground')]");
    }

    protected Button leftButton;

    protected Button rightButton;

    protected ScrollBar scrollingBar;

    protected List listCarousel;

    public void setRightButton(String arg) { rightButton = new Button(rightButtonLocator(arg)); }

    public void setLeftButton(String arg) { leftButton = new Button(leftButtonLocator(arg)); }

    public void setCarouselScrollBar(String arg) { scrollingBar = new ScrollBar(scrollBarLocator(arg)); }
    public void setCarouselList(String arg) { listCarousel = new List(gamesList(arg), "Special offer games list"); }

    public Button getRightButton() { return rightButton; }

    public Button getLeftButton() { return leftButton; }

    public ScrollBar getCarouselScrollBar() { return scrollingBar; }

    abstract <T extends CarouselPageComponent> ArrayList<T> getGameFromCarousel(By gameLocator);
}
