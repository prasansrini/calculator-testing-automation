import capabilities.WindowsCapabilities;
import io.appium.java_client.windows.WindowsDriver;
import org.junit.*;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class WindowsCalculatorTest {
    private static WindowsDriver<WebElement> mCalculatorSession = null;
    private static WebElement mCalculatorResult = null;

    @BeforeClass
    public static void setup() {
        try {
            mCalculatorSession = new WindowsDriver<>(new URL(Constants.APPIUM_LOCAL_URL.toString()), WindowsCapabilities.getInstance());
            mCalculatorSession.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

            mCalculatorResult = mCalculatorSession.findElementByAccessibilityId("CalculatorResults");
            Assert.assertNotNull(mCalculatorResult);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.exit(1);
        }
    }

    @AfterClass
    public static void tearDown() {
        mCalculatorResult = null;
        if (mCalculatorSession != null) {
            mCalculatorSession.quit();
        }
        mCalculatorSession = null;
    }

    @Before
    public void clear() {
        if (mCalculatorSession != null) {
            clickThrough("Clear");
            Assert.assertEquals("0", formatCalculatorResultsText());
        }
    }

    @Test
    public void addition() throws MalformedURLException {
        clickThrough("One", "Plus", "Seven", "Equals");
        mCalculatorResult = mCalculatorSession.findElementByAccessibilityId("CalculatorResults");
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
        Arrays.stream(elements).forEach(element -> mCalculatorSession.findElementByName(element).click());
    }
}
