package utils.factories;

import com.fasterxml.jackson.databind.JsonNode;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import utils.JsonConfigReader;

public class AndroidDriverFactory implements IDriverFactory {

    @Override
    public AppiumDriver createDriver(AppiumDriverLocalService service) {

        JsonNode config = JsonConfigReader.getPlatformConfig("android");

        String appPath = System.getProperty("user.dir") + "/" + config.get("app").asText();

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName(config.get("platformName").asText())
                .setAutomationName(config.get("automationName").asText())
                .setDeviceName(config.get("deviceName").asText())
                .setApp(appPath)
                .setFullReset(config.get("fullReset").asBoolean())
                .setNoReset(config.get("noReset").asBoolean());

        if (config.has("autoGrantPermissions")) {
            options.setAutoGrantPermissions(config.get("autoGrantPermissions").asBoolean());
        }

        System.out.println("Loaded ANDROID APP PATH: " + appPath);

        return new AndroidDriver(service.getUrl(), options);
    }
}
