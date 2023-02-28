package onliner.pageComponents;

import framework.elements.Label;
import framework.elements.TextBoxGroup;
import onliner.model.TVCriterias;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPageComponent {

    private TextBoxGroup txtGrProducts = new TextBoxGroup(By.xpath("//div[@class='schema-product__group']"), "Product search result");

    private Label productTitle(Integer index) {
        return new Label(By.xpath("//div[@class='schema-product__group']["+index+"]//a[@class='js-product-title-link']/span"));
    }

    private Label productDescription(Integer index) {
      return new Label(By.xpath("//div[@class='schema-product__group']["+index+"]//div[@class='schema-product__description']/span"));
    }

    private Label productPrice(Integer index){
        return new Label(By.xpath("//div[@class='schema-product__group']["+index+"]//div[@class='schema-product__price']/a/span"));
    }

    public List<TVCriterias> GetProductSearchResult()
    {
        List<TVCriterias> result = new ArrayList<>();
        int parentElements = txtGrProducts.getElements().size();
        for(int i = 0; i < parentElements; i++)
        {
            productTitle(i+1).getElement();
            productTitle(i+1).scrollPageTillElementVisible();
            String maker = productTitle(i+1).getTextFromElement().split(" ")[1];
            String resolution = productDescription(i+1).getTextFromElement().split(" ")[1];
            String diagonal = productDescription(i+1).getTextFromElement().split(" ")[0].replace("\"", "");
            String price = productPrice(i+1).getTextFromElement().replace(" р.", "").replace(",00", "").replace(",", ".");
            result.add(new TVCriterias(maker, resolution, Integer.valueOf(diagonal), Double.parseDouble(price)));
        }

        return result;
    }
}
