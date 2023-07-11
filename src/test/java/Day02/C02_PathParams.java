package Day02;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C02_PathParams {
    @Test
    public void pathParam(){
        //path param methoduyla path de kullanılacak parametreler belirtilebişlir.

given().
        accept("application/json").
        baseUri("https://petstore.swagger.io/v2").
        pathParam("id",10).
        when().
        get("/pet/{id}").then().log().all();

        }

    @Test
    public void pathParams() {

        //path params methodu birden fazla parametreyi aynı anda tanımlamayı sağlar

given().
        accept(ContentType.JSON).
        baseUri("https://petstore.swagger.io/v2").
        pathParams("path","pet","id",5).
        //
        when().
        get("/{path}/{id}").
        then().
        log().all();

    }
}
