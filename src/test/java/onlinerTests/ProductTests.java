package onlinerTests;

import framework.Logger;
import onliner.pageObjects.CatalogPage;
import onliner.pageObjects.TVPageObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

@Test
public class ProductTests extends BaseTest{

    @BeforeClass
    public void setUpTestData()
    {
        try {
            initTestData("TestData.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test()
    public void SelectProductTest() {
        Logger.loggerInstance.logTestName("onlinerTests.ProductTests.SelectProductTest");
        browser.navigate("https://catalog.onliner.by/");
        browser.maximise();
        browser.waitPageToLoad();
        CatalogPage page = new CatalogPage();
        page.selectProductFromCatalog(department, category, product);

        Assert.assertTrue(browser.getBrowserUri().contains("catalog.onliner.by/tv"));

        var tvPage = new TVPageObject();
        Assert.assertTrue(tvPage.GetTitle().contains( "Телевизоры"));
    }

}
