package capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

public class WindowsCapabilities extends DesiredCapabilities {
    private static WindowsCapabilities INSTANCE = null;

    private WindowsCapabilities() {
    }

    public static WindowsCapabilities getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new WindowsCapabilities();
            INSTANCE.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
            INSTANCE.setCapability("automationName", "windows");
        }
        return INSTANCE;
    }
}
