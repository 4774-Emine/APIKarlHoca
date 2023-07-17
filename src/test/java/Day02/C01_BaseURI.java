package Day02;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class C01_BaseURI {


        // GET /store/inventory

        // URL -> base URI + endpoint + query

    @Test
    public void baseURIUsage(){

        // Base URI request gonderilirken url in onune eklenecek olan base URI bilgisini requeste ekler

        given().
                accept("accept: application/json").
                baseUri("https://petstore.swagger.io/v2").
                when().
                get("/store/inventory").
                then().
                log().
                all();

    }

}