package Day02;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C04_Auth {


    // GET https://postman-echo.com/basic-auth
    // Basic Auth - postman:password
    @Test
    public void basicAuth() {

        given().
                auth().basic("postman", "password").
                baseUri("https://postman-echo.com").
                accept(ContentType.JSON).
                when().
                get("/basic-auth").
                then().
                log().
                all();

    }

    // GET https://api.github.com/repos/{owner}/{repo}


    @Test
    public void oauth2() {

        given().
                baseUri("https://api.github.com").
                pathParam("owner", "karlOrtis").
                pathParam("repo", "API-Instruction").
                auth().oauth2("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").
                /*
                oauth2() içerisine  bearer token giriyoruz.Eğer bu şekilde hata alırsak:
                Bearer token ==> header - Authorization = Bearer {token}(Bu 1. yol fakat oauth2() methodu daha yaygın)
                 */
                        log().all(). // requesti loglamak amaciyla kullanilan log methodu
                when().
                get("/repos/{owner}/{repo}").
                then().
                log().all(); // response u loglamak icin kullanilan log methodu
    }
}