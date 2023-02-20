package steam.model;

import framework.elements.Label;
import framework.utils.GamePaneComparator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SpecialOfferGamePane extends GamePane {
    private final By gamePriceLocator = By.xpath(".//div[contains(@class, 'StoreSalePriceBox')]");
    private final By gameDiscounterLocator = By.xpath(".//div[contains(@class, 'StoreSaleDiscountBox')]");
    private int lbDiscount;
    private float lbPrice;
    private String scrollBarPosition;
    public static GamePaneComparator comparator = new GamePaneComparator();

    public SpecialOfferGamePane(WebElement rootElement) {
        super(rootElement);
    }

    public void setDiscount() {
        try{
            String str = new Label(gameDiscounterLocator, rootElement).getElement().getText();
            lbDiscount = Integer.parseInt(str.substring(1, str.indexOf("%")));
        }
        catch(Exception e)
        {
            lbDiscount=0;
        }
    }

    public void setLbPrice() {
        try {
            String str = new Label(gamePriceLocator, rootElement).getElement().getText();
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

    public void setScrollBarPosition(String position){
        scrollBarPosition = position;
    }
    public int getLbDiscount() { return lbDiscount; }
    public float getLbPrice() { return lbPrice; }
    public String getScrollBar() { return scrollBarPosition; }
}
