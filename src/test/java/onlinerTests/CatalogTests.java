package onlinerTests;

import framework.basepage.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class CatalogTests extends BaseTest{

    @Test
    public void navigateToCatalogTest()
    {
        var mainPage = new MainPage();
        mainPage.SelectMenu("Каталог");

        Assert.assertEquals(browser.getBrowserUri(), "https://catalog.onliner.by/");
    }
}
