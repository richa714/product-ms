package com.org.product.model;

import lombok.Data;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Review {	
	private int id;
	private int rating;	
	private String comments;
	

}
