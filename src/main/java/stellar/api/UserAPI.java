package stellar.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.Map;

public class UserAPI {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    private static final String REGISTER_ENDPOINT = "/api/auth/register";
    private static final String LOGIN_ENDPOINT = "/api/auth/login";
    private static final String USER_ENDPOINT = "/api/auth/user";

    public static Map<String, String> generateRandomUserData() {
        String email = RandomStringUtils.randomAlphanumeric(10) + "@example.com";
        String password = RandomStringUtils.randomAlphanumeric(10);
        String name = RandomStringUtils.randomAlphanumeric(10);

        Map<String, String> userData = new HashMap<>();
        userData.put("email", email);
        userData.put("password", password);
        userData.put("name", name);

        return userData;
    }
        // Регистрируем пользователя через API
        public static String registerUser(Map<String, String> userData) {
            RestAssured.given()
                    .baseUri(BASE_URL)
                    .header("Content-type", "application/json")
                    .body(userData)
                    .post(REGISTER_ENDPOINT)
                    .then()
                    .statusCode(200);

            return loginUserAndGetToken(userData.get("email"), userData.get("password"));
        }

    public static String loginUserAndGetToken(String email, String password) {
        Map<String, String> loginData = new HashMap<>();
        loginData.put("email", email);
        loginData.put("password", password);

        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(loginData)
                .post(LOGIN_ENDPOINT);

        return response.jsonPath().getString("accessToken");
    }

    public static void deleteUser(String token) {
        RestAssured.given()
                .baseUri(BASE_URL)
                .header("Authorization", token)
                .delete(USER_ENDPOINT)
                .then()
                .statusCode(202);
    }
}