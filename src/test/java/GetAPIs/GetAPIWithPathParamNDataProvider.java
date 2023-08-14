package GetAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GetAPIWithPathParamNDataProvider {

	@Test
	 public void GetCircuitDataAPIWith_PathParam_YearTest()
	 {
		RestAssured.baseURI="http://ergast.com";

		given().log().all()
			.pathParam("year", "2019")
			.when().log().all()
				.get("/api/f1/{year}/circuits.json")
					.then()
						.assertThat()
							.statusCode(200)
							.and()
								.body("MRData.CircuitTable.season",equalTo("2019"))
									.and()
									.body("MRData.CircuitTable.Circuits.circuitId",hasSize(21));
		
	 }


	@DataProvider
	public Object[][] getCircuitYearData()
	{
		return new Object[][] {
			{"2016",21},
			{"2017",20},
			{"2018",21},
			{"2019",21}
		};
	}
	
	
	
	
	@Test(dataProvider="getCircuitYearData")
	 public void GetCircuitDataAPI_WithDataProvider_YearTest(String year,int totalCircuit)
	 {
		RestAssured.baseURI="http://ergast.com";

		given().log().all()
			.pathParam("year",year)
			.when().log().all()
				.get("/api/f1/{year}/circuits.json")
					.then()
						.assertThat()
							.statusCode(200)
							.and()
								.body("MRData.CircuitTable.season",equalTo(year))
									.and()
									.body("MRData.CircuitTable.Circuits.circuitId",hasSize(totalCircuit));
		
	 }


}
