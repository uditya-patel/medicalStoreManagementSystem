package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptionhandler.BillNotFoundException;
import com.example.demo.exceptionhandler.ProductNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/addProductToBill/{billId}")
	public ResponseEntity<Product> addProduct(@PathVariable(value = "billId") Long billId,
			@RequestBody Product product) throws ProductNotFoundException, BillNotFoundException {
		Product _product = productService.addProduct(billId, product);

		return new ResponseEntity<>(_product, HttpStatus.CREATED);
	}

	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product _product = productService.addProduct(product);

		return new ResponseEntity<>(_product, HttpStatus.CREATED);
	}

	@GetMapping("/getProductById/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Integer productId)
			throws ProductNotFoundException {
		Product product = productService.getProductById(productId);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("/getAllProducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);

	}

	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer productId, @RequestBody Product product)
			throws ProductNotFoundException {
		Product _product = productService.updateProduct(productId, product);
		return new ResponseEntity<>(_product, HttpStatus.OK);
	}

	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Integer productId)
			throws ProductNotFoundException {
		productService.deleteProduct(productId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/deleteProductFromBill/{billId}/{productId}")
	public ResponseEntity<HttpStatus> deleteProductFromBill(@PathVariable(value = "billId") Long billId,
			@PathVariable(value = "productId") Integer productId) throws BillNotFoundException {
		productService.deleteProductFromBill(billId, productId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/*
	 * @GetMapping("/getAllProductsByBillId/{billId}") public
	 * ResponseEntity<List<Product>> getAllProductsByBillId(@PathVariable(value =
	 * "billId") Integer billId) throws BillNotFoundException { List<Product>
	 * products = productService.getAllProductsByBillId(billId);
	 * 
	 * return new ResponseEntity<>(products, HttpStatus.OK);
	 * 
	 * }
	 */

}
