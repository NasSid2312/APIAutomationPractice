package jsonValidatorTest;


import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class JsonPathTest {
	
	@Test
	public void getCircuitDataAPIWith_Year()
	{

		//RestAssured.baseURI="http://ergast.com/";
		
		String baseUrl= "http://ergast.com/api/f1";
		String year="2017";
        String url = baseUrl + "/" + year + "/circuits.json";

		
		Response response =given().log().all()
			.when().log().all()
			.get(url);
		
		String jsonString =	response.asString();
		System.out.println(jsonString);
		
		int circuitLength =JsonPath.read(jsonString,"$.MRData.CircuitTable.Circuits.length()"); //$..Circuits.length()
		System.out.println("circuit Length :"+circuitLength);
		
		List<String> countryList =	JsonPath.read(jsonString,"$..Circuits..country");
		
		System.out.println(countryList.size());
		System.out.println(countryList);

	}
	
	@Test
	public void getFakeProductAPITest()
	{
		RestAssured.baseURI ="https://fakestoreapi.com";
		
		Response response  = given().log().all()
								.when().log().all()
									.get("/products");
				
		String jsonString =response.asString();
		System.out.println(jsonString);
		
		//List<Integer> rateLessthan3 =JsonPath.read(jsonString,"$[?(@.rating.rate<3)].rating.rate");
		List<Float> rateLessthan3 =JsonPath.read(jsonString,"$[?(@.rating.rate<3)].rating.rate");
		System.out.println(rateLessthan3);
		
		List<Float> thrdPrice =JsonPath.read(jsonString,"$[?(@.id==3)].price");
		System.out.println(thrdPrice);
		
		System.out.println("With two attribute==========>");
				
		List<Map<String,Object>> jewelaryList =JsonPath.read(jsonString,"$[?(@.category =='jewelery')].[\"title\",\"price\"]");

		System.out.println(jewelaryList);	
		
		for(Map<String,Object> product :jewelaryList)
		{
			String title = (String) product.get("title");
			Object price =	(Object)product.get("price");
			System.out.println("Title :"+title);
			System.out.println("Price :"+price);
			System.out.println("************");

		}
		
		System.out.println("With three attribute========>");
		List<Map<String,Object>> threeAttr =JsonPath.read(jsonString,"$[?(@.category =='jewelery')].[\"title\",\"price\",\"id\"]");

		for(Map<String,Object> product :threeAttr)
		{
			String title = (String) product.get("title");
			Object price =	(Object)product.get("price");
			Integer id =	(Integer)product.get("id");

			System.out.println("Title :"+title);
			System.out.println("Price :"+price);
			System.out.println("ID :"+id);

			System.out.println("************");

		}
		System.out.println("With Four attribute========>");
		List<Map<String,Object>> fourAttr =JsonPath.read(jsonString,"$[?(@.category =='jewelery')].[\"title\",\"price\",\"id\",\"count\"]");

		for(Map<String,Object> Attr1 :fourAttr)
		{
			String title = (String) Attr1.get("title"); 
			Object price =	(Object)Attr1.get("price");
			Integer id =	(Integer) Attr1.get("id");
			Integer count = (Integer) Attr1.get("count");


			System.out.println("Title :"+title);
			System.out.println("Price :"+price);
			System.out.println("ID :"+id);
			System.out.println("Count :"+count);

			System.out.println("************");

		}
	}
	
}
