package url;

import io.restassured.response.ValidatableResponse;
import model.Courier;


public class ApiCourier extends BaseHttp {

    private final String baseUrl = "https://qa-scooter.praktikum-services.ru/";

    public ValidatableResponse createCourier(String login, String password, String firstName){
        Courier courier = new Courier(login,password,firstName);
        ValidatableResponse response = doPostRequest(baseUrl + "api/v1/courier", courier);
        return response;
    }

    public ValidatableResponse loginCourier(String login, String password){
        Courier courier = new Courier(login,password);
        ValidatableResponse response = doPostRequest(baseUrl + "api/v1/courier/login", courier);
        return response;
    }

    public ValidatableResponse deleteCourier(int id){
        ValidatableResponse response = doDeleteRequest(baseUrl + "api/v1/courier/" +  id);
        return response;
    }

}
