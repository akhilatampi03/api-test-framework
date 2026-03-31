package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestBuilder {

    public static RequestSpecification getRequest() {

        RequestSpecBuilder builder = new RequestSpecBuilder();

        builder.addHeader("Content-Type", "application/json");

        return given()
                .spec(builder.build())
                .log().all();
    }
}