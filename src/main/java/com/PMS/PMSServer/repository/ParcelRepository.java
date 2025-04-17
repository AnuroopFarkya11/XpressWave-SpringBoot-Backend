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
import java.util.Random;

import com.PMS.PMSServer.model.PMSParcel;
import com.PMS.PMSServer.util.DBUtil;

public class ParcelRepository {

	private final String tableName = "PMS_PARCEL";

	private final String delimeter = ", ";

	private final String parcelId = "PARCEL_ID";
	private final String userId = "USER_ID";
	private final String senderName = "SENDER_NAME";
	private final String senderAddress = "SENDER_ADDRESS";
	private final String senderPin = "SENDER_PIN";
	private final String senderMobile = "SENDER_MOBILE";
	private final String recieverName = "RECIEVER_NAME";
	private final String recieverAddress = "RECIEVER_ADDRESS";
	private final String recieverPin = "RECIEVER_PIN";
	private final String recieverMobile = "RECIEVER_MOBILE";
	private final String parcelLength = "PARCEL_LENGTH";
	private final String parcelBreadth = "PARCEL_BREADTH";
	private final String parcelSize = "PARCEL_SIZE";
	private final String parcelWeight = "PARCEL_WEIGHT";
	private final String parcelContentDescription = "PARCEL_CONTENT";
	private final String deliveryType = "DELIVERY_TYPE";
	private final String packingPreference = "PACKING_PREFERENCE";
	private final String placedTime = "PLACED_TIME";
	private final String pickupTime = "PICKUP_TIME";
	private final String dropoffTime = "DROPOFF_TIME";
	private final String serviceCost = "SERVICE_COST";
	private final String paymentStatus = "PAYMENT_STATUS";
	private final String paymentTime = "PAYMENT_TIME";
	private final String parcelStatus = "PARCEL_STATUS";

	public boolean createTable() throws SQLException {
//		String sql = "CREATE TABLE " + tableName + " (" + parcelId + " INT PRIMARY KEY" + delimeter + userId
//				+ " INT NOT NULL" + delimeter + senderName + " VARCHAR(255) NOT NULL" + delimeter + senderAddress
//				+ " VARCHAR(255) NOT NULL" + delimeter + senderPin + " VARCHAR(255) NOT NULL" + delimeter + senderMobile
//				+ " VARCHAR(255) NOT NULL" + delimeter + recieverName + " VARCHAR(255) NOT NULL" + delimeter
//				+ recieverAddress + " VARCHAR(255) NOT NULL" + delimeter + recieverPin + " VARCHAR(255) NOT NULL"
//				+ delimeter + recieverMobile + " VARCHAR(255) NOT NULL" + delimeter + parcelLength + " DOUBLE NOT NULL"
//				+ delimeter + parcelBreadth + " DOUBLE NOT NULL" + delimeter + parcelSize + " DOUBLE NOT NULL"
//				+ delimeter + parcelWeight + " DOUBLE NOT NULL" + delimeter + parcelContentDescription
//				+ " VARCHAR(255) NOT NULL" + delimeter + deliveryType + " VARCHAR(255) NOT NULL" + delimeter
//				+ packingPreference + " VARCHAR(255) NOT NULL" + delimeter + placedTime + " TIMESTAMP NOT NULL"
//				+ delimeter + pickupTime + " TIMESTAMP NOT NULL" + delimeter + dropoffTime + " TIMESTAMP NOT NULL"
//				+ delimeter + serviceCost + " DOUBLE NOT NULL" + delimeter + paymentStatus + " VARCHAR(25) NOT NULL"
//				+ delimeter + paymentTime + " TIMESTAMP NOT NULL" + delimeter + parcelStatus + " VARCHAR(255) NOT NULL"
//				+ delimeter + " FOREIGN KEY (" + userId + ") REFERENCES PMS_USERS(id) ON DELETE CASCADE" + ")";
		String sql = "CREATE TABLE " + tableName + " (" + parcelId + " INT PRIMARY KEY" + delimeter + userId
				+ " INT NOT NULL" + delimeter + senderName + " VARCHAR(255) NOT NULL" + delimeter + senderAddress
				+ " VARCHAR(255) NOT NULL" + delimeter + senderPin + " VARCHAR(255) NOT NULL" + delimeter + senderMobile
				+ " VARCHAR(255) NOT NULL" + delimeter + recieverName + " VARCHAR(255) NOT NULL" + delimeter
				+ recieverAddress + " VARCHAR(255) NOT NULL" + delimeter + recieverPin + " VARCHAR(255) NOT NULL"
				+ delimeter + recieverMobile + " VARCHAR(255) NOT NULL" + delimeter + parcelWeight + " DOUBLE NOT NULL"
				+ delimeter + parcelLength + " DOUBLE NOT NULL" + delimeter + parcelBreadth + " DOUBLE NOT NULL"
				+ delimeter + parcelSize + " DOUBLE NOT NULL" + delimeter + parcelContentDescription
				+ " VARCHAR(255) NOT NULL" + delimeter + deliveryType + " VARCHAR(255) NOT NULL" + delimeter
				+ packingPreference + " VARCHAR(255) NOT NULL" + delimeter + placedTime + " TIMESTAMP NOT NULL"
				+ delimeter + pickupTime + " TIMESTAMP NOT NULL" + delimeter + dropoffTime + " TIMESTAMP NOT NULL"
				+ delimeter + serviceCost + " DOUBLE NOT NULL" + delimeter + paymentStatus + " VARCHAR(25) NOT NULL"
				+ delimeter + paymentTime + " TIMESTAMP NOT NULL" + delimeter + parcelStatus + " VARCHAR(255) NOT NULL"
				+ delimeter + " FOREIGN KEY (" + userId + ") REFERENCES PMS_USERS(id) ON DELETE CASCADE" + ")";

		System.out.println("CREATE PARCEL TABLE QUERY :" + sql);

		Connection connection = DBUtil.getConnection();
		Statement statement = connection.createStatement();
		int n = statement.executeUpdate(sql);
		return n > 0;
	}

