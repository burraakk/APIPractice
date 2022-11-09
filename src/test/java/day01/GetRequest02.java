package day01;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetRequest02 {
    @Test
    public void test02(){

        String url = "https://reqres.in/api/users";

        Response response = given().when().get(url);

        //HEADER TEST
        response.then().assertThat().
                statusCode(200).
                statusLine("HTTP/1.1 200 OK").
                contentType(ContentType.JSON);
        response.then().assertThat().header("Server","cloudflare");
        response.then().assertThat().header("Via","1.1 vegur");

        //BODY TEST
        /*
          ID'si 1 olanın datalarının asagidaki gibi oldugunu test edin

         "email": "george.bluth@reqres.in"
            "first_name": "George"
            "last_name": "Bluth"
         */
        //Matcher Class ile test edeceğiz
        response.then().body("data[0].email", Matchers.equalTo("george.bluth@reqres.in"));
        response.then().body("data[0].first_name", equalTo("George")); //equalTo() import edip bu şekilde de kullanabiliriz
        response.then().body("data[0].last_name", Matchers.equalTo("Bluth"));

    }
}
