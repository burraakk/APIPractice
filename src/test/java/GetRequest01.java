import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 {

    @Test
    public void test01(){
        String url = "https://restful-booker.herokuapp.com/booking";

        Response response = given().when().get(url);
        //"given().when().get(url)" ile end point'e request gönderdik
        //Daha sonra gelen respond'u response değişkenine atadık

        //Response response = given().auth().basic("user","password").when().get(url);
        //basic authentication ile request bu şekilde yollanır

        //response.prettyPrint(); // response'taki body'yi yazdırır
        //response.prettyPeek(); // response'taki her şeyi yazdırır
        //response.peek();  // her şeyi yazdırır, bodyi tek satır olacak şekilde yazdırır --> [{"bookingid":1215},{"bookingid":844},{"bookingid":87},...]
        //response.print(); // sadece bodyi, tek satırda olacak şekilde yazdırır --> [{"bookingid":1215},{"bookingid":844},{"bookingid":87},...]

        System.out.println("statusCode : " + response.statusCode());
        System.out.println("statusLine : " + response.statusLine());
        System.out.println("contentType : " + response.contentType());

        //Junit Assert'leri ile API testi yapabiliriz
        Assert.assertEquals("Status Kod hatali",200,response.statusCode());
        Assert.assertEquals("HTTP/1.1 200 OK",response.statusLine());
        Assert.assertEquals("application/json; charset=utf-8",response.contentType());

        //assertthat() ile
        response.then().assertThat().
                statusCode(200).
                contentType("application/json; charset=utf-8").
                statusLine("HTTP/1.1 200 OK");

    }
}
