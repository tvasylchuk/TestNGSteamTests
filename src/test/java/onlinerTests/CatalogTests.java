package onlinerTests;

import onliner.pageObjects.CatalogPage;
import onliner.pageObjects.MainPage;
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

        var catalogPage = new CatalogPage();
        Assert.assertTrue(catalogPage.GetTitle().contains( "Каталог"));
    }
}
