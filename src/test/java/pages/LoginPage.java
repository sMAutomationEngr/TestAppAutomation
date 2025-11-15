package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LoginPage {

    private AppiumDriver driver;

    private By username = AppiumBy.xpath("//android.widget.EditText[1]");
    private By continueBtn = AppiumBy.accessibilityId("Continue");

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public void clickOnUserName(){
        driver.findElement(username).click();
    }

    public void enterUsername(String text) {
        driver.findElement(username).sendKeys(text);
    }

    public void tapContinue(){
        driver.findElement(continueBtn).click();
    }

    public void hideKeyboard(){
//        AndroidDriver androidDriver = (AndroidDriver) driver;
//        androidDriver.hideKeyboard();
        driver.navigate().back();
    }
}
