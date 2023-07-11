package Day01;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class C04_BaseURI {

    @Test
    public void baseURI(){

        // Biz tum API requestlerimiz icin baseURI tanimlayabiliriz

        // Request Spec olusturulur

        RequestSpecification spec = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com")
                .setAccept("application/json").build();

        //TEMPLATE REQUEST

        RestAssured.given()
                .header("x", "a")
                .header("y", "b")
                .header("z", "c")
                .spec(spec);

        //Example request

        RestAssured.given()
                .spec(spec)
                .when()
                .get("/booking")
                .then().log().body();

    }

}