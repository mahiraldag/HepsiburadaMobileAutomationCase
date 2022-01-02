import org.openqa.selenium.remote.DesiredCapabilities;

public class TestUtil {
    public static DesiredCapabilities getDefaultDesiredCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Pixel 2 API 29");
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "10.0");
        caps.setCapability("skipUnlock", "true");
        caps.setCapability("appPackage", "com.pozitron.hepsiburada");
        caps.setCapability("appActivity", "com.hepsiburada.ui.startup.SplashActivity");
        caps.setCapability("noReset", "false");

        return caps;
    }
}
