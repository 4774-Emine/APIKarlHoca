package Day04;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isA;

public class C01_RequestJSONObject {

    // Request lerimizi ve response larimizi sadece string olarak gondermiyoruz.

    // REQUEST

    // 0 - String concatenation (Not Recommended) !!!

    // 1 - JSONObject (Not recommended but okay) !

    // 2 - Map (Not recommended but okay) !

    // 3- POJO (Plain Old Java Object) (Recommended)

    @Test
    public void jsonObject(){

        // POST https://reqres.in/api/users

        JSONObject jsonBody = new JSONObject();

        jsonBody.put("name", "Karl Ortis");
        jsonBody.put("job", "Instructor");

        // Json objesini urettikten sonra body olarak gonderirken toString() methodunu kullaniyoruz

        Response response = given().
                contentType("application/json").
                baseUri("https://reqres.in").
                body(jsonBody.toString()).
                log().all().
            when().
                post("/api/users");

        // 1. Yol response.then().body() assertion

        response.
                then().
                log().all().
                statusCode(201).
                contentType("application/json; charset=utf-8").
                body("name", equalTo("Karl Ortis")).
                body("job", equalTo("Instructor")).
                body("id", isA(String.class));

        // 2. Yol Json Object assertion
        //asString methodu responsu Stringe convert etti
        JSONObject responseBody = new JSONObject(response.asString());//{"createdAt":"2023-07-16T19:52:51.802Z","name":"Karl Ortis","id":"266","job":"Instructor"}
        System.out.println(responseBody);

        String actualName = responseBody.getString("name");
        String actualJob = responseBody.getString("job");
        String actualId = responseBody.getString("id");

        String expectedName = "Karl Ortis";
        String expectedJob = "Instructor";

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8");

        Assert.assertEquals(actualName, expectedName);
        Assert.assertEquals(actualJob, expectedJob);
        Assert.assertNotNull(actualId);

        // 3. Yol jsonPath assertion

        JsonPath jsonPath = response.jsonPath();//JsonPath ile responsu navigate ediyoruz.

        Assert.assertEquals(jsonPath.getString("name"), "Karl Ortis");
        Assert.assertEquals(jsonPath.getString("job"), "Instructor");
        Assert.assertNotNull(jsonPath.get("id"));

    }
}