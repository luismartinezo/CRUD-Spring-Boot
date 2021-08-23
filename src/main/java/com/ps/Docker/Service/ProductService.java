/**
 * 
 */
package com.ps.Docker.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.ps.Docker.Entity.Product;
import com.ps.Docker.Repository.ProductRepository;

/**
 * @author LUIS MARTINEZ
 *
 */
@Service
@Transactional
public class ProductService {


    @Autowired
    ProductRepository productRepository;

    public List<Product> obtenerTodos(){
        List<Product> lista = productRepository.findAll();
        return lista;
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public Optional<Product> findByProductName(String np){
        return productRepository.findByProductName(np);
    }

    public void save(Product product){
        productRepository.save(product);
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }

    public boolean existsByProductName(String np){
        return productRepository.existsByProductName(np);
    }

    public boolean existsById(Long id){
        return productRepository.existsById(id);
    }
}
