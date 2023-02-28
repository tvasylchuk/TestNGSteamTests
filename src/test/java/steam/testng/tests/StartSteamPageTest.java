package steam.testng.tests;

import framework.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steam.pageObjects.ActionGamesPage;
import steam.pageObjects.StoreHomePage;

import java.util.ArrayList;

@Test
public class StartSteamPageTest extends BaseTest {
    private String languageSettings = null;
    private String menuItem = null;
    private String subMenuItem = null;
    private String sectionName = null;

    @Override
    @BeforeMethod()
    protected void initTestData() {
        initResourceManager(TESTDATA_FILE_NAME);
        languageSettings = resourceManager.getPropertyValueByKey("language");
        menuItem = resourceManager.getPropertyValueByKey("storeMenu");
        subMenuItem = resourceManager.getPropertyValueByKey("categorySubMenu");
        sectionName = resourceManager.getPropertyValueByKey("salesSection");
    }

    @Test
    public void changeLanguageTest() throws Exception {
        try
        {
            logTestName("steam.tests.StartSteamPageTest.changeLanguageTest");
            logStep();
            browser.navigate("https://store.steampowered.com/");
            browser.maximise();
            StoreHomePage mainPage = new StoreHomePage();
            logStep();
            mainPage.mainMenuPC.switchLanguage(languageSettings);
            browser.waitPageToLoad();
            logStep();
            mainPage.navigationPC.navigateMenu(menuItem, subMenuItem);
            browser.waitPageToLoad();
            logStep();
            var args = new ArrayList<String>();
            args.add(sectionName);
            ActionGamesPage page = new ActionGamesPage(args);
        }
        catch (Exception e)
        {
            Logger.getInstance().error(e.getMessage());
            Logger.getInstance().logScreenshot();
            throw new Exception(e);
        }
    }
}
