import config.ConfigConstants;
import config.DriverConfig;
import io.appium.java_client.AppiumDriver;
import org.junit.*;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static config.DriverConfig.WindowsConstants.*;

public class WindowsCalculatorTest {
    private static final URL APPIUM_URL;
    private static AppiumDriver<WebElement> mCalculatorDriver = null;
    private static WebElement mCalculatorResult = null;

    static {
        try {
            APPIUM_URL = new URL(ConfigConstants.APPIUM_LOCAL_URL.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeClass
    public static void setup() {
        DriverConfig driverConfig = DriverConfig.getInstance(APPIUM_URL, DriverConfig.loadWindowsCapabilities(), ConfigConstants.WINDOWS_KEY.toString());
        mCalculatorDriver = driverConfig.getDriver(ConfigConstants.WINDOWS_KEY.toString());

        mCalculatorDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        mCalculatorResult = mCalculatorDriver.findElementByAccessibilityId(CALCULATOR_RESULTS);
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
            clickThrough(CLEAR);
            Assert.assertEquals(DIGIT_ZERO, formatCalculatorResultsText());
        }
    }

    @Test
    public void addition() {
        clickThrough(ONE, PLUS, SEVEN, EQUALS);
        mCalculatorResult = mCalculatorDriver.findElementByAccessibilityId(CALCULATOR_RESULTS);
        Assert.assertNotNull(mCalculatorResult);
        Assert.assertEquals(DIGIT_EIGHT, formatCalculatorResultsText());
    }

    @Test
    public void combination() {
        clickThrough(SEVEN, MULTIPLY, NINE, PLUS, ONE, EQUALS, DIVIDE, EIGHT, EQUALS);
        Assert.assertEquals(DIGIT_EIGHT, formatCalculatorResultsText());
    }

    @Test
    public void division() {
        clickThrough(EIGHT, EIGHT, DIVIDE, ONE, ONE, EQUALS);
        Assert.assertEquals(DIGIT_EIGHT, formatCalculatorResultsText());
    }

    @Test
    public void multiplication() {
        clickThrough(NINE, MULTIPLY, NINE, EQUALS);
        Assert.assertEquals(DIGIT_EIGHTY_ONE, formatCalculatorResultsText());
    }

    @Test
    public void subtraction() {
        clickThrough(NINE, MINUS, ONE, EQUALS);
        Assert.assertEquals(DIGIT_EIGHT, formatCalculatorResultsText());
    }

    private String formatCalculatorResultsText() {
        return mCalculatorResult.getText().replace("Display is", "").trim();
    }

    private void clickThrough(String... elements) {
        Arrays.stream(elements).forEach(element -> mCalculatorDriver.findElementByName(element).click());
    }
}
