package onlinerTests;

import framework.PropertiesResourceManager;
import framework.driver.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.internal.collections.Pair;

import java.io.FileNotFoundException;
import java.io.IOException;

@Test
public class BaseTest {
    protected static Browser browser;
    protected static String tvMaker;
    protected static String tvResolution;
    protected static String department;
    protected static String category;
    protected static String product;
    protected static Pair<String, String> diagonalRange;
    protected static Double price;
    protected static PropertiesResourceManager propertiesManager;

    @BeforeMethod()
    public void setUp() throws IOException {
        browser = Browser.getInstance();
        browser.navigate("https://www.onliner.by");
    }

    @AfterMethod()
    public void tearDown()
    {
        browser.exit();
    }

    protected static void initTestData(String fileName) throws FileNotFoundException {
        try
        {
            propertiesManager = new PropertiesResourceManager(fileName);
            propertiesManager.getPropertiesFromFile();
            department = propertiesManager.getPropertyValueByKey("Department").translateEscapes();
            category = propertiesManager.getPropertyValueByKey("ProductCategory").translateEscapes();
            product = propertiesManager.getPropertyValueByKey("Product").translateEscapes();
            tvMaker = propertiesManager.getPropertyValueByKey("Producer").translateEscapes();
            tvResolution = propertiesManager.getPropertyValueByKey("Resolution").translateEscapes();
            price = Double.parseDouble(propertiesManager.getPropertyValueByKey("Price"));
            diagonalRange = new Pair<>(propertiesManager.getPropertyValueByKey("MinDiagonalSize")+"\"", propertiesManager.getPropertyValueByKey("MaxDiagonalSize")+"\"");
        }
        catch(FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }
}
