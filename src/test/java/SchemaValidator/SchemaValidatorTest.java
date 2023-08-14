package SchemaValidator;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import java.io.File;

public class SchemaValidatorTest {
	
	@Test
	public void addUserSchemaTest() {
		
		RestAssured.baseURI="https://gorest.co.in";

		//Add user-Post
		
		given().log().all()
		.contentType(ContentType.JSON)
		.body(new File(".src/test/resource/data/adduser.json"))
		.header("Authorization","bearer e7ceb9cf8d544054f17778eb7631280ae715574858642094a7a671c3dc4ca3a8")
		.when()
		.post("/public/v2/users")
		.then()
		.assertThat()
		.statusCode(201)
		.body(matchesJsonSchemaInClasspath("createuserschema.json"));		
		
	}
	//Get all users
	@Test
	public void getallUserSchemaTest() {
		
		RestAssured.baseURI="https://gorest.co.in";

		//Add user-Post
		
		given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization","bearer e7ceb9cf8d544054f17778eb7631280ae715574858642094a7a671c3dc4ca3a8")
		.when()
		.get("/public/v2/users")
		.then()
		.assertThat()
		.statusCode(200)
		.body(matchesJsonSchemaInClasspath("getalluserschema.json"));
		
		
	}
}
