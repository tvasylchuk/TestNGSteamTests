package framework.utils;

import steam.model.SpecialOfferGamePane;

import java.util.Comparator;

public class GamePaneComparator implements Comparator<SpecialOfferGamePane> {
    @Override
    public int compare(SpecialOfferGamePane o1, SpecialOfferGamePane o2) {
        return ((Integer)o1.getLbDiscount()).compareTo(o2.getLbDiscount());
    }
}
