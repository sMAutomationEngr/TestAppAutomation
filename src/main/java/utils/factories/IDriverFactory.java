package utils.factories;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public interface IDriverFactory {

    AppiumDriver createDriver (AppiumDriverLocalService service);
}
