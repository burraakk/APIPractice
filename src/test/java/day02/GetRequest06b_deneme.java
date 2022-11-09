package day02;

import io.restassured.path.json.*;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static utilities.Authentication.generateToken;

public class GetRequest06b_deneme {
    @Test
    public void test06b() {
        String url = "https://www.gmibank.com/api/tp-customers/";
        String url2 = "https://www.gmibank.com/api/tp-customers/114351";

        Response tumData = given().headers("Authorization","Bearer "+generateToken()).when().get(url);
        //tumData.prettyPrint();
        Response singleData = given().headers("Authorization","Bearer "+generateToken()).when().get(url2);
        singleData.prettyPrint();

        //Matcher ile
        singleData.then().assertThat().body("ssn",equalTo("823-25-7239")
        ,"zipCode",equalTo("53081")
        ,"country.id",equalTo(3)
        ,"country.name",equalTo("USA")
        ,"country.states",equalTo(null)
        ,"state",equalTo("New York43")
        ,"user",equalTo(null)
        ,"accounts[0].id",equalTo(2333)
        ,"accounts[0].createDate",equalTo("2020-11-06T23:00:00Z")
        ,"accounts[1].employee",equalTo(null)
        ,"accounts[1].closedDate",equalTo("2022-11-24T23:00:00Z"));

        //Json Path ile
        JsonPath json1 = singleData.jsonPath();
        Assert.assertEquals("823-25-7239",json1.getString("ssn"));
        Assert.assertEquals("53081",json1.getString("zipCode"));
        Assert.assertEquals(3,json1.getInt("country.id"));
        Assert.assertEquals("USA",json1.getString("country.name"));
        Assert.assertNull(json1.getString("country.states"));
        Assert.assertEquals("New York43",json1.getString("state"));
        Assert.assertNull(json1.getString("user"));
        Assert.assertEquals(2333,json1.getInt("accounts[0].id"));
        Assert.assertEquals("2020-11-06T23:00:00Z",json1.getString("accounts[0].createDate"));
        Assert.assertNull(json1.getString("accounts[1].employee"));
        Assert.assertEquals("2022-11-24T23:00:00Z",json1.getString("accounts[1].closedDate"));

        //Liste atip yazdirma
        List<Integer> allBalances = json1.getList("accounts.balance");
        System.out.println("allBalances = " + allBalances);

        List<String> allAccountTypes = json1.getList("accounts.accountType");
        System.out.println("allAccountTypes = " + allAccountTypes);

        Map<String,Object> dataMap = singleData.as(HashMap.class);
        System.out.println(dataMap);
        System.out.println(dataMap.get("accounts"));

    }
}
