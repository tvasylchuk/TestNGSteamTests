package steam.testng.tests;

import framework.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steam.pageObjects.AgeVerificationPage;
import steam.pageObjects.GamePage;
import steam.pageObjects.SearchPage;
import steam.pageObjects.StoreHomePage;

import java.util.ArrayList;

@Test
public class SearchGameTest extends BaseTest {

    private final ArrayList<String> args = new ArrayList<>();

    @BeforeMethod
    @Override
    protected void initTestData() {
        initResourceManager("Local/PL.testdata.properties");
        args.add(resourceManager.getPropertyValueByKey("gameToSearch"));
    }

    @Test
    public void searchAdultGameTest() throws Exception {
        try
        {
            logTestName("steam.tests.StartSteamPageTest.changeLanguageTest");
            logStep();
            browser.navigate("https://store.steampowered.com/");
            browser.maximise();
            StoreHomePage mainPage = new StoreHomePage();
            logStep();
            mainPage.searchPC.searchForGame(args.get(0));
            browser.waitPageToLoad();
            var searchPage = new SearchPage();
            searchPage.selectSearchedItem(args.get(0));
            browser.waitPageToLoad();
            var agePage = new AgeVerificationPage();
            agePage.confirmAge("28", "February", "1985");
            browser.waitPageToLoad();
            var gamePage = new GamePage(args.get(0));
            gamePage.mainMenuPC.installSteam();
        }
        catch (Exception e)
        {
            Logger.getInstance().error(e.getMessage());
            Logger.getInstance().logScreenshot();
        }

    }

}
