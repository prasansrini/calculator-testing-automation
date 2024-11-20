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
}
