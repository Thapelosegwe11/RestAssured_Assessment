package tests;

import org.testng.annotations.Test;
import requestBuilder.InstructorRequestBuilder;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static requestBuilder.InstructorRequestBuilder.activeGroupId;


public class InstructorTests {

    static String email = "segwe.bz@gmail.com";
    static String password = "rA!ny@$14";
    static String invalidEmail = "invalidEmail";
    static String invalidPassword = "invalidPassword";

    @Test
    public void testPositiveLogin(){

        InstructorRequestBuilder.instructorLogin(email,password)
                .then()
                    .log().all()
                .assertThat()
                    .statusCode(200)
                    .body("success",equalTo(true))
                    .body("message",equalTo("Login successful"))
                    .body("data.token",notNullValue());
    }

    @Test
    public void testNegativeLogin(){

        InstructorRequestBuilder.instructorLogin(invalidEmail,invalidPassword)
                .then()
                    .log().all()
                .assertThat()
                    .statusCode(401)
                    .body("success",equalTo(false))
                    .body("message",equalTo("Invalid email or password"))
                    .body("error_code",equalTo("INVALID_CREDENTIALS"));
    }

    @Test(dependsOnMethods = "testPositiveLogin")
    public void testGetGroupId(){

        InstructorRequestBuilder.getGroupId()
                .then()
                    .log().all()
                .assertThat()
                    .statusCode(200)
                    .body("success",equalTo(true))
                    .body("message",equalTo("Active groups retrieved successfully"))
                    .body("data",notNullValue());
    }

    @Test(dependsOnMethods = {"testPositiveLogin", "testGetGroupId"})
    public void testCreateTask(){

        String title = "firstTest";
        String description = "TestingCreateTask";
        String groupId = InstructorRequestBuilder.activeGroupId;
        String priority = "medium";
        String dueDate = "2026-08-29T14:25:37.392Z";
        String studentId = InstructorRequestBuilder.studentID;

        System.out.println("groupId being sent: " + groupId);
        System.out.println("studentId being sent: " + studentId);

        InstructorRequestBuilder.createTask(title,description,groupId,priority,dueDate,studentId)
                .then()
                    .log().all()
                .assertThat()
                    .statusCode(201)
                    .body("success",equalTo(true))
                    .body("message",equalTo("Task created successfully"))
                    .body("data.id",notNullValue());
    }

    @Test(dependsOnMethods = {"testPositiveLogin","testCreateTask"})
    public void testUpdateTask(){

        String title = "secondTest";
        String description = "UpdateTask";
        String groupId = InstructorRequestBuilder.activeGroupId;
        String priority = "low";
        String dueDate = "2026-08-29T14:25:37.392Z";
        String studentId = InstructorRequestBuilder.studentID;

        InstructorRequestBuilder.updateTask(title, description, groupId, priority, dueDate,studentId)
                .then()
                    .log().all()
                .assertThat()
                    .statusCode(200)
                    .body("data.id",notNullValue());

    }

    @Test(dependsOnMethods = {"testPositiveLogin","testCreateTask","testUpdateTask"})
    public void testGetUpdatedTask(){

        InstructorRequestBuilder.getUpdatedTask()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test(dependsOnMethods = {"testPositiveLogin","testCreateTask","testUpdateTask","testGetUpdatedTask"})
    public void testDeleteUpdatedTask(){

        InstructorRequestBuilder.deleteUpdatedTask()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success",equalTo(true));
    }
}
