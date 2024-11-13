package capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * TODO: WIP
 */
public class AndroidCapabilities extends DesiredCapabilities {
    private static AndroidCapabilities INSTANCE = null;

    private AndroidCapabilities() {
    }

    public static AndroidCapabilities getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new AndroidCapabilities();
            loadCapabilities(INSTANCE);
        }

        return INSTANCE;
    }

    private static void loadCapabilities(AndroidCapabilities capabilities) {
        capabilities.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
        capabilities.setCapability("automationName", "windows");
    }
}
