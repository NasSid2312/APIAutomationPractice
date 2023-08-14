package multibody;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BodyAPITest {
	
	@Test
	public void bodyWithTestText()
	{
		RestAssured.baseURI="http://httpbin.org";
		
		String payload= "Hello world";
		
		Response response  =  RestAssured.given()
		.contentType(ContentType.TEXT)
		.body(payload)
		.when()
		.post("/post");
//		.then()
//		.assertThat()
//		.statusCode(200)
//		.and()
//		.assertThat()
//		.body("data",equalTo(payload));
		
		response.prettyPrint();
		System.out.println(response.statusCode());		
		
	}
	@Test
	public void bodyWithTestJavaScript()
	{
		RestAssured.baseURI="http://httpbin.org";
		
		String payload= "function add(){\n"
				+ "    let x=10;\n"
				+ "    let y=20;\n"
				+ "    console.log(x+y);\n"
				+ "}";
		
		Response response  =  RestAssured.given()
								.header("Content-Type","application/javascript")
									.body(payload)
									.when()
									.post("/post");
		
		
		response.prettyPrint();
		System.out.println(response.statusCode());		
		
	}

	@Test
	public void bodyWithHTMLTest()
	{
		RestAssured.baseURI="http://httpbin.org";
		
		String payload= "<!DOCTYPE html>\n"
				+ "<html dir=ltr lang=\"en\">\n"
				+ "    <head>\n"
				+ "        <meta charset=\"UTF-8\"/>\n"
				+ "    </head>\n"
				+ "</html>";
		
		Response response  =  RestAssured.given()
								.contentType(ContentType.HTML)				
								.body(payload)
								.when()
								.post("/post");
		
		
		response.prettyPrint();
		System.out.println(response.statusCode());		
		
	}
	@Test
	public void bodyWithXMLTest()
	{
		RestAssured.baseURI="http://httpbin.org";
		
		String payload= "";
		Response response  =  RestAssured.given()
								.contentType(ContentType.XML)				
								.body(payload)
								.when()
								.post("/post");
		
		
		response.prettyPrint();
		System.out.println(response.statusCode());		
		
	}
	@Test
	public void bodyWith_MultiPartTest()
	{
		RestAssured.baseURI="http://httpbin.org";
		
		Response response  =  RestAssured.given()
								.contentType(ContentType.MULTIPART)	
								.multiPart("name","testing")
								.multiPart("filename",new File("/Users/rizzy/Downloads/NaseemaAadhaar.pdf"))
								.when()
								.post("/post");
		
		
		response.prettyPrint();
		System.out.println(response.statusCode());		
		
	}
	@Test
	public void bodyWith_BinaryTest()
	{
		RestAssured.baseURI="http://httpbin.org";
		
		Response response  =  RestAssured.given()
								.header("Content-Type","application/pdf")
								.body(new File("/Users/rizzy/Downloads/NaseemaAadhaar.pdf"))
								.when()
								.post("/post");
		
		
		response.prettyPrint();
		System.out.println(response.statusCode());		
		
	}
}
