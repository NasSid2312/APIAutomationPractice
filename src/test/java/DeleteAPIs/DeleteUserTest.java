package DeleteAPIs;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import PutAPIs.User;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest {
	//1.Post call -User created 
	//2.Delete call -delete user
	//3.Get call with same user ID
	
	public static String getRamdomEmail()
	{
		return "apiTesting"+System.currentTimeMillis()+"@mail.com";
	}
	
	@Test
	public void deleteUserTest()
	{
		//1.Post call-Create user
		RestAssured.baseURI="https://gorest.co.in";
		
		User user = new User("siddi",getRamdomEmail(),"female","active");
		
		Response response = given().log().all()
								.contentType(ContentType.JSON)
									.header("Authorization","bearer e7ceb9cf8d544054f17778eb7631280ae715574858642094a7a671c3dc4ca3a8")
									.body(user)
									.when()
										.post("/public/v2/users");
		response.prettyPrint();
		Integer userId= response.jsonPath().get("id"); 
		System.out.println("User ID:"+userId);
		System.out.println("************************");
		
		//2.Delete User
		RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization","bearer e7ceb9cf8d544054f17778eb7631280ae715574858642094a7a671c3dc4ca3a8")
		.body(user)
		.when()
			.delete("/public/v2/users"+userId)
			.then().log().all()
			.assertThat()
			.statusCode(204);
		
		//3.Get the user after delete
		
		RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization","bearer e7ceb9cf8d544054f17778eb7631280ae715574858642094a7a671c3dc4ca3a8")
		.body(user)
		.when()
			.get("/public/v2/users"+userId)
			.then().log().all()
			.assertThat()
			.statusCode(404)
			.and()
			.body("message", equalTo("Resource not found"));
		
		
		
		
		
	}

}
