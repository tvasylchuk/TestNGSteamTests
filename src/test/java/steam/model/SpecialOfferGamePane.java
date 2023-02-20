package steam.model;

import framework.elements.Label;
import framework.utils.GamePaneComparator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GamePane {

    private int lbDiscount;
    private float lbPrice;
    private Label linkGame;
    private String gameName;
    private String scrollBarPosition;
    private final WebElement rootElement;
    public static GamePaneComparator comparator = new GamePaneComparator();

    public GamePane(WebElement rootElement) {
        this.rootElement = rootElement;
    }

    public void setDiscount(By locator) {
        try{
            String str = new Label(locator, rootElement).getElement().getText();
            lbDiscount = Integer.parseInt(str.substring(1, str.indexOf("%")));
        }
        catch(Exception e)
        {
            lbDiscount=0;
        }
    }

    public void setLbPrice(By locator) {
        try {
            String str = new Label(locator, rootElement).getElement().getText();
            if (str.equals("Free To Play") || str.isEmpty()) {
                lbPrice = 0;
            }
            else{
                lbPrice = Float.parseFloat(str.replace(",", ".").substring(0, str.indexOf(" ")));
            }
        }
        catch(Exception e)
        {
            lbPrice=0;
        }
    }

    public void setLinkGame(By locator){
        linkGame = new Label(locator, rootElement);
    }

    public void setName(By locator){
        var element =  new Label(locator, rootElement).getElement();
        gameName = element.getAttribute("alt");
    }

    public void setScrollBarPosition(String position){
        scrollBarPosition = position;
    }

    public int getLbDiscount() { return lbDiscount; }

    public float getLbPrice() { return lbPrice; }

    public Label getLinkGame() { return linkGame; }

    public String getGameName() { return gameName; }

    public String getScrollBar() { return scrollBarPosition; }
}
