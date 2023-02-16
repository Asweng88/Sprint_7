package api;

import url.ApiCourier;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestPositiveCreateApiCourier {

    private final ApiCourier api = new ApiCourier();

    private String login = "testLoginJons";
    private String password = "testPassword";
    private String firstName = "testFirstNameJons";

    @Test
    public void validCreateCourier(){

        ValidatableResponse response = api.createCourier(login,password,firstName);
        int statusCode = response.extract().statusCode();
        Boolean body = response.extract().path("ok");
        assertEquals("Курьер не создан",statusCode, HttpStatus.SC_CREATED);
        assertTrue("Курьер не создан",body);

    }

    @After
    public void deleteCourier(){

        ValidatableResponse response = api.loginCourier(login,password);
        int statusCode = response.extract().statusCode();
        int id = response.extract().path("id");
        assertEquals("Ошибка авторизации курьера",statusCode,HttpStatus.SC_OK);

        response = api.deleteCourier(id);
        statusCode = response.extract().statusCode();
        assertEquals("Ошибка удаления курьера",statusCode,HttpStatus.SC_OK);

    }
}
