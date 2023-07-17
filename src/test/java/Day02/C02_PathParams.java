package Day02;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class C02_PathParams {

    // GET /pet/{petId}
    // base URI = https://petstore.swagger.io/v2
    @Test
    public void pathParam(){
//https://petstore.swagger.io/v2/pet/{id}
        // pathParam() methoduyla path de kullanilacak parametreler belirtilebilir

        given().
                accept("application/json").
                baseUri("https://petstore.swagger.io/v2").
                pathParam("id", 10).
                when().
                get("/pet/{id}").
                then().
                log().
                all();

    }

    @Test
    public void pathParams() {

        // pathParams() methodu birden fazla parametreyi ayni anda tanimlamayi saglar
        // pathParams(String key1, Object value1, String key2, Object value2 ...) seklinde kullanilabilir

        // pathParams(Map params) in bir diger kullanimi key value ciftlerinin icinde bulundugu Map objesini methoda saglamaktir

        // path parametrelerini key value ciftleri halinde barindiran map objesi
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("path", "pet"); // path variable cifti
        parameters.put("id", 10); // path variable cifti

        given().
                accept(ContentType.JSON).
                baseUri("https://petstore.swagger.io/v2").
//                pathParams("path", "pet", "id", 5).
        pathParams(parameters).
                when().
                get("/{path}/{id}").
                then().
                log().
                all();

    }


}