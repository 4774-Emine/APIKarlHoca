package Day01;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class C05_BuildingRequest {

    // Bu classimizda requestlerde body nasil set edilir bunu gorecegiz

    @Test
    public void bodyUsage() {

        String payload = "{\"username\" : \"admin\", \"password\" : \"password123\"}";

        RestAssured.given().
                contentType(ContentType.JSON).
                body(payload).
                baseUri("https://restful-booker.herokuapp.com").
                when().
                post("/auth").
                then().statusCode(200);

//Body gönderirken ContentType belirtmeyi unutmuyoruz.
    }
}