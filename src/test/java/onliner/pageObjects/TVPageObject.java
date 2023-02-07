package framework.pageObjects;

import framework.elements.*;
import framework.pageComponents.ProductsPageComponent;
import framework.pageComponents.SearchResultPageComponent;
import model.TVCriterias;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.internal.collections.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

public class TVPageObject extends BasePage{
    private ProductsPageComponent productsPageComponent = null;
    private SearchResultPageComponent searchResult = null;
    private Label Title = new Label (By.tagName("h1"));
    private Button btnLocation = new Button(By.cssSelector("span.button-style.button-style_another.button-style_base.schema-filter__button"), "Confirm location button");
    private Label lbFilterTag = new Label(By.className("schema-tags__text"));
    private Button btnSearchProducts = new Button(By.xpath("//div[@class='schema-filter-button__state schema-filter-button__state_initial schema-filter-button__state_disabled schema-filter-button__state_control schema-filter-button__state_animated']"), "Search product");
    private TextBox UpperPrice = new TextBox(By.xpath("//input[@class='schema-filter-control__item schema-filter__number-input schema-filter__number-input_price' and @placeholder='до']"));
    private ComboBox diagonalSizeFrom = new ComboBox(By.xpath("//select[@class='schema-filter-control__item' and contains(@data-bind, 'facet.value.from')]"));
    private ComboBox diagonalSizeTo = new ComboBox(By.xpath("//select[@class='schema-filter-control__item' and contains(@data-bind, 'facet.value.to')]"));
    private TextBoxGroup txtGroupProducts = new TextBoxGroup(By.className("schema-product"));

    private FilterCheckBox chFilterByMaker(String filterCategory, String filterName)
    {
        String lct = "//div[@class='schema-filter__label']/span[text()='"+filterCategory+"']/../..//ul[@class='schema-filter__list']//input[@type='checkbox' and @value='"+filterName+"']";
        return new FilterCheckBox(By.xpath(lct));
    }

    public TVPageObject()
    {
        super();
    }

    @Override
    public String GetTitle()
    {
        return Title.getElement().getText();
    }

    public void SetProduceFilter(List<String> tvMakers)
    {
        for (String maker: tvMakers)
        {
            var producerFilter = chFilterByMaker("Производитель", maker.toLowerCase());
            producerFilter.getElement();
            producerFilter.setProducerFilter();
        }
    }

    public void SetResolutionFilter(List<String> resolutions)
    {
        for(String resolution:resolutions)
        {
            var resolutionFilter = chFilterByMaker("Разрешение", resolution);
            resolutionFilter.getElement();
            resolutionFilter.setProducerFilter();
        }
    }

    public void SetTopPrice(String price)
    {
        UpperPrice.sendKey(price.toString());
    }

    public void SetDiagonal(Pair<String, String> diagonals)
    {
        diagonalSizeFrom.getElement();
        diagonalSizeFrom.selectItemByText(diagonals.first());

        diagonalSizeTo.getElement();
        diagonalSizeTo.selectItemByText(diagonals.second());
    }

    public void ConfirmLocation()
    {
        try
        {
            browser.getWait().until(ExpectedConditions.visibilityOf(btnLocation.getElement()));
            btnLocation.scrollPageTillElementVisible();
            btnLocation.click();
        }
        catch (NoSuchElementException exception)
        {
        }
    }

    public String GetFilterTagText()
    {
        return lbFilterTag.getElement().getText();
    }


    public void WaitForSearch()
    {
        btnSearchProducts.waitTillButtonAnimated();
    }

    public List<TVCriterias> getSearchResult()
    {
        searchResult = new SearchResultPageComponent();
        return searchResult.GetProductSearchResult();
    }
}
