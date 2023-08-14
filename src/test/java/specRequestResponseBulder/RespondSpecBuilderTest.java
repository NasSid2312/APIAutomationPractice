package specRequestResponseBulder;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;


public class RespondSpecBuilderTest {
	
	
	public static ResponseSpecification get_res_spec_200_OK() {
		  ResponseSpecification res_spec_200_ok = new ResponseSpecBuilder()
		   .expectContentType(ContentType.JSON)
		   .expectStatusCode(200)
		   .expectHeader("Server", "cloudflare")
		   .build();
		  
		  return res_spec_200_ok;
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
}
