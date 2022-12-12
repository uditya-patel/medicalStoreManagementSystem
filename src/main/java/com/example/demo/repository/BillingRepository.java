package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Billing;
@Repository
public interface BillingRepository extends JpaRepository<Billing,Long>{
	
	public Billing findByBillId(long id);

}
