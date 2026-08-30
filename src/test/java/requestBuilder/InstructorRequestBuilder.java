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
    public static String studentID;
    public static String updatedTaskId;

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
        System.out.println("RETRIEVED TOKEN: " + userToken);

        studentID = response.jsonPath().getString("data.user.id");
        System.out.print("RETRIEVED STUDENT ID: " + studentID);

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

        activeGroupId = response.jsonPath().getString("data[0].Id");
        System.out.println("LOOK AT GOD! HAHA: " + activeGroupId);
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
        System.out.println("CREATED TASK ID: " + createdTaskId);

        return response;
    }

    public static Response updateTask
            (String tittle, String description, String groupId, String priority, String dueDate, String studentId){

        String Endpoint = "/APIDEV/instructor/tasks/"+createdTaskId;

        Response response = given()
                    .baseUri(BASE_URL)
                    .basePath(Endpoint)
                    .contentType(ContentType.JSON)
                    .header("Authorization","Bearer " + userToken)
                    .body(InstructorPayload.createTaskPayload(tittle, description, groupId, priority, dueDate, studentId))
                .when()
                    .put()
                .then()
                    .extract().response();

        updatedTaskId = response.jsonPath().getString("data.id");
        System.out.println("UPDATED TASK ID: " + updatedTaskId );

        return response;
    }

    public static Response getUpdatedTask(){

        String Endpoint = "/APIDEV/instructor/tasks/" + updatedTaskId +"/completions";

        Response response = given()
                    .baseUri(BASE_URL)
                    .basePath(Endpoint)
                    .contentType(ContentType.JSON)
                    .header("Authorization","Bearer " + userToken)
                .when()
                    .get()
                .then()
                    .extract().response();

        return response;
    }
}