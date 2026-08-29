package payloadBuilder;

import org.json.simple.JSONObject;

public class InstructorPayload {

    public static JSONObject instructorLoginPayload(String email, String password){

        JSONObject loginInstructor = new JSONObject();

        loginInstructor.put("email", email);
        loginInstructor.put("password", password);

        return loginInstructor;
    }

    public static JSONObject createTaskPayload
            (String tittle, String description, String groupId, String priority, String dueDate, String studentId, String url, String name,String documents){

        JSONObject createTask = new JSONObject();

        createTask.put("tittle",tittle);
        createTask.put("description", description);
        createTask.put("groupId", groupId);
        createTask.put("priority", priority);
        createTask.put("dueDate", dueDate);
        createTask.put("studentId", studentId);
        createTask.put("url", url);
        createTask.put("name", name);

       return  createTask;
    }
}
