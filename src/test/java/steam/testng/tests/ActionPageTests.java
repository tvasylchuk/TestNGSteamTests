package steam.testng.tests;

import framework.Logger;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steam.pageObjects.ActionGamesPage;
import steam.pageObjects.AgeVerificationPage;
import steam.pageObjects.GamePage;

import java.util.ArrayList;

@Test
public class ActionPageTests extends BaseTest {

    private final ArrayList<String> args = new ArrayList<>();

    @Override
    @BeforeMethod()
    protected void initTestData() {
        initResourceManager("Local/PL.testdata.properties");
        args.add(resourceManager.getPropertyValueByKey("salesSection"));
    }

    public void getDiscountGamesTest() throws Exception {
        try
        {
            logStep();
            browser.navigate("https://store.steampowered.com/category/action/");
            browser.maximise();
            browser.waitPageToLoad();

            logStep();
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
            var gamePage = new GamePage(game.getGameName());
        }
        catch (Exception e)
        {
            Logger.getInstance().error(e.getMessage());
            Logger.getInstance().logScreenshot();
        }

    }
}

