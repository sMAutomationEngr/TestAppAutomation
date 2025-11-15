package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import utils.factories.IDriverFactory;
import utils.factories.DriverFactoryRegistry;

import java.io.File;
import java.time.Duration;

public class DriverManager {

    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private static AppiumDriverLocalService service;

    // Start Appium Server
    public static void startServer() {

        if (service == null) {
            service = new AppiumServiceBuilder()
                    .usingDriverExecutable(new File("/usr/local/bin/node"))
                    .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                    .withIPAddress("127.0.0.1")
                    .usingPort(4723)
                    .withArgument(() -> "--session-override", "")
                    .withTimeout(Duration.ofSeconds(60))
                    .build();
            service.start();

            System.out.println("Appium server started at: " + service.getUrl());
        }
    }

    // Stop server
    public static void stopServer() {
        if (service != null && service.isRunning()) {
            service.stop();
            System.out.println("Appium server stopped.");
        }
    }

    // Get driver
    public static AppiumDriver getDriver() {
        return driver.get();
    }

    // Create driver using FACTORY
    public static void createDriver(String platform) throws Exception {

        // Start Appium automatically
//        startServer();

        IDriverFactory factory = DriverFactoryRegistry.getFactory(platform);
        AppiumDriver createdDriver = factory.createDriver(service);

        driver.set(createdDriver);
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
