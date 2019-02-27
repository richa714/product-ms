package com.org.product.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Richa_Arora Product model class
 *
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
public class Product {

	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
	//@SequenceGenerator(name = "product_generator", sequenceName = "product_sequence", allocationSize = 50)
	@GeneratedValue
	private int id;
	private String name;

	private String description;

	private Double price;

	private String type;

}
