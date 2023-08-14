package com.pet.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetLombok {
//https://projectlombok.org/p2
	private Integer id;
	private Category category;
	private String name;
	private List<String> photoUrls;
	private List<Tag> tags;
	private String status;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Category{
		private Integer id;
		private String name;
	}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Tag
	{
		private Integer id;
		private String name;
	}
}
