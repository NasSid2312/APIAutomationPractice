package PostAPIs;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Credentials;

public class BookingAuthUsingPojo {

	
	@Test
	public void getBookingAuthTokenTest()
	{
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		Credentials cred =new Credentials("admin","password123");
		
		String tokenID = given()
							.contentType(ContentType.JSON)
								.body(cred)
								.when()
									.post("/auth")
									   .then()
									     .assertThat()
									       .statusCode(200)
									       	 .extract()
									       	 	.path("token");
		
		System.out.println(tokenID);
		Assert.assertNotNull(tokenID);
	}
	
}
