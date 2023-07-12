package Day02;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C03_QueryParams {

    // queryParam methodu URL in sonuna otomatik olarak belirtilen key ve value ciftlerini query olarak ekler.

    // URL -> baseURI + endpoint + query
    // query -> ?k=v&k=v

    // GET https://restful-booker.herokuapp.com/booking ?firstname=sally&lastname=brown

    @Test
    public void queryParam() {

        given().
                baseUri("https://restful-booker.herokuapp.com").
                queryParam("firstname", "sally").
                queryParam("lastname", "brown").
                when().
                get("/booking").
                then().
                log().
                everything();

        //    https://restful-booker.herokuapp.com/booking ? firstname = sally & lastname = brown
    }

    @Test
    public void queryParams() {

        given().
                baseUri("https://restful-booker.herokuapp.com").
                queryParams("firstname", "sally", "lastname", "brown").
                when().
                get("/booking").
                then().
                log().
                everything();

        //    https://restful-booker.herokuapp.com/booking ? firstname = sally & lastname = brown
    }


}