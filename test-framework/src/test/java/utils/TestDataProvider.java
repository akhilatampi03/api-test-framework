package utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "userData")
    public static Object[][] getUserData() {

        return new Object[][]{
                {"Alice"},
                {"Bob"},
                {"Charlie"},
                {"David"}
        };
    }

    @DataProvider(name = "invalidUserData")
    public static Object[][] getInvalidUserData() {

        return new Object[][]{
                {""},
                {null}
        };
    }
}