	public static int generateRandomBookingId() {
		Random random = new Random();
		int id = random.nextInt(100000);
		return id;

	}

	public boolean createParcel(int userId, PMSParcel parcel) throws SQLException {
		Connection connection = DBUtil.getConnection();
//		String sql = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//
//		PreparedStatement ps = connection.prepareStatement(sql);
//
//		ps.setInt(1, parcel.getParcel_Id());
//		ps.setInt(2, userId);
//		ps.setString(3, parcel.getSenderName());
//		ps.setString(4, parcel.getSenderAddress());
//		ps.setString(5, parcel.getSenderPin());
//		ps.setString(6, parcel.getSenderMobile());
//		ps.setString(7, parcel.getReceiverName());
//		ps.setString(8, parcel.getReceiverAddress());
//		ps.setString(9, parcel.getReceiverPin());
//		ps.setString(10, parcel.getReceiverMobile());
//		ps.setDouble(11, parcel.getParcelWeightInGram());
//		ps.setString(12, parcel.getParcelContentsDescription());
//		ps.setString(13, parcel.getParcelDeliveryType());
//		ps.setString(14, parcel.getParcelPackingPreference());
//		ps.setTimestamp(15, Timestamp.valueOf(parcel.getParcelPlacedTime()));
//		ps.setTimestamp(16, Timestamp.valueOf(LocalDateTime.now()));
//		ps.setTimestamp(17, Timestamp.valueOf(LocalDateTime.now()));
//		ps.setDouble(18, parcel.getServiceCost());
//		ps.setString(19, parcel.getPaymentStatus());
//		ps.setTimestamp(20, Timestamp.valueOf(LocalDateTime.now()));
//		ps.setString(21, parcel.getParcelStatus());
		
		String sql = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        int id = ParcelRepository.generateRandomBookingId();
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1,parcel.getParcel_Id());
        ps.setInt(2, userId);
        ps.setString(3, parcel.getSenderName());
        ps.setString(4, parcel.getSenderAddress());
        ps.setString(5, parcel.getSenderPin());
        ps.setString(6, parcel.getSenderMobile());
        ps.setString(7, parcel.getReceiverName());
        ps.setString(8, parcel.getReceiverAddress());
        ps.setString(9, parcel.getReceiverPin());
        ps.setString(10, parcel.getReceiverMobile());
        ps.setDouble(11, parcel.getParcelWeightInGram());
        ps.setDouble(12, parcel.getParcelLength());
        ps.setDouble(13, parcel.getParcelBreadth());
        ps.setDouble(14, parcel.getParcelSize());
        ps.setString(15, parcel.getParcelContentsDescription());
        ps.setString(16, parcel.getParcelDeliveryType());
        ps.setString(17, parcel.getParcelPackingPreference());
        ps.setTimestamp(18, Timestamp.valueOf(parcel.getParcelPlacedTime()));
        ps.setTimestamp(19, Timestamp.valueOf(LocalDateTime.now()));
        ps.setTimestamp(20, Timestamp.valueOf(LocalDateTime.now()));
        ps.setDouble(21, parcel.getServiceCost());
        ps.setString(22, parcel.getPaymentStatus());
        ps.setTimestamp(23, Timestamp.valueOf(LocalDateTime.now()));
        ps.setString(24, parcel.getParcelStatus());

