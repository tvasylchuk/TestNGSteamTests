package steam.pageComponents;

import framework.Logger;
import framework.elements.MainMenuItem;
import org.openqa.selenium.By;

public class StoreNavigationPageComponent {

    private final static Logger logger = Logger.getInstance();

    private MainMenuItem storeManu(String menuName){
        return new MainMenuItem(By.xpath(String.format("//a[@class='pulldown_desktop' and text()='%s']", menuName)), String.format("Menu %s", menuName));
    }
    private MainMenuItem storeSubManu(String subMenuName){
        return new MainMenuItem(By.xpath(String.format("//a[@class='popup_menu_item' and contains(text(),'%s')]", subMenuName)), String.format("Submenu %s", subMenuName));
    }

    public void navigateMenu(String menu, String subMenu)
    {
        storeManu(menu).hoveOverMenu();
        logger.info("steam.pageComponents.MainMenuPageComponent.navigateMenu.hoveOverMenu.perform");
        storeSubManu(subMenu).click();
        logger.info("steam.pageComponents.MainMenuPageComponent.navigateMenu.click.perform");
    }
}
