package Day01;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C02_BuildingRequest<Request> {

    // Headerlar ve spesifik headerlar nasil kullanilir
    // ve set edilir bunu gorecegiz.

    // GET https://restful-booker.herokuapp.com/booking/1
    // Header -> Accept = application/json

    @Test
    public void headerUsage() {

        // header() methodu given kisminda kullanilir.
        // Requestimize herhangi bir header i eklememizi saglar (Content-type, Accept, Authorization, Content-Length)
        // 2 parametre alir. -> header(String key, Object value)

        //Requesti hazirla

        RequestSpecification httpRequest = given().
                header("Accept", "application/json");//json yerine xml de yazarak formatı değiştirebiliriz

        //Hazirlanan requesti gonder

        Response response = httpRequest.when()
                .get("https://restful-booker.herokuapp.com/booking/1");


        // Respone u isle

        response.then().
                log().
                body();

    }


}
