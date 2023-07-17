package Day03;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class C02_ResponseBody {


    //Matcher classından methodlar kullanarak response body assert işlemlerini yapabiliyoruz.

    @Test
    public void responseBody(){
    //https://jsonplaceholder.typicode.com/posts/1
        Response response =
        given().
                baseUri("https://jsonplaceholder.typicode.com").
                pathParam("postId", 1).
        when().
                get("/posts/{postId}");

        response.then().statusCode(200); // status code un 200 oldugunu kontrol eder
        response.then().header("Transfer-Encoding", "chunked");

        response.then().body("id",equalTo(1)); // response umuzun id fieldinin degeri 1 e esit olmalidir
        response.then().body("title", containsString("facere")); // response umuzun title fieldinin facere stringini icermelidir 

        response.then().log().all(); // response un status code header ve body bilgilerini consola yazar

    }

}