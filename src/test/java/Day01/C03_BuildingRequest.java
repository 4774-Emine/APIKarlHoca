package Day01;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class C03_BuildingRequest {

    // Request headerlarinda kullanilan ozel methodlari bu classta gorecegiz

    // accept(String value)
    // contentType() -> gonderilecek body nin veri tipini belirler

    @Test
    public void specificHeaders() {


        // accept(value) <=> header("Accept", value)

        RestAssured.given()
                .accept("application/json")
                .when()
                .get("url");

        // Content-type header i bir body oldugu takdirde requeste gonderilmelidir.
        // contentType(ContentType.JSON) <=> header("Content-type", "application/json")


        // contentType(String type)

        RestAssured.given()
                .contentType("application/json")
                .when()
                .post("url");


        // contentType(ContentType type)

        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .post("url");


    }
}