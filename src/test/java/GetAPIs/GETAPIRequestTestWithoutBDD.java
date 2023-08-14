package GetAPIs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETAPIRequestTestWithoutBDD {
	RequestSpecification request;
	
	@BeforeTest
	public void setUp()
	{
		RestAssured.baseURI="https://gorest.co.in/";
		request = RestAssured.given();
		request.header("Authorization","bearer e7ceb9cf8d544054f17778eb7631280ae715574858642094a7a671c3dc4ca3a8");
		
	}
	

	@Test()
	public void getALLUsersAPITest() {
				
		//============Response==========>
		
		// Get Method
		Response response= request.get("public/v2/users/");
		int statusCode=response.statusCode();
		System.out.println("Status code = "+statusCode);
		
		//Verification
		Assert.assertEquals(statusCode,200);
		
		//Status Mesg
		String statusMsg=response.statusLine();
		System.out.println("Status Message = "+statusMsg);
		
		//Fetch body
		response.prettyPrint();
				
		//Fetch header
		String contentType=response.header("Content-Type");
		System.out.println("Content-Type ="+contentType);
				
		//Fetch all headers
		List<Header> headerList=response.headers().asList();
		System.out.println("Header Size = "+headerList.size());
		
		//Print all list
		for(Header h:headerList) {
			System.out.println(h.getName()+" "+h.getValue());
		}
	}
		
	@Test	
		public void getALLUsersWithQueryParamAPITest() {
			
			//===========Request===========>
			System.out.println("<=======Get call with query parameter=======>");
			RestAssured.baseURI="https://gorest.co.in/";
			RequestSpecification request = RestAssured.given();
			request.queryParam("name", "Lakshmi");
			request.queryParam("status", "active");
			request.header("Authorization","bearer e7ceb9cf8d544054f17778eb7631280ae715574858642094a7a671c3dc4ca3a8");
			
			//============Response==========>
			
			// Get Method
			Response response= request.get("public/v2/users?name=Lakshmi&status=active");
			int statusCode=response.statusCode();
			System.out.println("Status code = "+statusCode);
			
			//Verification
			Assert.assertEquals(statusCode,200);
			
			//Status Mesg
			String statusMsg=response.statusLine();
			System.out.println("Status Message = "+statusMsg);
			
			//Fetch body
			response.prettyPrint();
		
		}	
	@Test	
	public void getALLUsersWithQueryParam_WithHashMap_APITest() {
		
		//===========Request===========>
		System.out.println("<=======Get call with query parametre and HashMap=======>");
		RestAssured.baseURI="https://gorest.co.in/";
		RequestSpecification request = RestAssured.given();
		
		Map<String,String> queryParamMap= new HashMap<String,String>();
		queryParamMap.put("name", "Lakshmi");
		queryParamMap.put("status", "active");
		queryParamMap.put("gender", "male");
		
		//request.queryParams(queryParamMap);

		request.header("Authorization","bearer e7ceb9cf8d544054f17778eb7631280ae715574858642094a7a671c3dc4ca3a8");
		
		//============Response==========>
		
		// Get Method
		Response response= request.get("public/v2/users?name=Lakshmi&status=active");
		int statusCode=response.statusCode();
		System.out.println("Status code = "+statusCode);
		
		//Verification
		Assert.assertEquals(statusCode,200);
		
		//Status Mesg
		String statusMsg=response.statusLine();
		System.out.println("Status Message = "+statusMsg);
		
		//Fetch body
		response.prettyPrint();
	}	

}
