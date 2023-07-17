package Day03;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C03_Exercise {

        // POST https://deprem.afad.gov.tr/EventData/GetEventsByFilter

    @Test
    public void afad(){

        // Request body de take degeri ne gonderilirse response da eventList size i o kadar olmalidir

        String body = "{\"EventSearchFilterList\":[{\"FilterType\":9," +
                "\"Value\":\"2023-07-12T19:38:39.894Z\"},{\"FilterType\":8," +
                "\"Value\":\"2023-07-11T19:38:39.894Z\"}]," +
                "\"Skip\":0," +
                "\"Take\":20," +
                "\"SortDescriptor\":{\"field\":\"eventDate\",\"dir\":\"desc\"}}";

        Response response = given().
                baseUri("https://deprem.afad.gov.tr").
                accept("application/json").
                contentType("application/json").
                body(body).
            when().
                post("/EventData/GetEventsByFilter");

        response.then().log().all();

        response.
                then().
                statusCode(200).
                header("Content-Type","application/json; charset=utf-8").
                body("eventList", hasSize(20)).//Matcher sayesinde evenListin 20 elemente sahip olup olmadığını test ettik
                body("eventList[0].magnitude", lessThan(5.0F)); // ilk deprem eventinin magnitude unun 5 ten kucuk oldugunu kontrol edelim
                //evenList bir array olduğu için ve magnitude ise 0. index elemanının altında olduğu için
                // -eventList[0].magnitude- şeklinde yazarak magnitude a ulaşırız.(kazma pratiği yaptık)
    }

}