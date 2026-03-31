package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import utils.Config;

public class BaseTest {

    @BeforeClass
    public void setup() {

        RestAssured.baseURI = Config.BASE_URL;

        System.out.println("Base URI set to: " + Config.BASE_URL);
    }
}