package Day04;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Filter;

import static io.restassured.RestAssured.given;

public class C02_JSONObjectExample {


    @Test
    public void example() {

        // request -> skip, take, eventsearchfilterlist, sortdescriptor

        // eventsearchfilterlist -> array[JSONObject]

        // sortdescriptor -> JSONObject

        JSONObject requestBody = new JSONObject().
                put("Skip", 0).
                put("Take", 10);

        JSONObject sortDescriptorValue = new JSONObject().
                put("dir", "desc").
                put("field", "eventDate");

        requestBody.put("SortDescriptor", sortDescriptorValue);

        JSONArray eventSearchFilterValue = new JSONArray();

        JSONObject filterType1 = new JSONObject();
        filterType1.put("FilterType", 9);
        filterType1.put("Value", "2023-07-13T18:18:14.178Z");

        JSONObject filterType2 = new JSONObject();
        filterType2.put("FilterType", 8);
        filterType2.put("Value", "2023-07-12T18:18:14.178Z");

        eventSearchFilterValue.put(filterType1);
        eventSearchFilterValue.put(filterType2);

        requestBody.put("EventSearchFilterList", eventSearchFilterValue);

        Response response = given().
                contentType("application/json").
                baseUri("https://deprem.afad.gov.tr").
                body(requestBody.toString()).
                when().
                post("/EventData/GetEventsByFilter");

        response.prettyPrint();

        JSONObject responseBody = new JSONObject(response.asString());

        // eventlist icindeki 2. objenin id fieldini cekelim
        int eventId = responseBody.
                getJSONArray("eventList").
                getJSONObject(1).
                getInt("id");

        System.out.println("eventId = " + eventId);

    }


    @Test
    public void afadMap() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("Skip", 0);
        requestBody.put("Take", 0);


        Map<String, Object> sortDescriptorValue = new HashMap<>();
        sortDescriptorValue.put("dir", "desc");
        sortDescriptorValue.put("field", "eventDate");

        requestBody.put("SortDescriptor",sortDescriptorValue);




        Map<String, Object> filterType1 = new HashMap<>();
        filterType1.put("FilterType",9);
        filterType1.put("Value","2023-07-13T18:18:14.178Z");

        Map<String, Object> filterType2 = new HashMap<>();
        filterType2.put("FilterType", 8);
        filterType2.put("Value", "2023-07-12T18:18:14.178Z");


        List<Map<String,Object>> eventSearchFilterValue = new ArrayList<>();

        eventSearchFilterValue.add(filterType1);
        eventSearchFilterValue.add(filterType2);

        requestBody.put("EventSearchFilterList", eventSearchFilterValue);


        Response response = given().
                contentType("application/json").
                baseUri("https://deprem.afad.gov.tr").
                body(requestBody).log().body().
                when().
                post("/EventData/GetEventsByFilter");
response.then().log().all();


    }

}