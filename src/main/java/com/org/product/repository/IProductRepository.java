package com.org.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.product.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product,Integer>{

}
