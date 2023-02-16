package api;

import url.ApiCourier;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestNegativeGetApiCourier {


    //Добавь необходимые поля
    private final String login;
    private final String password;
    private final String loginIn;
    private final String passwordIn;
    private final String firstName;
    private final int сode;
    private final String messeges;

    public TestNegativeGetApiCourier(String login, String loginIn, String passwordIn, String password, String firstName, int сode, String messeges) {
        this.login = login;
        this.password = password;
        this.loginIn = loginIn;
        this.passwordIn = passwordIn;
        this.firstName = firstName;
        this.сode = сode;
        this.messeges = messeges;
    }

    @Parameterized.Parameters
    public static Object[][] getNegativeGetApiCourier() {
        //Сгенерируй тестовые данные
        return new Object[][] {
                {"testLoginJons", "testPassword", "testLoginJons1", "testPassword", "testFirstNameJons", 404, "Учетная запись не найдена"},
                {"testLoginJons", "testPassword", "testLoginJons", "testPassword1", "testFirstNameJons", 404, "Учетная запись не найдена"},
                {"testLoginJons", "testPassword", "", "testPassword1", "testFirstNameJons", 400, "Недостаточно данных для входа"},
        };
    }


    private final ApiCourier api = new ApiCourier();
    private ValidatableResponse response;
    private int id;

    @Before
    public void createCourier(){

        response = api.createCourier(login,password,firstName);
        int statusCode = response.extract().statusCode();
        assertEquals("Курьер не создан",statusCode, HttpStatus.SC_CREATED);
    }

    @Test
    public void requiredFieldsCreateCourier(){

        response = api.loginCourier(loginIn,passwordIn);
        int statusCode = response.extract().statusCode();
        String message = response.extract().path("message");
        assertEquals("Ошибка валидации",statusCode,сode);
        assertEquals("Ошибка валидации",message, messeges);
        System.out.println("Проверка валидации " + statusCode + " messeges: " + messeges);

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
