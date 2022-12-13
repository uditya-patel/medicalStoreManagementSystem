package com.example.demo.service;


import java.util.List;

import com.example.demo.exceptionhandler.BillNotFoundException;
import com.example.demo.model.Billing;

public interface BillingService {
	
//	String registration(Billing billing);
	
	String billCancel(Long id);
	
//	Billing createBill(Billing bill);

	Billing getBillById(Long billId) throws BillNotFoundException;

	List<Billing> getAllBills();

	void deletBillById(Long billId) throws BillNotFoundException;

	Billing updateBill(Long billId, Billing bill) throws BillNotFoundException;



}
