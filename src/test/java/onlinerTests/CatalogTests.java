package onlinerTests;

import framework.Logger;
import onliner.pageObjects.CatalogPage;
import onliner.pageObjects.MainPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

@Test
public class CatalogTests extends BaseTest{

    @BeforeClass
    public void setUpTestData()
    {
        try {
            initTestData("TestData.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void navigateToCatalogTest()
    {
        Logger.loggerInstance.logTestName("onlinerTests.CatalogTests.navigateToCatalogTest");
        browser.maximise();
        browser.waitPageToLoad();
        var mainPage = new MainPage();
        mainPage.SelectMenu(catalog);

        Assert.assertEquals(browser.getBrowserUri(), "https://catalog.onliner.by/");
        Reporter.log("Каталог page is opened.", true);

        var catalogPage = new CatalogPage();
        Assert.assertTrue(catalogPage.GetTitle().contains(catalog));
    }
}
