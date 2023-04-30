package get;

import io.cucumber.java.it.Ma;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import pojo.DataPojo;
import pojo.ListOfUsersPojo;

import java.util.*;

public class ListOfUsers {

    @Test
    public void validationTests() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("https://reqres.in/api/users?page=2")
                .then().statusCode(200)
                .body("page", Matchers.equalTo(2)) //validatingPageNumber
                .extract().response();


        //statusCodeValidation
        int actualStatusCode = response.getStatusCode();
        int expectedStatusCode = 200;
        Assert.assertEquals(expectedStatusCode, actualStatusCode);


        ListOfUsersPojo parsedResponse = response.as(ListOfUsersPojo.class);
        List<DataPojo> data = parsedResponse.getData();


        List<String> actualFirstNames = response.path("data.collect{it.first_name}");
        List<String> actualLastNames = response.path("data.collect{it.last_name}");
        List<Integer> actualIDs = response.path("data.collect{it.id}");
        List<String> actualAvatars = response.path("data.collect{it.avatar}");

        int actualUserCount = actualIDs.size();
        int expectedUserCount = 6;
        Assert.assertEquals(6, actualUserCount); //validating UserCountMatchesOrNOt




        for (DataPojo singleData : data) {
            Map<String, String> storeNames = new HashMap<>();
            storeNames.put(singleData.getFirst_name() + "." + singleData.getLast_name(), singleData.getEmail());
            System.out.println(storeNames); //storingUserInfoAsInDocumentation
        }
    }
}
