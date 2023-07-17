package Day03;

import io.restassured.response.Response;
    import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class C04_RequestBodyTypes {

    // Su ana kadar request bodylerimizi string olarak gonderdik
    // String olarak gondermek cok etkili bir yontem olmayabilir.
    // Bu yuzden body baska hangi sekillerde gonderilir bunu gorecegiz

    // 1. JSON Object -> org.json-->body() kısmında toString() çağırıyoruz.JSONObject claasından obje üretiyoruz.

    // 2. Map

    // 3. POJO (Plain Old Java Object)

    @Test
    public void jsonObject(){

//        {
//            "firstname" : "Jim",
//            "lastname" : "Brown"
//        }

       // PATCH https://restful-booker.herokuapp.com/booking/:id

        JSONObject body = new JSONObject();
        body.put("firstname", "Karl");
        body.put("lastname", "Ortis");

        System.out.println("body: "+ body);//body: {"firstname":"Karl","lastname":"Ortis"}

        String token = "6dfdd873df42536";

        Response response = given().
                contentType("application/json").
                accept("application/json").
                header("Cookie", "token=" + token).
                pathParam("bookingId", 1).
                baseUri("https://restful-booker.herokuapp.com").
                body(body.toString()).log().body().
                when().patch("/booking/{bookingId}");

        response.prettyPrint();

    }

}