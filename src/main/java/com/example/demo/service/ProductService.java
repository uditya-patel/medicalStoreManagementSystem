package com.example.demo.service;


import java.util.List;

import com.example.demo.exceptionhandler.BillNotFoundException;
import com.example.demo.exceptionhandler.ProductNotFoundException;
import com.example.demo.model.Product;

public interface ProductService {
	

	public Product addProduct(Long billId, Product product) throws ProductNotFoundException, BillNotFoundException;

	public Product getProductById(Integer productId) throws ProductNotFoundException;

	public Product updateProduct(Integer productId, Product product) throws ProductNotFoundException;

	public void deleteProduct(Integer productId) throws ProductNotFoundException;

	public void deleteProductFromBill(Long billId, Integer productId) throws BillNotFoundException;

	public List<Product> getAllProducts();

	public Product addProduct(Product product);

//	public List<Product> getAllProductsByBillId(Integer billId) throws BillNotFoundException;

//	public List<Product> getAllProductsBybillId(Integer billId);

}
