package day02;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.Authentication;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetRequest06 extends Authentication {

    @Test
    public void test06() {
        String url = "https://www.gmibank.com/api/tp-customers/114351";

        Response response = given().headers("Authorization","Bearer "+generateToken()).when().get(url);
        response.prettyPrint();

        //Matcher class ile müşteri bilgilerini dogrulayin
        response.then().assertThat().body("firstName",equalTo("Della")
                , "lastName",equalTo("Heaney")
                , "email",equalTo("ricardo.larkin@yahoo.com")
                , "mobilePhoneNumber",equalTo("123-456-7893")
                , "accounts[0].balance",equalTo(69700)
                , "accounts[0].accountType",equalTo("CREDIT_CARD")
                , "accounts[1].balance",equalTo(11190)
                , "accounts[1].accountType",equalTo("CHECKING"));

        //JsonPath class ile müşteri bilgilerini dogrulayin
        JsonPath json1 = response.jsonPath();
        Assert.assertEquals("Della",json1.getString("firstName"));
        Assert.assertEquals("Heaney",json1.getString("lastName"));
        Assert.assertEquals("ricardo.larkin@yahoo.com",json1.getString("email"));
        Assert.assertEquals("123-456-7893",json1.getString("mobilePhoneNumber"));
        Assert.assertEquals(69700,json1.getInt("accounts[0].balance"));
        Assert.assertEquals("CREDIT_CARD",json1.getString("accounts[0].accountType"));
        Assert.assertEquals(11190,json1.getInt("accounts[1].balance"));
        Assert.assertEquals("CHECKING",json1.getString("accounts[1].accountType"));

    }
}
