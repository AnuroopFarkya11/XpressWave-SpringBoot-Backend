package com.PMS.PMSServer.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.PMS.PMSServer.exception.user.UserNotFoundException;
import com.PMS.PMSServer.model.PMSUser;
import com.PMS.PMSServer.model.PMSUserRole;
import com.PMS.PMSServer.util.DBUtil;
import com.PMS.PMSServer.util.Utils;

@Repository
public class UserRepository {

	/// Will perfom crud operation with sql

	private final String tableName = "PMS_USERS";

	private final String delimeter = ", ";

	private final String id = "ID";
	private final String fullName = "FULL_NAME";
	private final String email = "EMAIL";
	private final String mobile = "MOBILE";
	private final String password = "PASSWORD";
	private final String address = "ADDRESS";
	private final String role = "ROLE";

	public boolean createTable() throws SQLException {
		String sql = "CREATE TABLE " + tableName + " (" + id + " INT PRIMARY KEY" + delimeter + fullName
				+ " VARCHAR(255) NOT NULL" + delimeter + email + " VARCHAR(255) NOT NULL UNIQUE" + delimeter + mobile
				+ " VARCHAR(255) NOT NULL UNIQUE" + delimeter + password + " VARCHAR(255) NOT NULL" + delimeter + role
				+ " VARCHAR(15)" + ")";

		Connection connection = DBUtil.getConnection();
		Statement statement = connection.createStatement();
		int n = statement.executeUpdate(sql);
		return n > 0;
	}

	public List<PMSUser> readAllUsers() throws SQLException {
		String sql = "SELECT * FROM " + tableName;
		Statement statement = DBUtil.getConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);
		List<PMSUser> users = new ArrayList<>();

		while (rs.next()) {
			int id = rs.getInt(1);
			String fullName = rs.getString(2);
			String email = rs.getString(3);
			String mobile = rs.getString(4);
			String password = rs.getString(5);
//			String address = rs.getString(6);
			PMSUserRole role = PMSUserRole.valueOf(rs.getString(6).toUpperCase());

			PMSUser user = new PMSUser(id, fullName, email, password, mobile, role);
			users.add(user);
		}
		return users;

	}

	public boolean createUser(PMSUser user) throws SQLException {
		Connection connection = DBUtil.getConnection();
		String sql = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?)";

		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setInt(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getEmail());
		ps.setString(4, user.getMobile());
		ps.setString(5, user.getPassword());
//		ps.setString(6, user.getAddress());
		ps.setString(6, user.getRole().toString().toUpperCase());

		int num = ps.executeUpdate();

		return num > 0;

	}

	public PMSUser readUserById(int uid) throws SQLException {

//		String sql = "SELECT * FROM PMS_USERS P INNER JOIN PMS_USERS_ADDRESS A ON P.ID = A.USER_ID WHERE P.ID = ?";
		String sql = "SELECT * FROM " + tableName + " WHERE " + id + " = ?";
		Connection connection = DBUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, uid);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int id = rs.getInt(1);
			String fullName = rs.getString(2);
			String email = rs.getString(3);
			String mobile = rs.getString(4);
			String password = rs.getString(5);
