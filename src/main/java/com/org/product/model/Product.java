package com.org.product.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Richa_Arora Product model class
 *
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Product {

	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
	//@SequenceGenerator(name = "product_generator", sequenceName = "product_sequence", allocationSize = 50)
	//@GeneratedValue
	private int id;
	private String name;

	private String description;

	private Double price;

	private String type;
	
	@Transient	
	private List<Review> reviews;

}
