package testData;


import org.testng.annotations.DataProvider;

public class LoginDataProvider {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][]{
                {"U7542d0ff280c455b860682fd8307eaad"}
        };
    }
}
