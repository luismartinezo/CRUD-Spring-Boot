/**
 * 
 */
package com.ps.Docker.Controller;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.ps.Docker.DTO.Message;
import com.ps.Docker.Entity.Product;
import com.ps.Docker.Service.ProductService;

/**
 * @author LUIS MARTINEZ
 *
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/list")
	public ResponseEntity<List<Product>> getLista() {
		List<Product> lista = productService.obtenerTodos();
		return new ResponseEntity<List<Product>>(lista, HttpStatus.OK);
	}

	@GetMapping("/detail/{id}")
	public ResponseEntity<Product> getOne(@PathVariable Long id) {
		if (!productService.existsById(id))
			return new ResponseEntity(new Message("there is no such product"), HttpStatus.NOT_FOUND);
		Product product = productService.findById(id).get();
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@PostMapping("new")
	public ResponseEntity<?> create(@RequestBody Product product) {
		if (StringUtils.isBlank(product.getProductName()))
			return new ResponseEntity(new Message("the name is required"), HttpStatus.BAD_REQUEST);
		if ((Integer) product.getPrice() == null || product.getPrice() == 0)
			return new ResponseEntity(new Message("the price is required"), HttpStatus.BAD_REQUEST);
		if (productService.existsByProductName(product.getProductName()))
			return new ResponseEntity(new Message("that name already exists"), HttpStatus.BAD_REQUEST);
		productService.save(product);
		return new ResponseEntity(new Message("saved product"), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@RequestBody Product product, @PathVariable("id") Long id) {
		if (!productService.existsById(id))
			return new ResponseEntity(new Message("there is no such product"), HttpStatus.NOT_FOUND);
		if (StringUtils.isBlank(product.getProductName()))
			return new ResponseEntity(new Message("the name is required"), HttpStatus.BAD_REQUEST);
		if ((Integer) product.getPrice() == null || product.getPrice() == 0)
			return new ResponseEntity(new Message("the price is required"), HttpStatus.BAD_REQUEST);
		if ((Integer) product.getAmount() == null || product.getAmount() == 0)
			return new ResponseEntity(new Message("There is not stock for the product"), HttpStatus.BAD_REQUEST);
		if (productService.existsByProductName(product.getProductName())
				&& productService.findByProductName(product.getProductName()).get().getId() != id)
			return new ResponseEntity(new Message("the name already exists"), HttpStatus.BAD_REQUEST);

		Product prodUpdate = productService.findById(id).get();
		prodUpdate.setProductName(product.getProductName());
		prodUpdate.setPrice(product.getPrice());
		prodUpdate.setAmount(product.getAmount());
		prodUpdate.setDescription(product.getDescription());
		prodUpdate.setImg(product.getImg());
		productService.save(prodUpdate);
		return new ResponseEntity(new Message("updated product"), HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if (!productService.existsById(id))
			return new ResponseEntity(new Message("product does not exist"), HttpStatus.NOT_FOUND);
		productService.delete(id);
		return new ResponseEntity(new Message("removed product"), HttpStatus.OK);
	}

}
