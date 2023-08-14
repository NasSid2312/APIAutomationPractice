package AuthAPIs;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class AuthTest {
	
	@BeforeTest
	public void allureSetup()
	{
		RestAssured.filters(new AllureRestAssured());
	}
	@Test
	public void jwtAuth_withJsonBody()
	{
		
		RestAssured.baseURI="https://fakestoreapi.com";
	
		String jwtTokenID= RestAssured.given()
		.contentType(ContentType.JSON)
		.body("{\n"
				+ "    \"username\": \"mor_2314\",\n"
				+ "    \"password\": \"83r5^_\"\n"
				+ "}")
			.when()
			.post("/auth/login")
			.then()
			.assertThat().statusCode(200)
			.extract().path("token");
		
		System.out.println(jwtTokenID);
		
		String tokenArray[]=jwtTokenID.split("\\.");
		System.out.println(tokenArray[0]);
		System.out.println(tokenArray[1]);
		System.out.println(tokenArray[2]);
			
	}

	@Test
	public void BasicAuth_Test()  // 1.Basic Auth
	{
		
		RestAssured.baseURI="https://the-internet.herokuapp.com";
	
		String responseBody= RestAssured.given()
			.auth().basic("admin", "admin")
			.when()
			.get("/basic_auth")
			.then()
			.assertThat().statusCode(200)
			.and()
			.extract().body().asString();
			
		// responseBody.contains(String)--check the string whether it is present in the body or not?
		
		System.out.println(responseBody);
		
		
	}
	@Test
	public void preemptiveAuth_Test()  // 2.Basic Auth-Faster than Basic- use more efficient algo in client-Server Arch to perform authetication
	{
		
		RestAssured.baseURI="https://the-internet.herokuapp.com";
	
		String responseBody= RestAssured.given()
			.auth().preemptive().basic("admin", "admin")
			.when()
			.get("/basic_auth")
			.then()
			.assertThat().statusCode(200)
			.and()
			.extract().body().asString();
					
		System.out.println(responseBody);
		
		
	}
	@Test
	public void digestAuth_Test()  //3.More secure,much better performance than preemptive,basic
	{
		
		RestAssured.baseURI="https://the-internet.herokuapp.com";
	
		String responseBody= RestAssured.given()
			.auth().digest("admin", "admin")
			.when()
			.get("/basic_auth")
			.then()
			.assertThat().statusCode(200)
			.and()
			.extract().body().asString();
					
		System.out.println(responseBody);
				
	}
	//@Test
	public void ApiKeyAuth_Test()  //3.More secure,much better performance than preemptive,basic
	{
		
		RestAssured.baseURI="http://api.whetherapi.com/";
	
		String responseBody= RestAssured.given()
				.queryParam("q", "London")
				.queryParam("aqi", "no")
				.queryParam("key", "generate key from UI")
				.when()
			.get("/v1/currentjson")
			.then()
			.assertThat().statusCode(200)
			.and()
			.extract().body().asString();
					
		System.out.println(responseBody);
				
	}
}
