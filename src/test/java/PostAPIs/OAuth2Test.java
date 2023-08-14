package PostAPIs;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

import java.util.List;



public class OAuth2Test {

	static String accessToken;
	
	@BeforeMethod
	public void accessToken()
	{
	RestAssured.baseURI="https://test.api.amadeus.com";
		
		//Get the access token
		
		 accessToken=given().log().all()
		.header("Content-Type","application/x-www-form-urlencoded")
		.formParam("grant_type", "client_credentials")
		.formParam("client_id", "kFVTxAqJqqiJnAecCY92V0vxNo8Qt1tG")
		.formParam("client_secret", "XcfnIAEkbJsNAGQY")
		.when().log().all()
			.post("/v1/security/oauth2/token")
		.then().log().all()
			.assertThat()
			.statusCode(200)
		.extract().path("access_token");
		
		System.out.println(accessToken);
	
	}
	
	
	
	@Test
	public void getFlightInfo()
	{
		
		//Get the flight infor
		
		Response flightDataResponse = given().log().all()
		.header("Authorization","Bearer "+accessToken)
		.when().log().all()
		.get("/v1/shopping/flight-destinations?origin=PAR&maxPrice=200")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.extract().response();

		JsonPath js =flightDataResponse.jsonPath();
		
		String type =js.get("data[0].type");
		System.out.println(type);
		
		String total =js.get("data[0].price.total");
		System.out.println(total);
		
		List<String> totalList =js.get("data.price.total");
		System.out.println(totalList);

	}
}
