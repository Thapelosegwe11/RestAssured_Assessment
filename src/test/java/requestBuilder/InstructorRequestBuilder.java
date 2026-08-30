package requestBuilder;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloadBuilder.InstructorPayload;

import static commons.Routes.BASE_URL;
import static io.restassured.RestAssured.given;

public class InstructorRequestBuilder {

    public static String userToken;
    public static String activeGroupId;
    public static String createdTaskId;

    public static Response instructorLogin(String email, String password) {

        String Endpoint = "/APIDEV/login";

        Response response = given()
                    .baseUri(BASE_URL)
                    .basePath(Endpoint)
                    .contentType(ContentType.JSON)
                    .body(InstructorPayload.instructorLoginPayload(email, password))
                .when()
                    .post()
                .then()
                    .extract().response();

        userToken = response.jsonPath().getString("data.token");
        System.out.println("Retrieved token: " + userToken);

        return response;
    }

    public static Response getGroupId(){

        String Endpoint = "/APIDEV/groups";

        Response response = given()
                    .baseUri(BASE_URL)
                    .basePath(Endpoint)
                    .contentType(ContentType.JSON)
                    .header("Authorization","Bearer "+ userToken)
                .when()
                    .get()
                .then()
                    .extract().response();

        activeGroupId = response.jsonPath().getString("data.Id");
        System.out.println("Look at God: " + activeGroupId);
        //KEEP LEARNING KEEP GROWING.

        return response;
    }

    public static Response createTask
            (String tittle, String description, String groupId, String priority, String dueDate, String studentId){

        String Endpoint = "/APIDEV/instructor/tasks";

        Response response = given()
                    .baseUri(BASE_URL)
                    .basePath(Endpoint)
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + userToken)
                    .body(InstructorPayload.createTaskPayload(tittle, description, groupId, priority, dueDate, studentId))
                .when()
                    .post()
                .then()
                    .extract().response();

        createdTaskId = response.jsonPath().getString("data.id");
        System.out.println("Created TaskId: " + createdTaskId);

        return response;
    }
}