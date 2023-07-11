package Day02;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C01_BaseURI {

    //GET https://petstore.swagger.io/v2/store/inventory

    @Test
    public void baseURIUsage() {
//Base URI reguest gönderilirken url in önüne eklenecek olan base URI bilgisini requeste ekler
        given().
                accept(ContentType.JSON).
                baseUri("https://petstore.swagger.io/v2").
                when().get("/store/inventory").then().log().all();

    }


}
