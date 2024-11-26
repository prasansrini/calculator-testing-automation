package config;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DriverConfig extends AppiumDriver<WebElement> {
    private static final Map<String, AppiumDriver<WebElement>> DRIVER_CONFIG = new HashMap<>();
    private static DesiredCapabilities mDesiredCapabilities = null;

    private DriverConfig(URL remoteAddress, DesiredCapabilities desiredCapabilities) {
        super(remoteAddress, desiredCapabilities);
    }

    public static DesiredCapabilities loadAndroidCapabilities() {
        if (null != mDesiredCapabilities && mDesiredCapabilities.getCapability("platformName") == "Android") {
            return mDesiredCapabilities;
        }

        mDesiredCapabilities = new DesiredCapabilities();
        mDesiredCapabilities.setCapability("platformName", "Android");
        mDesiredCapabilities.setCapability("appium:version", "14");
        mDesiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        mDesiredCapabilities.setCapability("deviceName", "Android");
        mDesiredCapabilities.setCapability("appPackage", "com.sec.android.app.popupcalculator");
        mDesiredCapabilities.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");

        return mDesiredCapabilities;
    }

    public static DesiredCapabilities loadWindowsCapabilities() {
        if (null != mDesiredCapabilities && mDesiredCapabilities.getCapability("platformName") == "Windows") {
            return mDesiredCapabilities;
        }

        mDesiredCapabilities = new DesiredCapabilities();
        mDesiredCapabilities.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
        mDesiredCapabilities.setCapability("automationName", "windows");
        mDesiredCapabilities.setCapability("platformName", "Windows");

        return mDesiredCapabilities;
    }

    public static DriverConfig getInstance(URL remoteAddress, DesiredCapabilities desiredCapabilities, String platform) {
        DriverConfig driverConfig = new DriverConfig(remoteAddress, desiredCapabilities);

        DRIVER_CONFIG.putIfAbsent(platform, driverConfig);

        return driverConfig;
    }

    public AppiumDriver<WebElement> getDriver(String platform) {
        return DRIVER_CONFIG.get(platform);
    }

    public static class WindowsConstants {
        public static final String CALCULATOR_RESULTS = "CalculatorResults";
        public static final String CLEAR = "Clear";

        public static final String DIGIT_ZERO = "0";
        public static final String DIGIT_EIGHT = "8";
        public static final String DIGIT_EIGHTY_ONE = "81";

        public static final String PLUS = "Plus";
        public static final String MINUS = "Minus";
        public static final String MULTIPLY = "Multiply by";
        public static final String DIVIDE = "Divide by";
        public static final String EQUALS = "Equals";

        public static final String ONE = "One";
        public static final String TWO = "Two";
        public static final String SEVEN = "Seven";
        public static final String EIGHT = "Eight";
        public static final String NINE = "Nine";
    }

    public static class AndroidConstants {
        public static final String CALCULATOR_RESULTS_ID = "com.sec.android.app.popupcalculator:id/calc_edt_formula";
        public static final String CLEAR = "Clear";

        public static final String DIGIT_ZERO = "0";
        public static final String DIGIT_ONE = "1";
        public static final String DIGIT_SIX = "6";
        public static final String DIGIT_SEVEN = "7";
        public static final String DIGIT_EIGHT = "8";
        public static final String DIGIT_THIRTEEN = "13";
        public static final String DIGIT_EIGHTY_ONE = "81";

        public static final String PLUS = "Plus";
        public static final String MINUS = "Minus";
        public static final String MULTIPLY = "Multiply by";
        public static final String DIVIDE = "Divide by";
        public static final String EQUALS = "Calculation";

        public static final String ONE = "One";
        public static final String TWO = "Two";
        public static final String SEVEN = "Seven";
        public static final String EIGHT = "Eight";
        public static final String NINE = "Nine";
    }
}
