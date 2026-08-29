package requestBuilder;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloadBuilder.AuthPayload;

import static commons.Routes.BASE_URL;
import static io.restassured.RestAssured.given;

public class AuthRequestBuilder {

    static String userToken;

    public static Response instructorLogin(String email, String password) {

        String Endpoint = "/APIDEV/login";

        Response response = given()
                .baseUri(BASE_URL)
                .basePath(Endpoint)
                .contentType(ContentType.JSON)
                .body(AuthPayload.instructorLoginPayload(email, password))
                .when()
                .post()
                .then()
                .extract().response();

        userToken = response.jsonPath().getString("data.token");

        return response;
    }
}