		int num = ps.executeUpdate();

		return num > 0;

	}

	public List<PMSParcel> readAllParcel() throws SQLException {
		String sql = "SELECT * FROM " + tableName;
		Connection connection = DBUtil.getConnection();
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		List<PMSParcel> parcels = new ArrayList<>();

		while (rs.next()) {
			int bId = rs.getInt(1);
//            int userId = rs.getInt(2);
			String senderName = rs.getString(3);
			String senderAddress = rs.getString(4);
			String senderPin = rs.getString(5);
			String senderMobile = rs.getString(6);
			String receiverName = rs.getString(7);
			String receiverAddress = rs.getString(8);
			String receiverPin = rs.getString(9);
			String receiverMobile = rs.getString(10);
			double parcelWeightInGram = rs.getDouble(11);
			double parcelLength = rs.getDouble(12);
			double parcelBreadth = rs.getDouble(13);
			double parcelSize = rs.getDouble(14);
			String parcelContentsDescription = rs.getString(15);
			String parcelDeliveryType = rs.getString(16);
			String parcelPackingPreference = rs.getString(17);
			LocalDateTime parcelPlacedTime = rs.getTimestamp(18).toLocalDateTime();
			LocalDateTime parcelPickupTime = rs.getTimestamp(19).toLocalDateTime();
			LocalDateTime parcelDropoffTime = rs.getTimestamp(20).toLocalDateTime();
			double serviceCost = rs.getDouble(21);
			String paymentStatus = rs.getString(22);
			LocalDateTime paymentTime = rs.getTimestamp(23).toLocalDateTime();
			String parcelStatus = rs.getString(24);

			// PMSParcel
			PMSParcel parcel = new PMSParcel(bId, senderName, senderAddress, senderPin, senderMobile, receiverName,
					receiverAddress, receiverPin, receiverMobile, parcelWeightInGram, parcelLength, parcelBreadth,
					parcelSize, parcelContentsDescription, parcelDeliveryType, parcelPackingPreference,
					parcelPlacedTime, parcelPickupTime, parcelDropoffTime, serviceCost, paymentStatus, paymentTime,
					parcelStatus);
			parcels.add(parcel);
		}
		return parcels;
	}

	public List<PMSParcel> readParcelsByUserId(int uid) throws SQLException {
		String sql = "SELECT * FROM " + tableName + " WHERE " + userId + " = ?";
		Connection connection = DBUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, uid);
		ResultSet rs = ps.executeQuery();

		List<PMSParcel> parcels = new ArrayList<>();

		while (rs.next()) {
			int bId = rs.getInt(1);
//			int userId = rs.getInt(2);
			String senderName = rs.getString(3);
			String senderAddress = rs.getString(4);
			String senderPin = rs.getString(5);
			String senderMobile = rs.getString(6);
			String receiverName = rs.getString(7);
			String receiverAddress = rs.getString(8);
			String receiverPin = rs.getString(9);
			String receiverMobile = rs.getString(10);
			double parcelWeightInGram = rs.getDouble(11);
			double parcelLength = rs.getDouble(12);
			double parcelBreadth = rs.getDouble(13);
			double parcelSize = rs.getDouble(14);
			String parcelContentsDescription = rs.getString(15);
			String parcelDeliveryType = rs.getString(16);
			String parcelPackingPreference = rs.getString(17);
			LocalDateTime parcelPlacedTime = rs.getTimestamp(18).toLocalDateTime();
			LocalDateTime parcelPickupTime = rs.getTimestamp(19).toLocalDateTime();
			LocalDateTime parcelDropoffTime = rs.getTimestamp(20).toLocalDateTime();
			double serviceCost = rs.getDouble(21);
			String paymentStatus = rs.getString(22);
			LocalDateTime paymentTime = rs.getTimestamp(23).toLocalDateTime();
			String parcelStatus = rs.getString(24);

			// PMSParcel
			PMSParcel parcel = new PMSParcel(bId, senderName, senderAddress, senderPin, senderMobile, receiverName,
					receiverAddress, receiverPin, receiverMobile, parcelWeightInGram, parcelLength, parcelBreadth,
					parcelSize, parcelContentsDescription, parcelDeliveryType, parcelPackingPreference,
					parcelPlacedTime, parcelPickupTime, parcelDropoffTime, serviceCost, paymentStatus, paymentTime,
					parcelStatus);
			parcels.add(parcel);
		}
		return parcels;
	}

	public PMSParcel readParcelByParcelId(int bookingId) throws SQLException {
		String sql = "SELECT * FROM " + tableName + " WHERE " + parcelId + " = ?";
		Connection connection = DBUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, bookingId);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int bId = rs.getInt(1);
