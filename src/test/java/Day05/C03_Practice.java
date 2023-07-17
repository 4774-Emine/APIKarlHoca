package Day05;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.petstore.Pet;

import java.util.ArrayList;
import java.util.Arrays;


import static io.restassured.RestAssured.given;

public class C03_Practice {

    @Test
    public void practice(){





        //burada json to pojo adresine giderek dönüşüm yapabiliriz veya nasıl pojo hazırlayacağımızı
        //response body nin yanında model e tıklayarak pojo yapısını görebiliriz.
        /*
         {
  "id": 10,
  "category": {
   "id": 10,
   "name": "sample string"
  },
  *
  "name": "doggie",
  "photoUrls": [
    "sample 1",
    "sample 2",
    "sample 3"
  ],
  "tags": [
    {
      "id": 10,
      "name": "sample string"
    },
    {
      "id": 10,
      "name": "sample string"
    }
  ],
  "status": "available"
}*/

        int petId = 12;

        Response getPetResponse = given().
                baseUri("https://petstore.swagger.io/v2").
                accept("accept: application/json").
                pathParam("petId", petId).
            when().
                get("/pet/{petId}");

        getPetResponse.then().statusCode(200);
        System.out.println("-------------------------------------------------------------------------");
        getPetResponse.then().log().body();

        // Ilk get requesti ile guncellenecek olan hayvan java objesi olarak alinir
        Pet putRequestBody = getPetResponse.as(Pet.class);

        // Guncellenecek fieldlar guncellenir
        ArrayList<String> updatedList = new ArrayList<>(Arrays.asList("Foto1", "Foto2", "Foto3"));

        putRequestBody.setName("Pamuk");
        putRequestBody.setPhotoUrls(updatedList);

       // Guncellenen Pet ile put requesti atilir ve pet guncellenir
        Response putPetResponse = given().
                baseUri("https://petstore.swagger.io/v2").
                accept("application/json").
                contentType("application/json").
                body(putRequestBody).
            when().
                put("/pet");

        putPetResponse.then().statusCode(200);
        System.out.println("-------------------------------------------------------------------------");
        putPetResponse.then().log().body();

        // Guncellenen pet in guncellenen alanlari kontrol edilir
        Response finalGetResponse = given().
                baseUri("https://petstore.swagger.io/v2").
                accept("application/json").
                pathParam("petId", petId).
                when().
                get("/pet/{petId}");

        finalGetResponse.then().statusCode(200);
        System.out.println("-------------------------------------------------------------------------");
        finalGetResponse.then().log().body();

        Pet updatedPet = finalGetResponse.as(Pet.class);

        // Pet in nae mini Pamuk olarak degistirmistik, kontrol ediyoruz
        Assert.assertEquals(updatedPet.getName(), "Pamuk");

        // Pet in photoUrl leri arasinda gezinerek bu Url lerin tek tek degisip degismedigine bakiyoruz
        for (int i = 0; i < updatedPet.getPhotoUrls().size(); i++) {
            String currentPhoto = updatedPet.getPhotoUrls().get(i);
            Assert.assertTrue(updatedList.indexOf(currentPhoto)>=0);
        }

    }

}