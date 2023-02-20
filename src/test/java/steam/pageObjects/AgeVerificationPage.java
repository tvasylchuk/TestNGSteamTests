package steam.pageObjects;

import framework.elements.Button;
import framework.elements.ComboBox;
import framework.pageObjects.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class AgeVerificationPage extends SteamBasePage {
    @Override
    public String GetTitle() { return null; }
    private final ComboBox cmBoxDay = new ComboBox(By.id("ageDay"));
    private final ComboBox cmBoxMonth = new ComboBox(By.id("ageMonth"));
    private final ComboBox cmBoxYear = new ComboBox(By.id("ageYear"));
    private Button btnViewPage = new Button(By.id("view_product_page_btn"));

    public AgeVerificationPage(){
        super();
        Assert.assertTrue(browser.getBrowserUri().contains("store.steampowered.com/agecheck"));
        logger.info("steam.pageObjects.AgeVerificationPage.ctor()");
    }

    public void confirmAge(String day, String month, String year)
    {
        logger.info("steam.pageObjects.AgeVerificationPage.confirmAge");
        cmBoxDay.selectItemByValue(day);
        cmBoxMonth.selectItemByValue(month);
        cmBoxYear.selectItemByValue(year);
        btnViewPage.click();
    }
}
