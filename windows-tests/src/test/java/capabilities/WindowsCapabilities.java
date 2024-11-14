package capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

public class WindowsCapabilities extends DesiredCapabilities {
    private static WindowsCapabilities INSTANCE = null;

    private WindowsCapabilities() {
    }

    public static WindowsCapabilities getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new WindowsCapabilities();
            loadCapabilities(INSTANCE);
        }
        return INSTANCE;
    }

    private static void loadCapabilities(WindowsCapabilities instance) {
        instance.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
        instance.setCapability("automationName", "windows");
    }
}
