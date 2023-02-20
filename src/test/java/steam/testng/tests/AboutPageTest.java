package steam.tests;

import framework.utils.FileManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steam.pageObjects.AboutSteamPage;
import steam.pageObjects.StoreHomePage;
import steam.tests.BaseTest;

import java.util.ArrayList;

public class AboutPageTest extends BaseTest {
    private final ArrayList<String> args = new ArrayList<>();

    @Override
    @BeforeMethod()
    protected void initTestData() {
        initResourceManager("Local/PL.testdata.properties");
        args.add(resourceManager.getPropertyValueByKey("steamInstaller"));
    }

    @Test
    public void steamInstallationTest()
    {
        logTestName("steam.tests.AboutPageTest.steamInstallationTest");
        logStep();
        browser.navigate("https://store.steampowered.com/");
        browser.maximise();
        StoreHomePage mainPage = new StoreHomePage();
        logStep();
        mainPage.mainMenuPC.installSteam();
        var aboutPage = new AboutSteamPage(args);
        aboutPage.downloadInstallFile();

        Assert.assertTrue(FileManager.fileExists(browser.getBrowserDownloadDirectory()+"\\SteamSetup.exe"), "File SteamSetup.exe was not found.");
    }
}
