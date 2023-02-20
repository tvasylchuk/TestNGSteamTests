package steam.pageObjects;

import framework.elements.Label;
import framework.utils.FileManager;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;

public class AboutSteamPage extends SteamBasePage{

    private final String installText;

    private Label btnInstallFile(String text)
    {
        return new Label(By.xpath(String.format("//div[@id='about_greeting']//a[contains(text(), '%s')]", text)));
    }

    public AboutSteamPage(ArrayList<String> args)
    {
        super();
        installText = args.get(0);
        Assert.assertTrue(browser.getBrowserUri().contains("store.steampowered.com/about/"));
        logger.info("steam.pageObjects.AboutSteamPage.ctr()");
    }

    public void downloadInstallFile()
    {
        var str = browser.getBrowserDownloadDirectory()+"\\SteamSetup.exe";
        FileManager.deleteOldFiles(str);

        btnInstallFile(installText).click();
        FileManager.waitTillFIleDownloadingStarted(browser.getBrowserDownloadDirectory(), ".tmp");
        FileManager.waitTillFIleDownloaded(browser.getBrowserDownloadDirectory(), ".tmp");
    }
}
