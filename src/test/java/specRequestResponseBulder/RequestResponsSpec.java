package specRequestResponseBulder;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;


public class RequestResponsSpec {

	
	public static RequestSpecification user_Request_Spec()
	{
		RequestSpecification requestSpec = new RequestSpecBuilder()
				.setBaseUri("https://gorest.co.in")
				.setContentType(ContentType.JSON)
				.addHeader("Authorization","Bearer e7ceb9cf8d544054f17778eb7631280ae715574858642094a7a671c3dc4ca3a8")
				.build();
		return requestSpec;
		
	}

	public static ResponseSpecification get_res_spec_200_OK_With_Body() {
		  ResponseSpecification res_spec_200_ok = new ResponseSpecBuilder()
		   .expectContentType(ContentType.JSON)
		   .expectStatusCode(200)
		   .expectHeader("Server", "cloudflare")
		   .expectBody("$.size()", equalTo(10))
		   .expectBody("id", hasSize(10))
		   .expectBody("status", hasItem("active")) 
		   .build();
		  
		  return res_spec_200_ok;

	}
		  
	@Test
	public void getUser_with_Req_RespSpec()
	{
		given().log().all()
			.spec(user_Request_Spec())
				.get("/public/v2/users")
				.then().log().all()
				.assertThat()
				.spec(get_res_spec_200_OK_With_Body());
				
	}
}
