package onliner.pageObjects;

import framework.elements.Label;
import framework.pageObjects.BasePage;
import onliner.pageComponents.ProductsPageComponent;
import org.openqa.selenium.By;

public class CatalogPage extends BasePage {
    private final Label title = new Label(By.xpath("//div[@class='catalog-navigation__title']"), "Title");
    private final ProductsPageComponent productsPageComponent;
    public CatalogPage()
    {
        super();
        productsPageComponent = new ProductsPageComponent();
    }

    @Override
    public String GetTitle() {
        return title.getTextFromElement();
    }

    public void selectProductFromCatalog(String department, String category, String product) {
        productsPageComponent.selectDepartment(department);
        productsPageComponent.selectProductCategory(category);
        productsPageComponent.selectProduct(product);
    }
}
