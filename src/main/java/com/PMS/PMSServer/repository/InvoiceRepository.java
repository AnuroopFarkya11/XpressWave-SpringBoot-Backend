package com.PMS.PMSServer.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.PMS.PMSServer.model.ParcelInvoice;
import com.PMS.PMSServer.util.DBUtil;


@Component
public class InvoiceRepository {
	private final String tableName="PMS_Parcel_Invoice";
	private final String delimeter=", ";
	private final String invoiceNumber="INVOICE_NUMBER";
	private final String paymentID ="PAYMENT_ID";
	private final String transactionId="TRANSACTION_ID";
	private final String transactionDate="TRANSACTION_DATE";
	private final String paymentAmount="PAYMENT_AMOUNT";
	private final String paymentMode="PAYMENT_MODE";
	private final String paymentStatus="PAYMENT_STATUS";
	private final String userId="USER_ID";
	private final String parcelId="PARCEL_ID";
	
	
	
	public boolean createTable() throws SQLException{
		String sql="CREATE TABLE "+ tableName +"("
	                                          + invoiceNumber +" INT PRIMARY KEY "+ delimeter 
	                                          + paymentID +" INT NOT NULL" + delimeter
	                                          + transactionId +" INT NOT NULL" + delimeter
	                                          + userId + " INT NOT NULL"   +delimeter
	                          				  + parcelId + " INT NOT NULL"  + delimeter
	                                          + transactionDate + " TIMESTAMP NOT NULL "  +delimeter
	                                          + paymentAmount + " DOUBLE NOT NULL " + delimeter
	                                          + paymentMode + " VARCHAR(255) NOT NULL " + delimeter
	                                          + paymentStatus  + " VARCHAR(255) NOT NULL " + delimeter
	                                          + "FOREIGN KEY (" + userId + ") REFERENCES PMS_USERS(ID) ON DELETE CASCADE" + delimeter
	                                          + "FOREIGN KEY (" + parcelId + ") REFERENCES PMS_PARCEL(PARCEL_ID) ON DELETE CASCADE" +")";
		
		System.out.println("CREATE Invoice TABLE QUERY :" +sql);
		Connection connection = DBUtil.getConnection();
		Statement statement = connection.createStatement();
		int n = statement.executeUpdate(sql);
		return n > 0;
	}    
	
	//add Transaction to table
	public boolean createTransaction(ParcelInvoice trans,int userId,int parcelId) throws Exception{
		Connection connection = DBUtil.getConnection();
		String sql="INSERT INTO "+ tableName + " VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1,trans.getInvoiceNumber());
		ps.setInt(2,trans.getPaymentId());
		ps.setInt(3,trans.getTransactionId());
		ps.setInt(4, userId);
		ps.setInt(5, parcelId);	
		ps.setTimestamp(6, Timestamp.valueOf(trans.getTransactionDate()));
		ps.setDouble(7,trans.getTransactionAmount());
		ps.setString(8, trans.getTransactionMode());
		ps.setString(9, trans.getTransactionStatus());
		
		
		int num = ps.executeUpdate();
		return num > 0;
	}

	//View All Transactions
	
	public List<ParcelInvoice> readAllTransactions() throws SQLException{
		String sql="SELECT * FROM "+ tableName;
		Statement statement = DBUtil.getConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);
		List<ParcelInvoice> transactions = new ArrayList<>();
		
		while (rs.next()) {

			ParcelInvoice trans= new ParcelInvoice(rs.getInt(paymentID),rs.getInt(invoiceNumber),rs.getInt(transactionId)
					,LocalDateTime.now(),rs.getDouble(paymentAmount),rs.getString(paymentMode)
					,rs.getString(paymentStatus));
				
		transactions.add(trans);
			
		}

		return transactions;
	}
	
	//View Transaction By UserId
	
	public ParcelInvoice getTransactionByInvoiceNumber(int iNum) throws Exception{
		String sql = "SELECT * FROM " + tableName + " WHERE " + invoiceNumber + " = ?";
		Connection connection = DBUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, iNum);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			ParcelInvoice trans= new ParcelInvoice(rs.getInt(paymentID),rs.getInt(invoiceNumber),rs.getInt(transactionId)
					,LocalDateTime.now(),rs.getDouble(paymentAmount),rs.getString(paymentMode)
					,rs.getString(paymentStatus));
			
			return trans;
			
		}
		return null;
	}
	
}
