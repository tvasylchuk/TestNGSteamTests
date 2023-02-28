package onlinerTests;

import framework.Logger;
import onliner.pageObjects.CatalogPage;
import onliner.pageObjects.MainPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

@Test
public class CatalogTests extends BaseTest{

    @Test
    public void navigateToCatalogTest()
    {
        Logger.loggerInstance.logTestName("onlinerTests.CatalogTests.navigateToCatalogTest");
        var mainPage = new MainPage();
        mainPage.SelectMenu("Каталог");

        Assert.assertEquals(browser.getBrowserUri(), "https://catalog.onliner.by/");
        Reporter.log("Каталог page is opened.", true);

        var catalogPage = new CatalogPage();
        Assert.assertTrue(catalogPage.GetTitle().contains( "Каталог"));
    }
}
