package Day04;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.restfullbooker.Booking;
import pojo.restfullbooker.BookingDates;

public class C04_RequestPOJO {


    // (subjektif yorum) POJO API automation da en onem arz eden konulardan biridir.
    // Request body sini POJO class imizi kullanarak olusturuyoruz
    //obje gördüğümüz her yerde bir pojo classı,array gördüğümüz her yerde de arrayList oluşturmamız gerekir.

    @Test
    public void pojo(){
        // POST https://restful-booker.herokuapp.com/booking
        /*Body:
        {
            "firstname": "Karl",
                "lastname": "Ortis",
                "totalprice": 311,
                "depositpaid": false,
                "bookingdates": {
                    "checkin": "2023-02-02",
                    "checkout": "2024-02-02"
        },
            "additionalneeds": "Kahvalti"
        }*/

        Booking booking = new Booking();

        booking.setFirstname("Karl");
        booking.setLastname("Ortis");
        booking.setTotalprice(311);
        booking.setDepositpaid(false);
        booking.setAdditionalneeds("Kahvalti");

        // Request body sinde ic ice olan her bir json objesi icin POJO objesi uretiyoruz
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2023-02-02");
        bookingDates.setCheckout("2024-02-02");

        booking.setBookingdates(bookingDates);

        Response response = RestAssured.given().
                baseUri("https://restful-booker.herokuapp.com").
                contentType("application/json").
                body(booking). // requestimizi POJO objemizi body methoduna koyarak yapiyoruz
                log().body().
            when().
                post("/booking");

        response.then().statusCode(200);

    }
}