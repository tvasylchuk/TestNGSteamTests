package framework.elements;

import org.openqa.selenium.By;

public class ScrollBar extends BaseElement{

    private Float scrollLeftStyle = null;
    private Float scrollRightStyle = null;

    public ScrollBar(By locator) {
        super(locator);
    }

    private void setScrollLeftStyle()
    {
        var element = getElement();
        String str = element.getAttribute("style");
        scrollLeftStyle = Float.valueOf(str.substring(str.indexOf("left: ")+6, str.indexOf("%;")));
    }

    public Float getScrollLeftStyle()
    {
        this.setScrollLeftStyle();
        return scrollLeftStyle;
    }

    private void setScrollRightStyle()
    {
        var element = getElement();
        String str = element.getAttribute("style");
        scrollRightStyle = Float.valueOf(str.substring(str.indexOf("right: ")+7).replace("%;", ""));
    }

    public Float getScrollRightStyle()
    {
        this.setScrollRightStyle();
        return scrollRightStyle;
    }

}
