package specRequestResponseBulder;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderTest {
	
	public static RequestSpecification user_Request_Spec()
	{
		RequestSpecification requestSpec = new RequestSpecBuilder()
				.setBaseUri("https://gorest.co.in")
				.setContentType(ContentType.JSON)
				.addHeader("Authorization","Bearer e7ceb9cf8d544054f17778eb7631280ae715574858642094a7a671c3dc4ca3a8")
				.build();
		return requestSpec;
		
	}

	@Test
	
	public void getUser_With_Request_Spec()
	{
		
		RestAssured.given().log().all()
			.spec(user_Request_Spec())
			.get("/public/v2/users")
			.then()
			.statusCode(200);	
		
	}

	
	
	@Test
	
	public void getUser_With_QueryParam_Request_Spec()
	{
		
		RestAssured.given().log().all()
		.queryParam("name", "Ashok")
			.spec(user_Request_Spec())
			.get("/public/v2/users")
			.then()
			.statusCode(200);	
		
	}

}
