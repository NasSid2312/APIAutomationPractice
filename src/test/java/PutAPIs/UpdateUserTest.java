package PutAPIs;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUserTest {
	
	public static String getRamdomEmail()
	{
		return "apiTesting"+System.currentTimeMillis()+"@mail.com";
	}
	
	@Test
	public void updateUserTest()
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
		
		user.setName("UpdatedSidd");  //update the user fields
		user.setStatus("inactive");
		
		//2.Put call-Update user
		RestAssured.given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization","bearer e7ceb9cf8d544054f17778eb7631280ae715574858642094a7a671c3dc4ca3a8")
				.body(user)  // pass the updated object in put call
				.when()
					.put("/public/v2/users")  // patch also works
						.then().log().all()
						.assertThat()
						.statusCode(200)
						.and()
						.assertThat()
						.body("id", equalTo(userId))
						.and()
						.body("name", equalTo(user.getName()))
						.and()
						.body("status",equalTo(user.getStatus()));
					
		
		
	}
	

}
