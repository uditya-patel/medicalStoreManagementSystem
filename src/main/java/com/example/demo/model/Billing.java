package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Component
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Billing {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long billId;

	@NotBlank(message = "Address should not be blank")
	private String address;

	@NotNull(message = "Amount should not be blank")
	private Long totalAmount;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "CUSTOMERID")
	// @NotBlank(message="id should not be blank")
	private Customer customer;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "bill_product", joinColumns = { @JoinColumn(name = "bill_id") }, inverseJoinColumns = {
			@JoinColumn(name = "product_id") })
	private Set<Product> products = new HashSet<>();

	public void addProduct(Product product) {
		this.products.add(product);
		product.getBills().add(this);
	}

	public void removeProduct(Integer productId) {
		Product product = this.products.stream().filter(t -> t.getProductId() == productId).findFirst().orElse(null);
		if (product != null) {
			this.products.remove(product);
			product.getBills().remove(this);
		}
	}

}
