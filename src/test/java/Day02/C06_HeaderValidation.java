package Day02;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class C06_HeaderValidation {

    @Test
    public void headerValidation(){

        given().
                baseUri("https://petstore.swagger.io/v2").
                pathParam("petId", 10).
                accept(ContentType.JSON).
                log().all().
        when().
                get("/pet/{petId}").
        then().
                log().all().
                statusCode(200).
                contentType("application/json").
                header("Transfer-Encoding", "chunked"); // (validate edilecek header in ismi, header in beklenen degeri)
    }

}