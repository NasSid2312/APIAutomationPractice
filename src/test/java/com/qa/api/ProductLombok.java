package com.qa.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductLombok {

	private int id;
	private String title;
	private float price;
	private String description;
	private String category;
	private String image;
	private Rating rating;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Rating{
		private double rate;
		private int count;
	}
}
