package steam.testng.tests;

import framework.Logger;
import framework.PropertiesResourceManager;
import framework.driver.Browser;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.Arrays;

public abstract class BaseTest {
    protected static Browser browser;
    private static int stepNumber = 1;
    private final static Logger logger = Logger.getInstance();
    protected PropertiesResourceManager resourceManager;
    protected static String TESTDATA_FILE_NAME;
    protected ITestContext context;


    @BeforeClass
    public void setUp(ITestContext context) {
        browser = Browser.getInstance();
        stepNumber = 1;
        this.context = context;
        TESTDATA_FILE_NAME=String.format("Local/%s.testdata.properties", browser.getLoc().toString());
    }

    @AfterClass()
    public void tearDown()
    {
        if (browser.isBrowserAlive())
        {
            browser.exit();
        }
    }

    @BeforeMethod()
    protected abstract void initTestData();

    public void logStep()
    {
        logger.logStep(stepNumber);
        stepNumber++;
    }

    public void logTestName(String testName)
    {
        logger.logTestName(testName);
    }

    public void info(final String msg) {
        logger.info(msg);
    }

    public void warn(final String msg) {
        logger.warn(msg);
    }

    public void error(final String msg) {
        logger.error(msg);
    }

    public void fatal(final String msg) {
        logger.fatal(msg);
    }

    public void initResourceManager(String fileName) {
        resourceManager = new PropertiesResourceManager(fileName);
        try{
            resourceManager.getPropertiesFromFile();
        }
        catch(IOException e)
        {
            logger.error(String.format("The file %1s$ was not found.", fileName));
            logger.error(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException(e);
        }
    }

}
