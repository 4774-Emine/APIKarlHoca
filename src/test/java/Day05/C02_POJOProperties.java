package Day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.restfullbooker.CreateTokenRequest;
import pojo.restfullbooker.CreateTokenResponse;


import static io.restassured.RestAssured.given;

public class C02_POJOProperties {

    // POJO classlarini olustururken field
    // isimlerimiz json field isimleriyle birebir ayni olmali

    // Bu kosul her zaman icin gecerli degildir.
    //Eger Jackson un bize sagladigi anotasyonlardan faydalanirsak POJO class field isimlerimizi farkli kullanabiliriz

    // @JsonProperty anotasyonu bir fielda uygulandiginda o fieldin JSON da hangi field a hitap ettigini gosterir

        @Test
        public void test(){

            CreateTokenRequest request = new CreateTokenRequest();
            request.setKullaniciAdi("admin");
            request.setSifre("password123");

            Response response = given().
                    baseUri("https://restful-booker.herokuapp.com").
                    contentType(ContentType.JSON).
                    body(request).log().body().
                    when().
                    post("/auth");

            response.then().assertThat().statusCode(200);//assertThat() ile then() aynı işlevi görür.

            CreateTokenResponse pojoResponse = response.as(CreateTokenResponse.class);

            System.out.println(pojoResponse.getToken());

        }

}