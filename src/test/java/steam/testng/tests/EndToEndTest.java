package steam.testng.tests;

import framework.Logger;
import framework.utils.FileManager;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steam.pageObjects.*;

import java.util.ArrayList;

@Test
public class EndToEndTest extends BaseTest {

    private String languageSettings = null;
    private String menuItem = null;
    private String subMenuItem = null;
    private String sectionName = null;

    private String btnInstallation = null;

    @BeforeMethod
    @Override
    protected void initTestData() {
        initResourceManager(TESTDATA_FILE_NAME);
        languageSettings = resourceManager.getPropertyValueByKey("language");
        menuItem = resourceManager.getPropertyValueByKey("storeMenu");
        subMenuItem = resourceManager.getPropertyValueByKey("categorySubMenu");
        sectionName = resourceManager.getPropertyValueByKey("salesSection");
        btnInstallation = resourceManager.getPropertyValueByKey("steamInstaller");
    }

    @Test
    public void steamGameEndToENdTest()
    {
        logTestName("steam.tests.EndToEndTest.steamGameEndToENdTest");
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
        try
        {
            page.coockiesPage.acceptAllCoockies();
        }
        catch(TimeoutException e)
        {
            Logger.getInstance().warn("AcceptCookiesPage.not.found");
        }

        logStep();
        var game = page.findCheapestActionGame();
        page.selectGameFromOffered(game);
        browser.waitPageToLoad();
        if (browser.getBrowserUri().contains("agecheck"))
        {
            var ageCheckPage = new AgeVerificationPage();
            ageCheckPage.confirmAge("1", "January", "1984");
        }
        browser.waitPageToLoad();

        logStep();
        var gamePage = new GamePage(game.getGameName());
        gamePage.mainMenuPC.installSteam();

        logStep();
        args.clear();
        args.add(btnInstallation);
        var aboutPage = new AboutSteamPage(args);
        aboutPage.downloadInstallFile();

        Assert.assertTrue(FileManager.fileExists(browser.getBrowserDownloadDirectory()+"\\SteamSetup.exe"), "File SteamSetup.exe was not found.");
    }
}
