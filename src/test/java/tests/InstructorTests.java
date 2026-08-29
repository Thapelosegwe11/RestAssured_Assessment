package tests;

import org.testng.annotations.Test;
import requestBuilder.AuthRequestBuilder;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class InstructorTests {

    static String email = "segwe.bz@gmail.com";
    static String password = "rA!ny@$14";
    static String invalidEmail = "invalidEmail";
    static String invalidPassword = "invalidPassword";

    @Test
    public void testInstructorLogin(){
        //Accessing the request method with the response we need to test.

        AuthRequestBuilder.instructorLogin(email,password)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success",equalTo(true))
                .body("message",equalTo("Login successful"))
                .body("data.token",notNullValue());
    }
}
