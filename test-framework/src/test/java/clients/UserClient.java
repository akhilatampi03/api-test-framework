package clients;

import io.restassured.response.Response;
import utils.RequestBuilder;

public class UserClient {

    public Response getUsers() {

        return RequestBuilder
                .getRequest()
                .when()
                .get("/users");
    }
    public Response createUser(Object body) {

    return RequestBuilder
            .getRequest()
            .body(body)
            .when()
            .post("/users");
}
}