package base;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.*;
import utils.DriverManager;

import java.util.Objects;

public class BaseTest {

    protected AppiumDriver driver;

    // Start Appium server before the entire test suite
    @BeforeSuite(alwaysRun = true)
    public void startAppiumServer() {
        DriverManager.startServer();
    }

    // Stop Appium server after the entire test suite
    @AfterSuite(alwaysRun = true)
    public void stopAppiumServer() {
        DriverManager.stopServer();
    }

    // Create driver before each test method
    @Parameters({"platform", "appVersion"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(String platform, @Optional("176.1") String appVersion) throws Exception {
        String updatedPlatform;
        if (Objects.isNull(platform)){
            updatedPlatform = System.getProperty("platform", "android"); // default to Android
        }
        else {
            updatedPlatform = platform;
        }
        DriverManager.createDriver(updatedPlatform);
        driver = DriverManager.getDriver();
    }

    // Quit driver after each test method
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
