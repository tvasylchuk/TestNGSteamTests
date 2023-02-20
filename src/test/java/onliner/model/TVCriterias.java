package onliner.model;

public class TVCriterias {

    private String maker = null;

    private String resolution = null;

    private Integer diagonal = null;

    private Double price = null;

    public TVCriterias(String maker, String resolution, Integer diagonal, Double price)
    {
        this.maker = maker;
        this.resolution = resolution;
        this.diagonal = diagonal;
        this.price = price;
    }

    public String getMaker()
    {
        return maker;
    }

    public String getResolution()
    {
        return resolution;
    }

    public Integer getDiagonal()
    {
        return diagonal;
    }

    public Double getPrice()
    {
        return price;
    }
}
