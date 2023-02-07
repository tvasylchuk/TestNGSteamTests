package framework.elements;

import org.openqa.selenium.By;

public class FilterCheckBox extends BaseElement{

    public FilterCheckBox(By locator, String elementName)
    {
        super(locator, elementName);
    }

    public FilterCheckBox(By locator)
    {
        super(locator);
    }

    public void setProducerFilter()
    {
        String lct = this.getLocator().toString().replace("By.xpath: ", "")+"/../span[@class='i-checkbox__faux']";
        FilterCheckBox clickReceiver = new FilterCheckBox(By.xpath(lct));
        var element = this.getElement();
            if (!element.isSelected())
            {
                scrollPageTillElementVisible();
                clickReceiver.getElement().click();
            }
    }

    public void unSetProducerFilter()
    {
        FilterCheckBox clickReceiver = new FilterCheckBox(By.xpath(this.getLocator()+"/../span[@class='i-checkbox__faux']"));
        var element = this.getElement();
        if (element.isSelected())
        {
            scrollPageTillElementVisible();
            clickReceiver.getElement().click();
        }
    }

    @Override
    public void sendKey(String key) throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
