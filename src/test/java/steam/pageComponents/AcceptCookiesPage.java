package steam.pageComponents;

import framework.elements.Button;
import framework.pageObjects.BasePage;
import org.openqa.selenium.By;

public class AcceptCookiesPage extends BasePage {
    private final Button btnAcceptAll = new Button(By.xpath("//div[@id='acceptAllButton']/span"));

    private final Button btnRejectAll = new Button(By.xpath("//div[@id='rejectAllButton']/span"));

    public void acceptAllCoockies()
    {
        logger.error("steam.pageObjects.AcceptCookiesPage.acceptAllCoockies()");
        btnAcceptAll.click();
    }

    public void rejectAllCoockies()
    {
        logger.error("steam.pageObjects.AcceptCookiesPage.rejectAllCoockies()");
        btnRejectAll.click();
    }

    @Override
    public String GetTitle() { return null;  }
}
