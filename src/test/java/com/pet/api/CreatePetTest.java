package com.pet.api;
import static io.restassured.RestAssured.given;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.api.PetLombok.Category;
import com.pet.api.PetLombok.Tag;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class CreatePetTest {
	
	@Test
	public void createPetTest()
	{
		RestAssured.baseURI="https://petstore.swagger.io";
	
		Category category =new Category(1, "Dog");
		
		List<String> photoUrls =Arrays.asList("https://dogs.com","https://dogs1.com");
	
		Tag tag1=new Tag(5,"red");
		Tag tag2=new Tag(6,"blue");

		List<Tag> tags =Arrays.asList(tag1,tag2);
		
		PetLombok pet = new PetLombok(300,category,"Ronney",photoUrls,tags,"avaiable");
		
		
		Response response =given()
					.contentType(ContentType.JSON)
						.body(pet)  ///serailization
							.when()
								.post("/v2/pet");
		
		System.out.println(response.statusCode());
		response.prettyPrint();
		
		//De-serialization
		ObjectMapper mapper = new ObjectMapper();
		
		
	//	PetLombok petResp;
		try {
			PetLombok petResp = mapper.readValue(response.getBody().asString(),PetLombok.class);
		
			System.out.println(petResp.getId());
			System.out.println(petResp.getName());
			System.out.println(petResp.getStatus());

			System.out.println(petResp.getCategory().getId());
			System.out.println(petResp.getCategory().getName());
			
			System.out.println(petResp.getPhotoUrls());
			
			System.out.println(petResp.getTags().get(0).getId());
			System.out.println(petResp.getTags().get(0).getName());




		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		


		
	}

/*
	@Test
	public void createPet_With BuilderPattern()
	{
		RestAssured.baseURI="https://petstore.swagger.io";
	
		Category category =new Category.CategoryBuilder()
			.id(200)
			.name("Cat")
			.build();
		
		Tag tag1=new Tag.TagBuilder()
		.id(50)
		.name("Blue");
		.build();
		
		Tag tag2=new Tag.TagBuilder()
				.id(51)
				.name("Green");
				.build();
				
		
		PetLombok pet =new PetLombok.PetLombokBuilder()
			.id(5000)
			.category(category)
			.name("Gucci")
			.photUrls(Arrays.asList("https://dogs.com","https://dogs1.com"))
			.tags(Arrays.asList(tag1,tag2))
			.status("avaiable")
			.build();
			
		Response response =given()
					.contentType(ContentType.JSON)
						.body(pet)  ///serailization
							.when()
								.post("/v2/pet");
		
		System.out.println(response.statusCode());
		response.prettyPrint();
		
		//De-serialization
		ObjectMapper mapper = new ObjectMapper();
		
		
		PetLombok petResp=mapper.readValue(response.getBody().asString(),PetLombok.class);
		System.out.println(petResp.getId());
		System.out.println(petResp.getName());
		System.out.println(petResp.getStatus());
		System.out.println(petResp.getCategory().getId());
		System.out.println(petResp.getCategory().getName());
		System.out.println(petResp.getPhotoUrls());
		
		System.out.println(petResp.getTags().get(0).getId());
		System.out.println(petResp.getTags().get(0).getName());

		System.out.println(petResp.getTags().get(1).getId());
		System.out.println(petResp.getTags().get(2).getName());


		
	
	}
	*/
}
