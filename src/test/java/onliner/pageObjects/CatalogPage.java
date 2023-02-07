package framework.pageObjects;

import framework.elements.Label;
import framework.pageComponents.ProductsPageComponent;
import org.openqa.selenium.By;

public class CatalogPage extends BasePage{
    private final Label title = new Label(By.xpath("//div[@class='catalog-navigation__title']"), "Title");
    private final ProductsPageComponent productsPageComponent;
    public CatalogPage()
    {
        super();
        productsPageComponent = new ProductsPageComponent();
    }

    @Override
    public String GetTitle() {
        return title.getElement().getText();
    }

    public void selectProductFromCatalog(String department, String category, String product) {
        productsPageComponent.selectDepartment(department);
        productsPageComponent.selectProductCategory(category);
        productsPageComponent.selectProduct(product);
    }
}
