package Day02;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class C05_StatusCode {

    @Test
    public void statusCode(){

        // then() methodundan sonra cagirilan statusCode(int sc) methodu status code umuzu assert eder yani dogrular.

        // GET https://petstore.swagger.io/v2/pet/{petId}

        given().
                baseUri("https://petstore.swagger.io/v2").
                pathParam("petId", 10).
                accept(ContentType.JSON).
                log().all().
        when().
                get("/pet/{petId}").
        then().
                log().all().
                statusCode(200);

    }

    @Test
    public void statusCodev2(){

        Response response = given().
                baseUri("https://petstore.swagger.io/v2").
                pathParam("petId", 10).
                accept(ContentType.JSON).
                log().all().
        when().
                get("/pet/{petId}");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

}