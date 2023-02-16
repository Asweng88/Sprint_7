package api;

import url.ApiCourier;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestNegativeRequiredFieldsCreateApiCourier {

    //Добавь необходимые поля
    private final String login;
    private final String password;
    private final String firstName;
    private final int сode;
    private final String messeges;

    public TestNegativeRequiredFieldsCreateApiCourier(String login, String password, String firstName, int сode, String messeges) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.сode = сode;
        this.messeges = messeges;
    }

        @Parameterized.Parameters
        public static Object[][] getNegativeCreateApiCourier() {
            //Сгенерируй тестовые данные
            return new Object[][] {
                    {null, "testPassword", "testFirstNameJons", 400, "Недостаточно данных для создания учетной записи"},
                    {"testLoginJons", null, "testFirstNameJons", 400, "Недостаточно данных для создания учетной записи"},
            };
        }


    final ApiCourier api = new ApiCourier();

    @Test
    public void requiredFieldsCreateCourier(){

        ValidatableResponse response = api.createCourier(login,password,firstName);
        int statusCode = response.extract().statusCode();
        String body = response.extract().path("message");
        assertEquals("Ошибка валидации", statusCode, сode);
        assertEquals("Ошибка валидации", body, messeges);
        System.out.println("Курьер не прошел валидацию. Статусы " + statusCode + " " + body);


    }

}
