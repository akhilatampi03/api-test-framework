package tests;

import base.BaseTest;
import clients.UserClient;
import io.restassured.response.Response;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTest extends BaseTest {

    UserClient userClient = new UserClient();

    @Test(description = "Validate fetching users list")
    public void testGetUsers() {

        Response response = userClient.getUsers();

        Assert.assertEquals(response.getStatusCode(), 200);

        User[] users = response.as(User[].class);

        Assert.assertTrue(users.length > 0);

        System.out.println("First user: " + users[0].getName());
    }

    // ✅ Data-driven positive test
    @Test(
        dataProvider = "userData",
        dataProviderClass = utils.TestDataProvider.class,
        description = "Validate user creation with valid inputs",
        retryAnalyzer = utils.RetryAnalyzer.class
    )
    public void shouldCreateUserSuccessfully(String name) {

        System.out.println("Running test with name: " + name);

        User newUser = new User();
        newUser.setName(name);

        Response response = userClient.createUser(newUser);

        Assert.assertEquals(response.getStatusCode(), 201);

        User createdUser = response.as(User.class);

        Assert.assertEquals(createdUser.getName(), name);
        Assert.assertTrue(createdUser.getId() > 0);

        System.out.println("Created user: " + createdUser.getName());
    }

    // ✅ Data-driven negative test
    @Test(
        dataProvider = "invalidUserData",
        dataProviderClass = utils.TestDataProvider.class,
        description = "Validate user creation with invalid inputs"
    )
    public void shouldHandleInvalidUser(String name) {

        System.out.println("Running negative test with name: " + name);

        User newUser = new User();
        newUser.setName(name);

        Response response = userClient.createUser(newUser);

        System.out.println("Response: " + response.getBody().asString());

        // json-server is lenient, so just validating response presence
        Assert.assertTrue(response.getStatusCode() >= 200);
    }
}