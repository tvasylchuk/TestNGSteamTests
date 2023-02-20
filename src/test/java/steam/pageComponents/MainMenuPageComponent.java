package steam.pageComponents;

import framework.Logger;
import framework.elements.Button;
import framework.elements.MainMenuItem;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.util.Arrays;

public class MainMenuPageComponent {

    private final static Logger logger = Logger.getInstance();
    private final MainMenuItem languageMenu = new MainMenuItem(By.cssSelector("span.pulldown.global_action_link"), "Language main menu");

    private MainMenuItem language(String lang) {
        return new MainMenuItem(By.xpath(String.format("//a[@class='popup_menu_item tight' and contains(@onclick, '%s')]", lang)));
    }

    private Button installSteam = new Button(By.xpath(String.format("//div[@id='global_actions']//a[@class='header_installsteam_btn_content']")));

    public MainMenuPageComponent(){
        logger.info(MainMenuPageComponent.class.getName());
    }

    public void switchLanguage(String code)
    {
        try {
            languageMenu.click();
            logger.info("steam.pageComponents.MainMenuPageComponent.switchLanguage.languageMenu.click");
            if(language(code).exists())
            {
                language(code).click();
            }
            else
            {
                languageMenu.click();
            }

            logger.info(String.format("steam.pageComponents.MainMenuPageComponent.switchLanguage.language.click: %s", code));
        }
        catch(NoSuchElementException elementException) {
            logger.error("steam.pageComponents.MainMenuPageComponent.switchLanguage");
            logger.error(elementException.getMessage());
            logger.error(Arrays.toString(elementException.getStackTrace()));
            throw new RuntimeException(elementException);
        }
    }

    public void installSteam()
    {
        installSteam.click();
    }
}
