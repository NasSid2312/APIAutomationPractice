package PostAPIs;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Credentials;

public class BookingAuthTest {

	
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
	
	@Test
	public void getBookingAuthTokenTest_With_Json_File()
	{
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		String tokenID = given()
							.contentType(ContentType.JSON)
								.body(new File("./src/test/resource/data/basicAuth.json"))
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
	
	@Test
	public void addUserTest()
	{
		RestAssured.baseURI="https://gorest.co.in";
		
		//create a user
		
		int userID =given().log().all()
		.contentType(ContentType.JSON)
		.body(new File("./src/test/resource/data/adduser.json"))
		.header("Authorization","Bearer e7ceb9cf8d544054f17778eb7631280ae715574858642094a7a671c3dc4ca3a8")
			.when().log().all()
				.post("/public/v2/users/")
			.then().log().all()
				.assertThat()
				.statusCode(201)
				.and()
				.body("name", equalTo("Siddiqui"))
				.extract()
				.path(("id"));
			
		System.out.println(userID);
		
		//Get the user information
		
		given().log().all()
		.header("Authorization","Bearer e7ceb9cf8d544054f17778eb7631280ae715574858642094a7a671c3dc4ca3a8")
			.when().log().all()
				.get("/public/v2/users/"+userID)
					.then().log().all()
						.assertThat()
						.statusCode(200)
						.and()
							.body("id", equalTo(userID));
	}
	
	
}
