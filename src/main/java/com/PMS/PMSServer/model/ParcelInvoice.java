package com.PMS.PMSServer.model;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParcelInvoice {
	private int paymentId;
	private int invoiceNumber;
	private int transactionId;
	private LocalDateTime transactionDate;
	private double transactionAmount;
	private String transactionMode;
	private String transactionStatus;
}