//			int userId = rs.getInt(2);
			String senderName = rs.getString(3);
			String senderAddress = rs.getString(4);
			String senderPin = rs.getString(5);
			String senderMobile = rs.getString(6);
			String receiverName = rs.getString(7);
			String receiverAddress = rs.getString(8);
			String receiverPin = rs.getString(9);
			String receiverMobile = rs.getString(10);
			double parcelWeightInGram = rs.getDouble(11);
			double parcelLength = rs.getDouble(12);
			double parcelBreadth = rs.getDouble(13);
			double parcelSize = rs.getDouble(14);
			String parcelContentsDescription = rs.getString(15);
			String parcelDeliveryType = rs.getString(16);
			String parcelPackingPreference = rs.getString(17);
			LocalDateTime parcelPlacedTime = rs.getTimestamp(18).toLocalDateTime();
			LocalDateTime parcelPickupTime = rs.getTimestamp(19).toLocalDateTime();
			LocalDateTime parcelDropoffTime = rs.getTimestamp(20).toLocalDateTime();
			double serviceCost = rs.getDouble(21);
			String paymentStatus = rs.getString(22);
			LocalDateTime paymentTime = rs.getTimestamp(23).toLocalDateTime();
			String parcelStatus = rs.getString(24);

			// PMSParcel
			PMSParcel parcel = new PMSParcel(bId, senderName, senderAddress, senderPin, senderMobile, receiverName,
					receiverAddress, receiverPin, receiverMobile, parcelWeightInGram, parcelLength, parcelBreadth,
					parcelSize, parcelContentsDescription, parcelDeliveryType, parcelPackingPreference,
					parcelPlacedTime, parcelPickupTime, parcelDropoffTime, serviceCost, paymentStatus, paymentTime,
					parcelStatus);
			return parcel;
		}
		return null;
	}

	public PMSParcel updateParcel(int bId, PMSParcel parcel) throws SQLException {

        StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");

        String mark = " = ?";
        /// todo add check for id

        if (parcel.getSenderName() != null && !parcel.getSenderName().isEmpty()) {
            sql.append(senderName + mark + delimeter);
        }
        if (parcel.getSenderAddress() != null && !parcel.getSenderAddress().isEmpty()) {
            sql.append(senderAddress + mark + delimeter);
        }
        if (parcel.getSenderPin() != null && !parcel.getSenderPin().isEmpty()) {
            sql.append(senderPin + mark + delimeter);
        }
        if (parcel.getSenderMobile() != null && !parcel.getSenderMobile().isEmpty()) {
            sql.append(senderMobile + mark + delimeter);
        }
        if (parcel.getReceiverName() != null && !parcel.getReceiverName().isEmpty()) {
            sql.append(recieverName + mark + delimeter);
        }
        if (parcel.getReceiverAddress() != null && !parcel.getReceiverAddress().isEmpty()) {
            sql.append(recieverAddress + mark + delimeter);
        }
        if (parcel.getReceiverPin() != null && !parcel.getReceiverPin().isEmpty()) {
            sql.append(recieverPin + mark + delimeter);
        }
        if (parcel.getReceiverMobile() != null && !parcel.getReceiverMobile().isEmpty()) {

            sql.append(recieverMobile + mark + delimeter);
        }
        if (parcel.getParcelWeightInGram() != null && parcel.getParcelWeightInGram() >= 0) {
            sql.append(parcelWeight + mark + delimeter);
        }
        if (parcel.getParcelLength() != null && parcel.getParcelLength() >= 0) {
            sql.append(parcelLength + mark + delimeter);
        }
        if (parcel.getParcelBreadth() != null && parcel.getParcelBreadth() >= 0) {
            sql.append(parcelBreadth + mark + delimeter);
        }
        if (parcel.getParcelSize() != null && parcel.getParcelSize() >= 0) {
            sql.append(parcelSize + mark + delimeter);
        }
        if (parcel.getParcelContentsDescription() != null && !parcel.getParcelContentsDescription().isEmpty()) {
            sql.append(parcelContentDescription + mark + delimeter);
        }
        if (parcel.getParcelDeliveryType() != null && !parcel.getParcelDeliveryType().isEmpty()) {
            sql.append(deliveryType + mark + delimeter);
        }
        if (parcel.getParcelPackingPreference() != null && !parcel.getParcelPackingPreference().isEmpty()) {
            sql.append(packingPreference + mark + delimeter);
        }
        if (parcel.getParcelPlacedTime() != null) {
            // todo validate the format
            sql.append(packingPreference + mark + delimeter);
        }

        if (parcel.getParcelPickupTime() != null) {
            // todo validate the format
            sql.append(pickupTime + mark + delimeter);
        }
        if (parcel.getParcelDropoffTime() != null) {
            // todo validate the format
            sql.append(dropoffTime + mark + delimeter);
        }
        if (parcel.getServiceCost() != null && parcel.getServiceCost() >= 0) {
            sql.append(serviceCost + mark + delimeter);
        }
        if (parcel.getPaymentStatus() != null && !parcel.getPaymentStatus().isEmpty()) {
            sql.append(paymentStatus + mark + delimeter);
        }
        if (parcel.getParcelPaymentTime() != null) {
            // todo validate the format
            sql.append(paymentTime + mark + delimeter);
        }
        if (parcel.getParcelStatus() != null && !parcel.getParcelStatus().isEmpty()) {
            sql.append(parcelStatus + mark + delimeter);
        }

        sql.setLength(sql.length() - 2);

        sql.append(" WHERE " + parcelId + " = " + bId);
        PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql.toString());

        int indexParam = 1;

        if (parcel.getSenderName() != null && !parcel.getSenderName().isEmpty()) {
            ps.setString(indexParam, parcel.getSenderName());
            indexParam++;
        }
        if (parcel.getSenderAddress() != null && !parcel.getSenderAddress().isEmpty()) {
            ps.setString(indexParam, parcel.getSenderAddress());
            indexParam++;
        }
        if (parcel.getSenderPin() != null && !parcel.getSenderPin().isEmpty()) {
            ps.setString(indexParam, parcel.getSenderPin());
            indexParam++;
        }
        if (parcel.getSenderMobile() != null && !parcel.getSenderMobile().isEmpty()) {
            ps.setString(indexParam, parcel.getSenderMobile());
            indexParam++;
        }
        if (parcel.getReceiverName() != null && !parcel.getReceiverName().isEmpty()) {
            ps.setString(indexParam, parcel.getReceiverName());
            indexParam++;
        }
        if (parcel.getReceiverAddress() != null && !parcel.getReceiverAddress().isEmpty()) {
            ps.setString(indexParam, parcel.getReceiverAddress());
            indexParam++;
        }
        if (parcel.getReceiverPin() != null && !parcel.getReceiverPin().isEmpty()) {
            ps.setString(indexParam, parcel.getReceiverPin());
            indexParam++;
        }
        if (parcel.getReceiverMobile() != null && !parcel.getReceiverMobile().isEmpty()) {
            ps.setString(indexParam, parcel.getReceiverMobile());
            indexParam++;
        }
        if (parcel.getParcelWeightInGram() != null) {
            ps.setDouble(indexParam, parcel.getParcelWeightInGram());
            indexParam++;
        }
        if (parcel.getParcelLength() != null && parcel.getParcelLength() >= 0) {
            ps.setDouble(indexParam, parcel.getParcelLength());
            indexParam++;
        }
        if (parcel.getParcelBreadth() != null && parcel.getParcelBreadth() >= 0) {
            ps.setDouble(indexParam, parcel.getParcelBreadth());
            indexParam++;
        }
        if (parcel.getParcelSize() != null && parcel.getParcelSize() >= 0) {
            ps.setDouble(indexParam, parcel.getParcelSize());
            indexParam++;
        }
        if (parcel.getParcelContentsDescription() != null && !parcel.getParcelContentsDescription().isEmpty()) {
            ps.setString(indexParam, parcel.getParcelContentsDescription());
            indexParam++;
        }
        if (parcel.getParcelDeliveryType() != null && !parcel.getParcelDeliveryType().isEmpty()) {
            ps.setString(indexParam, parcel.getParcelDeliveryType());
            indexParam++;
        }
        if (parcel.getParcelPackingPreference() != null && !parcel.getParcelPackingPreference().isEmpty()) {
            ps.setString(indexParam, parcel.getParcelPackingPreference());
            indexParam++;
        }
        if (parcel.getParcelPlacedTime() != null) {
            ps.setTimestamp(indexParam, Timestamp.valueOf(parcel.getParcelPlacedTime()));
            indexParam++;
        }
        if (parcel.getParcelPickupTime() != null) {
            ps.setTimestamp(indexParam, Timestamp.valueOf(parcel.getParcelPickupTime()));
            indexParam++;
        }
        if (parcel.getParcelDropoffTime() != null) {
            ps.setTimestamp(indexParam, Timestamp.valueOf(parcel.getParcelDropoffTime()));
            indexParam++;
        }
        if (parcel.getServiceCost() != null) {
            ps.setDouble(indexParam, parcel.getServiceCost());
            indexParam++;
        }
        if (parcel.getPaymentStatus() != null && !parcel.getPaymentStatus().isEmpty()) {
            ps.setString(indexParam, parcel.getPaymentStatus());
            indexParam++;
        }
        if (parcel.getParcelPaymentTime() != null) {
            ps.setTimestamp(indexParam, Timestamp.valueOf(parcel.getParcelPaymentTime()));
            indexParam++;
        }
        if (parcel.getParcelStatus() != null && !parcel.getParcelStatus().isEmpty()) {
            ps.setString(indexParam, parcel.getParcelStatus());
            indexParam++;
        }

        System.out.println("Update SQL query : " + sql);
        int num = ps.executeUpdate();
        if (num > 0) {
            return readParcelByParcelId(bId);
        }

        return null;

    }

	public boolean deleteParcel(int bId) throws SQLException {
		String sql = "DELETE FROM " + tableName + " WHERE " + parcelId + " = ?";
		Connection connection = DBUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, bId);
		int num = ps.executeUpdate();

		return num > 0;
	}
}
