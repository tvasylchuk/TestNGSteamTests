package onlinerTests;

import framework.Logger;
import onliner.pageObjects.CatalogPage;
import onliner.pageObjects.MainPage;
import onliner.pageObjects.TVPageObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.internal.collections.Pair;

import java.io.FileNotFoundException;
import java.util.ArrayList;

@Test
public class EndToEndTests extends  BaseTest {

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
    public void checkSearchResultsTest() throws FileNotFoundException {
        Logger.loggerInstance.logTestName("onlinerTests.EndToEndTests.checkSearchResultsTest");
        var mainPage = new MainPage();
        mainPage.SelectMenu(catalog);

        CatalogPage catalogPage = new CatalogPage();
        catalogPage.selectProductFromCatalog(department, category, product);

        var tvPage = new TVPageObject();
        tvPage.ConfirmLocation();

        ArrayList<String> tvMakers = new ArrayList<>();
        tvMakers.add( tvMaker );

        ArrayList<String> resolutions = new ArrayList<>();
        resolutions.add( tvResolution );

        var diagonal = new Pair<>(diagonalRange.first(), diagonalRange.second());

        tvPage.SetProduceFilter(tvMakers);
        tvPage.SetTopPrice(price.toString());
        tvPage.SetResolutionFilter(resolutions);
        tvPage.SetDiagonal(diagonal);
        tvPage.WaitForSearch();

        var result = tvPage.getSearchResult();
        for (var item : result)
        {
            Assert.assertTrue(item.getPrice()<=price);
            Assert.assertTrue(item.getResolution().equals(resolutions.get(0)));
            Assert.assertTrue(item.getMaker().equals(tvMakers.get(0)));
            Assert.assertTrue(item.getDiagonal()>=40);
            Assert.assertTrue(item.getDiagonal()<=50);
        }
    }
}
