package Day04;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class C03_RequestMap {

    @Test
    public void map(){

        // Map objeleri JSON objelerini temsil eder
        // Json arraylerini ise ArrayList veya Array ler temsil eder

        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("firstname", "Karl");
        requestBody.put("lastname", "Ortis");
        requestBody.put("totalprice", 311);
        requestBody.put("depositpaid", true);

        Map<String, Object> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2023-01-01");
        bookingdates.put("checkout", "2024-01-01");
        requestBody.put("bookingdates", bookingdates);

        requestBody.put("additionalneeds", "Breakfast");

        Response response = given().
                baseUri("https://restful-booker.herokuapp.com").
                contentType("application/json").
                body(requestBody).
                log().body().
            when().
                post("/booking");

        response.then().log().all();

        JsonPath jsonPath = response.jsonPath();
        
        String checkinDate = jsonPath.getString("booking.bookingdates.checkin");
        System.out.println("checkinDate = " + checkinDate);

        String checkoutDate = jsonPath.getString("booking.bookingdates.checkout");
        System.out.println("checkoutDate = " + checkoutDate);

        String additionalNeeds = jsonPath.getString("booking.additionalneeds");
        System.out.println("additionalNeeds = " + additionalNeeds);

        boolean depositPaid = jsonPath.getBoolean("booking.depositpaid");
        System.out.println("depositPaid = " + depositPaid);

        int totalPrice = jsonPath.getInt("booking.totalprice");
        System.out.println("totalPrice = " + totalPrice);

    }

}