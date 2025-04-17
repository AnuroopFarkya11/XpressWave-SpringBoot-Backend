package com.PMS.PMSServer.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PMS.PMSServer.exception.user.UserException;
import com.PMS.PMSServer.exception.user.UserNotFoundException;
import com.PMS.PMSServer.model.ParcelInvoice;
import com.PMS.PMSServer.repository.InvoiceRepository;
import com.PMS.PMSServer.util.Utils;
import com.PMS.PMSServer.util.Validators;


@Service
public class InvoiceService {
	
	@Autowired
	private InvoiceRepository repository;
	
	public InvoiceService() {
		repository=new InvoiceRepository();
	}
	
	public boolean createTable() throws SQLException {
		try {
			boolean res = repository.createTable();

			System.out.println("Created Invoice Table Successfully!");

			return res;

		} catch (SQLException e) {

			if (!e.getLocalizedMessage().toLowerCase().contains("already exist")) {
				e.printStackTrace();
				throw e;
			}

		}
		System.out.println("Connected Invoice Table Successfully!");

		return true;

	}
	
	
	
	public ParcelInvoice createTransaction(int userId,int parcelId,ParcelInvoice transaction) throws Exception{
		try {
			
			int invoiceNumber = Utils.generateRandomId();
			int transactionID = Utils.generateRandomId();
			int  paymentId = Utils.generateRandomId();
			
			transaction.setPaymentId(paymentId);
			transaction.setInvoiceNumber(invoiceNumber);
			transaction.setTransactionId(transactionID);
			transaction.setTransactionDate(LocalDateTime.now());
			
			boolean res=repository.createTransaction(transaction,userId,parcelId);
			if(res) {
				return transaction;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<ParcelInvoice> readAllTransactions() {
		try {
			return repository.readAllTransactions();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public ParcelInvoice getTransactionByInoviceNumber(int invoiceNumber) throws Exception{
		Validators.validateId("Inovice number", invoiceNumber);
		try {
			ParcelInvoice transaction = repository.getTransactionByInvoiceNumber(invoiceNumber);
			if (transaction != null) {
				return transaction;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		throw new UserNotFoundException(invoiceNumber);
	}
		
}
