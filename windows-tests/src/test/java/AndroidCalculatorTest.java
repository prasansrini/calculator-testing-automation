import io.appium.java_client.AppiumDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class AndroidCalculatorTest {

    private static AppiumDriver<WebElement> driver;

    @BeforeClass
    public static void beforeMethod() throws MalformedURLException {
        URL appiumUrl = new URL(Constants.APPIUM_LOCAL_URL.toString());
        driver = new AppiumDriver<>(appiumUrl, loadCapabilities());
    }

    @AfterClass
    public static void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }

    private static Capabilities loadCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:version", "14");
        capabilities.setCapability("appium:automationName", "uiautomator2");
        capabilities.setCapability("deviceName", "Android");
        capabilities.setCapability("appPackage", "com.sec.android.app.popupcalculator");
        capabilities.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");

        return capabilities;
    }

    @Test
    public void additionTest() {
        clickThrough("7", "Plus", "6", "Calculation");
        String result = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_edt_formula")).getText();

        assertEquals(formattedResult(result), "13");
    }

    @Test
    public void subtractionTest() {
        clickThrough("7", "Minus", "6", "Calculation");
        String result = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_edt_formula")).getText();

        assertEquals(formattedResult(result), "1");
    }

    private String formattedResult(String result) {
        return result.replaceAll("Calculation result", "").trim();
    }

    private void clickThrough(String... elements) {
        Arrays.stream(elements).forEach(element -> driver.findElementsByAccessibilityId(element).getFirst().click());
    }
}
