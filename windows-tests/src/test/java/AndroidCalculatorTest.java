import config.ConfigConstants;
import config.DriverConfig;
import io.appium.java_client.AppiumDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import static config.DriverConfig.loadAndroidCapabilities;
import static org.junit.Assert.assertEquals;
import static util.Constants.AndroidConstants.*;

public class AndroidCalculatorTest {

    private static final URL appiumUrl;
    private static AppiumDriver<WebElement> driver;

    static {
        try {
            appiumUrl = new URL(ConfigConstants.APPIUM_LOCAL_URL.toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeClass
    public static void beforeMethod() throws MalformedURLException {
        DriverConfig driverConfig = DriverConfig.getInstance(appiumUrl, loadAndroidCapabilities(), ConfigConstants.ANDROID_KEY.toString());
        driver = driverConfig.getDriver(ConfigConstants.ANDROID_KEY.toString());
    }

    @AfterClass
    public static void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }

    @Test
    public void additionTest() {
        clickThrough(DIGIT_SEVEN, PLUS, DIGIT_SIX, EQUALS);
        String result = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_edt_formula")).getText();

        assertEquals(DIGIT_THIRTEEN, formattedResult(result));
    }

    @Test
    public void subtractionTest() {
        clickThrough(DIGIT_SEVEN, MINUS, DIGIT_SIX, EQUALS);
        String result = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_edt_formula")).getText();

        assertEquals(DIGIT_ONE, formattedResult(result));
    }

    private String formattedResult(String result) {
        return result.replaceAll("Calculation result", "").trim();
    }

    private void clickThrough(String... elements) {
        Arrays.stream(elements).forEach(element -> driver.findElementsByAccessibilityId(element).getFirst().click());
    }
}
