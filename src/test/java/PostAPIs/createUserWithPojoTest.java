	package PostAPIs;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import java.util.UUID;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.User;

//Create Post call using 3 ways :

	//1.Create Post call by passing Json String
	//2.Create Post call by passing Json File
	//3.Create Post call by passing POJO object with the help of jackson -rest assured pom dependency -Best way to implement

public class createUserWithPojoTest {
		
	public static String getRandomEmailId()
	{
		//return "apiTesting"+System.currentTimeMillis() + "@yahoo.com";
		return "apiTesting"+UUID.randomUUID()+"@yahoo.com";
	}
	@Test
	public void addUser()
	{
		RestAssured.baseURI="https://gorest.co.in";

		//Create the object of POJO class
		User user=new User("Nasee",getRandomEmailId(),"female","active");
		
		int userID =given().log().all()
		.contentType(ContentType.JSON)
		.body(user)  //	//pass the POJO object in the body
		.header("Authorization","Bearer e7ceb9cf8d544054f17778eb7631280ae715574858642094a7a671c3dc4ca3a8")
			.when().log().all()
				.post("/public/v2/users/")
					.then().log().all()	
					  .assertThat()
						.statusCode(201)
							.and()
								.body("name",equalTo(user.getName()))
									.extract().path("id");
			
		System.out.println(userID);
	
	//Get call -get the create user by supply the user id from above call 	
	given()
		.header("Authorization","Bearer e7ceb9cf8d544054f17778eb7631280ae715574858642094a7a671c3dc4ca3a8")
		.when().log().all()
		.get("/public/v2/users/"+userID)
			.then().log().all()
			  .assertThat()
				.statusCode(200)
					.and()
						.body("id", equalTo(userID))
							.and()
							   .body("name", equalTo(user.getName()))
							   .and()
							   .body("status",equalTo(user.getStatus()));
							   
				
			
	
	
	
	}
	
	

}
