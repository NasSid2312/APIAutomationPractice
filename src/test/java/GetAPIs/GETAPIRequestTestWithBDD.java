package GetAPIs;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.awt.geom.Arc2D.Float;
import java.util.List;
public class GETAPIRequestTestWithBDD {
	
	@Test
	public void getProductsTest() {
		
		given().log().all()
			.when().log().all()
				.get("https://fakestoreapi.com/products")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.and()
									.contentType(ContentType.JSON)
										.and()
											.header("Connection", "keep-alive")
												.and()
													.body("$.size()",equalTo(20))
														.and()
															.body("id",is(notNullValue()))
																.and()
																	.body("title",hasItem("Mens Casual Premium Slim Fit T-Shirts "));
	
	}	
								
	@Test
	public void getUserAPITest() {
		RestAssured.baseURI ="https://gorest.co.in";
		
			given().log().all()
				.header("Authorization","bearer e7ceb9cf8d544054f17778eb7631280ae715574858642094a7a671c3dc4ca3a8")
				.when().log().all()
					.get("/public/v2/users")
						.then().log().all()
							.assertThat()
								.statusCode(200)
								.and()
								.contentType(ContentType.JSON)
								.body("$.size()",equalTo(10));
			
				
	}
	
	@Test
	public void getProductDataAPIWithQueryParamTest()
	{
		RestAssured.baseURI="https://fakestoreapi.com";
		
		given().log().all()
		.queryParam("limit", 5)
				.when().log().all()
				.get("/products")
					.then().log().all()
						.assertThat()
							.statusCode(200)
							.and()
							.contentType(ContentType.JSON)
							.body("$.size", equalTo(5));
							
							
	}
	
	@Test
	public void getProductDataAPI_With_ExtractBody()
	{
		RestAssured.baseURI="https://fakestoreapi.com";
		
		Response response=given().log().all()
		.queryParam("limit", 5)
				.when().log().all()
				.get("/products");
		
		JsonPath js=response.jsonPath();
		
		int frstProductID=js.getInt("[0].id");
		System.out.println("1st product ID =>"+frstProductID);
		
		String frstProductTitle=js.getString("[0].title");
		System.out.println("1st product title =>"+frstProductTitle);
		
		float price=js.getFloat("[0].price");
		System.out.println("1st product price =>"+price);
		
		int count=js.getInt("[0].rating.count");
		System.out.println("1st product count =>"+count);
			
				
	}
	
	
	@Test
	public void getProductDataAPI_With_ExtractBody_With_JsonArray()
	{
		RestAssured.baseURI="https://fakestoreapi.com";
		
		Response response=given().log().all()
							.queryParam("limit", 10)
								.when().log().all()
									.get("/products");
		
		JsonPath js=response.jsonPath();
		
		List<Integer> idList    = js.getList("id");
		List<String>  titleList = js.getList("title");
		List<Object>rateList    = js.getList("rating.rate");
	//	List<Float> rateList	= js.getList("rating.rate", Float.class);
		List<Integer> countList = js.getList("rating.count");
		
		for(int i=0;i<idList.size();i++)
			{
			int id= idList.get(i);
			String title=titleList.get(i);
			Object rating=rateList.get(i);
			int count=countList.get(i);
			
			System.out.println("ID :"+id+ " "+"Title :"+title+" "+"Rating :"+rating+" "+"Count :"+count);
			}
		}
	@Test
	public void getProductDataAPI_With_ExtractBody_With_Json()
	{
		RestAssured.baseURI ="https://gorest.co.in";
		
		Response response= given().log().all()
							.header("Authorization","bearer e7ceb9cf8d544054f17778eb7631280ae715574858642094a7a671c3dc4ca3a8")
								.when().log().all()
									.get("/public/v2/users/3708372");
					
		
		JsonPath js=response.jsonPath();
		
		System.out.println("ID :"+js.getInt("id"));
		System.out.println("Email"+js.getString("email"));

	
	
	}
	
	@Test
	public void getProductDataAPI_With_ExtractBody_With_Json_Extract()
	{
	//Extract is used to fetch the single value
		
	RestAssured.baseURI ="https://gorest.co.in";
		
//	int userID	=	given().log().all()
//							.header("Authorization","bearer e7ceb9cf8d544054f17778eb7631280ae715574858642094a7a671c3dc4ca3a8")
//								.when().log().all()
//									.get("/public/v2/users/3708361")
//										.then()
//											.extract().path("id");	
//		
//	System.out.println(	userID);
//	
	
	
	//-->Using extract method we can also fetch the multiple values
	Response response =	given().log().all()
			.header("Authorization","bearer e7ceb9cf8d544054f17778eb7631280ae715574858642094a7a671c3dc4ca3a8")
				.when().log().all()
					.get("/public/v2/users/3708361")
						.then()
						.extract().
						response();
					
		int userID    =	response.path("id");
		String userEmail =	response.path("email");

		System.out.println(	userID);
		System.out.println(	userEmail);

	}

}
