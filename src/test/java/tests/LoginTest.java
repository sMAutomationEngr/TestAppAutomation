package tests;

import base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import pages.LoginPage;
import testData.LoginDataProvider;


@Slf4j
public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class, priority = 1)
    public void testLogin(String userName) throws InterruptedException {
        // Initialize page object with updated driver
        LoginPage login = new LoginPage(driver);

        // Perform login steps
        login.clickOnUserName();
        login.enterUsername(userName);
        Thread.sleep(2000);
        login.hideKeyboard();
        login.tapContinue();
        log.info("Login test executed successfully!");
    }

    @Test(dataProvider = "loginData",dataProviderClass = LoginDataProvider.class, priority = 2)
    public void testLogin2(String userName) throws InterruptedException {
        // Initialize page object with updated driver
        LoginPage login = new LoginPage(driver);
        // Perform login steps
        login.clickOnUserName();
        login.enterUsername(userName);
        Thread.sleep(2000);
        login.hideKeyboard();
        login.tapContinue();
        log.info("Login test executed successfully!");
    }

    //ToDo: 1. validations, 2. Json config to Java (deserialization)
    //ToDo: 3. Swipe elements, 4. Push repo to github., 5. RetryTransformer Listener.
}