//			String address = rs.getString(6);
			PMSUserRole role = PMSUserRole.valueOf(rs.getString(6).toUpperCase());

			PMSUser user = new PMSUser(id, fullName, email, password, mobile, role);

			return user;

		}
		throw new UserNotFoundException(uid);
	}

	public PMSUser readUserByEmailNPassword(String uemail, String upassword) throws SQLException {

		String sql = "SELECT * FROM " + tableName + " WHERE " + email + " = ?" + " AND " + password + " = ?";

		Connection connection = DBUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, uemail);
		ps.setString(2, upassword);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			int id = rs.getInt(1);
			String fullName = rs.getString(2);
			String email = rs.getString(3);
			String mobile = rs.getString(4);
			String password = rs.getString(5);
			PMSUserRole role = rs.getString(6).toUpperCase() == PMSUserRole.ADMIN.toString() ? PMSUserRole.ADMIN
					: PMSUserRole.CUSTOMER;

			PMSUser user = new PMSUser(id, fullName, email, password, mobile, role);

			return user;

		}
		return null;
	}

	public PMSUser updateUser(int uid, PMSUser user) throws UserNotFoundException, SQLException {

		if (readUserById(uid) != null) {

			StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");

			String mark = " = ?";
			/// todo add check for id
			if (user.getName() != null && !user.getName().isEmpty()) {
//			sql.append(fullName + " = " + delimeter + user.getName() + delimeter + ", ");
				sql.append(fullName + mark + delimeter);
			}
			if (user.getEmail() != null && !user.getEmail().isEmpty()) {
//			sql.append(email + " = " + delimeter + user.getEmail() + delimeter + ", ");
				sql.append(email + mark + delimeter);
			}
			if (user.getPassword() != null && !user.getPassword().isEmpty()) {
//			sql.append(password + " = " + delimeter + user.getPassword() + delimeter + ", ");
				sql.append(password + mark + delimeter);
			}
			if (user.getMobile() != null && !user.getMobile().isEmpty()) {
//			sql.append(mobile + " = " + delimeter + user.getMobile() + delimeter + ", ");
				sql.append(mobile + mark + delimeter);
			}
//			if (user.getAddress() != null && !user.getAddress().isEmpty()) {
////			sql.append(address + " = " + delimeter + user.getAddress() + delimeter + ", ");
//				sql.append(address + mark + delimeter);
//			}
			if (user.getRole() != null) {
//			sql.append(role + " = " + delimeter + user.getRole().toString() + delimeter + ", ");
				sql.append(role + mark + delimeter);
			}

			sql.setLength(sql.length() - 2);

			sql.append(" WHERE " + id + " = " + uid);
			PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql.toString());

			int indexParam = 1;

			if (user.getName() != null && !user.getName().isEmpty()) {
				ps.setString(indexParam, user.getName());
				indexParam++;
			}

			if (user.getEmail() != null && !user.getEmail().isEmpty()) {
				ps.setString(indexParam, user.getEmail());
				indexParam++;
			}
			if (user.getPassword() != null && !user.getPassword().isEmpty()) {
				ps.setString(indexParam, user.getPassword());
				indexParam++;
			}
			if (user.getMobile() != null && !user.getMobile().isEmpty()) {
				ps.setString(indexParam, user.getMobile());
				indexParam++;
			}
//			if (user.getAddress() != null && !user.getAddress().isEmpty()) {
//				ps.setString(indexParam, user.getAddress());
//				indexParam++;
//			}
			if (user.getRole() != null) {
				ps.setString(indexParam, user.getRole().toString().toUpperCase());
				indexParam++;
			}

			System.out.println("Update SQL query : " + sql);
			int num = ps.executeUpdate();
			if (num > 0) {
				return readUserById(uid);
			}
		} else {
			throw new UserNotFoundException(uid);
		}
		return null;

	}

	public PMSUser updatePasswordByEmailAddress(String givenEmail, String newpassword)
			throws UserNotFoundException, SQLException {

		String sql = "UPDATE " + tableName + " SET " + password + " = ? WHERE " + email + " = ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);

		ps.setString(1, newpassword);
		ps.setString(2, givenEmail);

		System.out.println("Update SQL query : " + sql);
		int num = ps.executeUpdate();
		if (num > 0) {
			return readUserByEmail(givenEmail);
		}

		else {
			throw new UserNotFoundException(givenEmail);
		}

	}

	public PMSUser readUserByEmail(String uemail) throws SQLException {

		String sql = "SELECT * FROM " + tableName + " WHERE " + email + " = ?";

		Connection connection = DBUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, uemail);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			int id = rs.getInt(1);
			String fullName = rs.getString(2);
			String email = rs.getString(3);
			String mobile = rs.getString(4);
			String password = rs.getString(5);
			PMSUserRole role = rs.getString(6).toUpperCase() == PMSUserRole.ADMIN.toString() ? PMSUserRole.ADMIN
					: PMSUserRole.CUSTOMER;

			PMSUser user = new PMSUser(id, fullName, email, password, mobile, role);

			return user;

		}
		return null;
	}

}
