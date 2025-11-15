package utils.factories;

import com.fasterxml.jackson.databind.JsonNode;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import utils.JsonConfigReader;

public class IOSDriverFactory implements IDriverFactory {

    @Override
    public AppiumDriver createDriver(AppiumDriverLocalService service) {

        JsonNode config = JsonConfigReader.getPlatformConfig("ios");

        String appPath = System.getProperty("user.dir") + "/" + config.get("app").asText();

        XCUITestOptions options = new XCUITestOptions()
                .setPlatformName(config.get("platformName").asText())
                .setAutomationName(config.get("automationName").asText())
                .setDeviceName(config.get("deviceName").asText())
                .setApp(appPath)
                .setFullReset(config.get("fullReset").asBoolean())
                .setNoReset(config.get("noReset").asBoolean());

        if (config.has("autoAcceptAlerts")) {
            options.setAutoAcceptAlerts(config.get("autoAcceptAlerts").asBoolean());
        }

        System.out.println("Loaded IOS APP PATH: " + appPath);

        return new IOSDriver(service.getUrl(), options);
    }
}
