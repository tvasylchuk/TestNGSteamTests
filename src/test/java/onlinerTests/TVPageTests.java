package onlinerTests;

import onliner.pageObjects.TVPageObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

@Test
public class TVPageTests extends BaseTest{

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
    public void SetProducerFilterTest()
    {
        ArrayList<String> tvMakers = new ArrayList<>();
        tvMakers.add( tvMaker );

        browser.navigate("https://catalog.onliner.by/tv");

        var page = new TVPageObject();
        page.ConfirmLocation();
        page.SetProduceFilter(tvMakers);
        page.WaitForSearch();

        Assert.assertEquals(tvMakers.get(0), page.GetFilterTagText());
    }

    @Test
    public void SetResolutionFilterTest()
    {
        ArrayList<String> resolutions = new ArrayList<>();
        resolutions.add( tvResolution );

        browser.navigate("https://catalog.onliner.by/tv");

        var page = new TVPageObject();
        page.ConfirmLocation();
        page.SetResolutionFilter(resolutions);
        page.WaitForSearch();

        Assert.assertTrue(page.GetFilterTagText().contains(resolutions.get(0)));
    }

    @Test
    public void SetUpperPriceFilterTest()
    {
        browser.navigate("https://catalog.onliner.by/tv");

        var page = new TVPageObject();
        page.ConfirmLocation();
        page.SetTopPrice(price.toString());
        page.WaitForSearch();

        var txt = page.GetFilterTagText();
        Assert.assertTrue(txt.contains("до 1500"));
    }

    @Test
    public void SetDiagonalFilterTest()
    {
       //var diagonalRange = new Pair<>("40\"", "50\"");

        browser.navigate("https://catalog.onliner.by/tv");

        var page = new TVPageObject();
        page.ConfirmLocation();
        page.SetDiagonal(diagonalRange);
        page.WaitForSearch();

        Assert.assertEquals(page.GetFilterTagText(), diagonalRange.first()+" — "+diagonalRange.second());
    }
}
