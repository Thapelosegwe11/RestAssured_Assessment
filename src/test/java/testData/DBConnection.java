package testData;

import com.github.javafaker.Faker;
import commons.Routes;
import org.testng.annotations.BeforeClass;

import java.sql.*;

public class DBConnection {

    public static String emailFromDB;
    public static String passwordFromDB;


    public static void getConnection() throws SQLException {

        try (Connection connection = DriverManager.getConnection(Routes.DB_URL, Routes.DB_USERNAME, Routes.DB_PASSWORD)) {
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM thaps_users WHERE ID = 1 ")) {

                while (resultSet.next()) {
                    emailFromDB = resultSet.getString("email");
                    passwordFromDB = resultSet.getString("password");
                    System.out.println("Email:" + emailFromDB + "Password: " + passwordFromDB);
                }

                }catch (Exception error) {
                    error.printStackTrace();
            }
        }
    }
}
