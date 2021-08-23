/**
 * 
 */
package com.ps.Docker.Repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ps.Docker.Entity.Product;

/**
 * @author LUIS MARTINEZ
 *
 */
public interface ProductRepository extends JpaRepository<Product, Long>{

	Optional<Product> findByProductName(String np);
    boolean existsByProductName(String np);
}
