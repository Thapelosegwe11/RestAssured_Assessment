package payloadBuilder;

import org.json.simple.JSONObject;

public class AuthPayload {

    public static JSONObject instructorLoginPayload(String email, String password){
        JSONObject loginInstructor = new JSONObject();
        loginInstructor.put("email", email);
        loginInstructor.put("password", password);

        return loginInstructor;
    }
}
