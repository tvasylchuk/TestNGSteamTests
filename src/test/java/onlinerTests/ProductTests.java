package onlinerTests;

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
    public void SelectProductTest() throws FileNotFoundException {
        browser.navigate("https://catalog.onliner.by/");
        CatalogPage page = new CatalogPage();
        page.selectProductFromCatalog(department, category, product);

        Assert.assertTrue(browser.getBrowserUri().contains("catalog.onliner.by/tv"));

        var tvPage = new TVPageObject();
        Assert.assertTrue(tvPage.GetTitle().contains( "Телевизоры"));
    }

}
