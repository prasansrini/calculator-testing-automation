import config.DriverConfig;
import io.appium.java_client.AppiumDriver;
import org.junit.*;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class WindowsCalculatorTest {
    private static final URL APPIUM_URL;
    private static AppiumDriver<WebElement> mCalculatorDriver = null;
    private static WebElement mCalculatorResult = null;

    static {
        try {
            APPIUM_URL = new URL(Constants.APPIUM_LOCAL_URL.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeClass
    public static void setup() {
        DriverConfig driverConfig = DriverConfig.getInstance(APPIUM_URL, DriverConfig.loadWindowsCapabilities(), Constants.WINDOWS_KEY.toString());
        mCalculatorDriver = driverConfig.getDriver(Constants.WINDOWS_KEY.toString());

        mCalculatorDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        mCalculatorResult = mCalculatorDriver.findElementByAccessibilityId("CalculatorResults");
        Assert.assertNotNull(mCalculatorResult);
    }

    @AfterClass
    public static void tearDown() {
        mCalculatorResult = null;
        if (mCalculatorDriver != null) {
            mCalculatorDriver.quit();
        }
        mCalculatorDriver = null;
    }

    @Before
    public void clear() {
        if (mCalculatorDriver != null) {
            clickThrough("Clear");
            Assert.assertEquals("0", formatCalculatorResultsText());
        }
    }

    @Test
    public void addition() {
        clickThrough("One", "Plus", "Seven", "Equals");
        mCalculatorResult = mCalculatorDriver.findElementByAccessibilityId("CalculatorResults");
        Assert.assertNotNull(mCalculatorResult);
        Assert.assertEquals("8", formatCalculatorResultsText());
    }

    @Test
    public void combination() {
        clickThrough("Seven", "Multiply by", "Nine", "Plus", "One", "Equals", "Divide by", "Eight", "Equals");
        Assert.assertEquals("8", formatCalculatorResultsText());
    }

    @Test
    public void division() {
        clickThrough("Eight", "Eight", "Divide by", "One", "One", "Equals");
        Assert.assertEquals("8", formatCalculatorResultsText());
    }

    @Test
    public void multiplication() {
        clickThrough("Nine", "Multiply by", "Nine", "Equals");
        Assert.assertEquals("81", formatCalculatorResultsText());
    }

    @Test
    public void subtraction() {
        clickThrough("Nine", "Minus", "One", "Equals");
        Assert.assertEquals("8", formatCalculatorResultsText());
    }

    private String formatCalculatorResultsText() {
        return mCalculatorResult.getText().replace("Display is", "").trim();
    }

    private void clickThrough(String... elements) {
        Arrays.stream(elements).forEach(element -> mCalculatorDriver.findElementByName(element).click());
    }
}
