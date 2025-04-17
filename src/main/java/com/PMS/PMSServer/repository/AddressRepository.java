package com.PMS.PMSServer.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.PMS.PMSServer.model.Address;
import com.PMS.PMSServer.model.AddressType;
import com.PMS.PMSServer.util.DBUtil;

@Repository
public class AddressRepository {
	private final String tableName = "PMS_USERS_ADDRESS";

	private final String delimeter = ", ";

	private final String addressId = "ADDRESS_ID";
	private final String userID = "USER_ID";
	private final String street = "STREET";
	private final String city = "CITY";
	private final String state = "STATE";
	private final String zipCode = "ZIPCODE";
	private final String country = "COUNTRY";
	private final String addressType = "ADDRESS_TYPE";

	public boolean createTable() throws SQLException {
		String sql = "CREATE TABLE " + tableName + " (" + addressId + " INT PRIMARY KEY" + delimeter + userID + " INT"
				+ delimeter + street + " VARCHAR(255) NOT NULL" + delimeter + city + " VARCHAR(255) NOT NULL"
				+ delimeter + state + " VARCHAR(255) NOT NULL" + delimeter + zipCode + " VARCHAR(20) NOT NULL"
				+ delimeter + country + " VARCHAR(255) NOT NULL" + delimeter + addressType + " VARCHAR(15)" + delimeter
				+ "FOREIGN KEY (" + userID + ") REFERENCES PMS_USERS(id) ON DELETE CASCADE" + ")";

		
		System.out.println("CREATE Address TABLE QUERY :" +sql);
		
		Connection connection = DBUtil.getConnection();
		Statement statement = connection.createStatement();
		int n = statement.executeUpdate(sql);
		return n > 0;
	}

	public List<Address> readAllAddresses() throws SQLException {
		String sql = "SELECT * FROM " + tableName;
		Statement statement = DBUtil.getConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);
		List<Address> addresses = new ArrayList<>();

		while (rs.next()) {

			Address address = new Address(rs.getInt(addressId), rs.getString(street), rs.getString(city),
					rs.getString(state), rs.getString(zipCode), rs.getString(country),
					AddressType.valueOf(rs.getString(addressType)));

			addresses.add(address);
		}
		return addresses;

	}

	public boolean createAddress(int uid, Address address) throws SQLException {
		Connection connection = DBUtil.getConnection();
		String sql = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?,?)";

		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setInt(1, address.getAddressId());
		ps.setInt(2, uid);
		ps.setString(3, address.getStreet());
		ps.setString(4, address.getCity());
		ps.setString(5, address.getState());
		ps.setString(6, address.getZipCode());
		ps.setString(7, address.getCountry());
		ps.setString(8, address.getAddressType().toString().toUpperCase());

		int num = ps.executeUpdate();

		return num > 0;

	}

	public Address readAddressByAId(int aid) throws SQLException {
		String sql = "SELECT * FROM " + tableName + " WHERE " + addressId + " = ?";
		Connection connection = DBUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, aid);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Address address = new Address(rs.getInt(addressId), rs.getString(street), rs.getString(city),
					rs.getString(state), rs.getString(zipCode), rs.getString(country),
					AddressType.valueOf(rs.getString(addressType)));

			return address;

		}
		return null;
	}
//	public List<Address> readAllAddressByUId(int uid) throws SQLException {
//		String sql = "SELECT * FROM " + tableName + " WHERE " + userID + " = ?";
//		Connection connection = DBUtil.getConnection();
//		PreparedStatement ps = connection.prepareStatement(sql);
//		ps.setInt(1, uid);
//		ResultSet rs = ps.executeQuery();
//		
//		List<Address> addresses = new ArrayList<>();
//
//		while (rs.next()) {
//			Address address = new Address(rs.getInt(addressId), rs.getString(street), rs.getString(city),
//					rs.getString(state), rs.getString(zipCode), rs.getString(country),
//					AddressType.valueOf(rs.getString(addressType)));
//
//			addresses.add(address);
//			
//			
//
//		}
//		return addresses;
//	}

	public List<Address> readAllAddressesByUId(int uid) throws SQLException {
        String sql = "SELECT * FROM " + tableName + " WHERE " + userID + " = ?";
        Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, uid);
        ResultSet rs = ps.executeQuery();
        List<Address> addresses=new ArrayList<>();
        while (rs.next()) {
            Address address = new Address(rs.getInt(addressId), rs.getString(street), rs.getString(city),
                    rs.getString(state), rs.getString(zipCode), rs.getString(country),
                    AddressType.valueOf(rs.getString(addressType)));
            addresses.add(address);

            }
        if(addresses.isEmpty()) {
            return null;
        }
        return addresses;
        
    }
	public Address updateAddress(int aid, Address address) throws SQLException {

		StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");

		String mark = " = ?";
		/// todo add check for id
		if (address.getStreet() != null && !address.getStreet().isEmpty()) {
//			sql.append(fullName + " = " + delimeter + address.getName() + delimeter + ", ");
			sql.append(street + mark + delimeter);
		}
		if (address.getCity() != null && !address.getCity().isEmpty()) {
//			sql.append(email + " = " + delimeter + address.getEmail() + delimeter + ", ");
			sql.append(city + mark + delimeter);
		}
		if (address.getState() != null && !address.getState().isEmpty()) {
//			sql.append(password + " = " + delimeter + address.getPassword() + delimeter + ", ");
			sql.append(state + mark + delimeter);
		}
		if (address.getZipCode() != null && !address.getZipCode().isEmpty()) {
//			sql.append(mobile + " = " + delimeter + address.getMobile() + delimeter + ", ");
			sql.append(zipCode + mark + delimeter);
		}
		if (address.getCountry() != null && !address.getCountry().isEmpty()) {
//			sql.append(address + " = " + delimeter + address.getAddress() + delimeter + ", ");
			sql.append(country + mark + delimeter);
		}
		if (address.getAddressType() != null) {
//			sql.append(role + " = " + delimeter + address.getRole().toString() + delimeter + ", ");
			sql.append(addressType + mark + delimeter);
		}

		sql.setLength(sql.length() - 2);

		sql.append(" WHERE " + addressId + " = " + aid);
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql.toString());

		int indexParam = 1;

		if (address.getStreet() != null && !address.getStreet().isEmpty()) {
			ps.setString(indexParam, address.getStreet());
			indexParam++;
		}

		if (address.getCity() != null && !address.getCity().isEmpty()) {
			ps.setString(indexParam, address.getCity());
			indexParam++;
		}
		if (address.getState() != null && !address.getState().isEmpty()) {
			ps.setString(indexParam, address.getState());
			indexParam++;
		}
		if (address.getZipCode() != null && !address.getZipCode().isEmpty()) {
			ps.setString(indexParam, address.getZipCode());
			indexParam++;
		}
		if (address.getCountry() != null && !address.getCountry().isEmpty()) {
			ps.setString(indexParam, address.getCountry());
			indexParam++;
		}
		if (address.getAddressType() != null) {
			ps.setString(indexParam, address.getAddressType().toString().toUpperCase());
			indexParam++;
		}

		System.out.println("Update SQL query : " + sql);
		int num = ps.executeUpdate();
		if (num > 0) {
			return readAddressByAId(aid);
		}

		return null;

	}

}
