import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetRequest03_Task {
    /*
    MATCHERS İLE DATAYI TEST EDİN

    endpoint = https://reqres.in/api/users

      "id": 5,
            "email": "charles.morris@reqres.in",
            "first_name": "Charles",
            "last_name": "Morris",
            "avatar": "https://reqres.in/img/faces/5-image.jpg"
     */

    @Test
    public void test01() {
        String url = "https://reqres.in/api/users";

        Response response = given().when().get(url);
        response.prettyPrint();

        response.then().body("data[4].id", equalTo(5));
        response.then().body("data[4].email",equalTo("charles.morris@reqres.in"));
        response.then().body("data[4].first_name",equalTo("Charles"));
        response.then().body("data[4].last_name",equalTo("Morris"));
        response.then().body("data[4].avatar",equalTo("https://reqres.in/img/faces/5-image.jpg"));
    }


}
