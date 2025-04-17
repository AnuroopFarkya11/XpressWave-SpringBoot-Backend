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

import org.springframework.stereotype.Repository;

import com.PMS.PMSServer.model.Feedback;
import com.PMS.PMSServer.model.FeedbackAckStatus;
import com.PMS.PMSServer.util.DBUtil;
import com.PMS.PMSServer.util.Utils;

@Repository
public class FeedbackRepository {
	private final String tableName = "PMS_Parcel_FeedBack";
	private final String delimeter = ", ";
	private final String feedbackId = "FEEDBACK_ID";// 1
	private final String userId = "USER_ID";// 2
	private final String parcelId = "PARCEL_ID";// 3
	private final String feedback = "FEEDBACK";// 4
	private final String userName = "USER_NAME";// 5
	private final String rating = "RATING";// 6
	private final String createdAt = "CREATED_AT";// 7
	private final String ackStatus = "ACK_STATUS";// 8

	public boolean createTable() throws SQLException {
		String sql = "CREATE TABLE " + tableName + "(" + feedbackId + " INT PRIMARY KEY" + delimeter + userId
				+ " INT NOT NULL" + delimeter + parcelId + " INT NOT NULL" + delimeter + feedback
				+ " VARCHAR(255) NOT NULL" + delimeter + userName + " VARCHAR(255) NOT NULL" + delimeter + rating
				+ " double NOT NULL " + delimeter + createdAt + " Timestamp NOT NULL " + delimeter + ackStatus
				+ " VARCHAR(10)" + delimeter + "FOREIGN KEY (" + userId
				+ ") REFERENCES PMS_USERS(ID) ON DELETE CASCADE " + delimeter + "FOREIGN KEY (" + parcelId
				+ ")REFERENCES PMS_PARCEL(PARCEL_ID) ON DELETE CASCADE" + ")";

		Connection connection = DBUtil.getConnection();
		Statement statement = connection.createStatement();
		int n = statement.executeUpdate(sql);
		return n > 0;
	}

	public List<Feedback> readAllFeedback() throws SQLException {
		String sql = "SELECT * FROM " + tableName;
		Statement statement = DBUtil.getConnection().createStatement();
		ResultSet rs = statement.executeQuery(sql);
		List<Feedback> feedbacks = new ArrayList<>();

		while (rs.next()) {

			Feedback feedbackk = new Feedback(rs.getInt(feedbackId), rs.getString(userName), rs.getString(feedback),
					rs.getDouble(rating), FeedbackAckStatus.valueOf(rs.getString(ackStatus)),
					rs.getTimestamp(createdAt).toLocalDateTime());

			feedbacks.add(feedbackk);

		}

		return feedbacks;
	}

	public boolean createFeedBack(Feedback feedback, int uId, int pId) throws Exception {

		if (getFeedbackByParcelId(pId) != null) {
			throw new IllegalArgumentException("Feedback already registered");
		}

		Connection connection = DBUtil.getConnection();
		String sql = "INSERT INTO " + tableName + " VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setInt(1, feedback.getFeedbackId());
		ps.setInt(2, uId);
		ps.setInt(3, pId);
		ps.setString(4, feedback.getFeedBack());
		ps.setString(5, feedback.getUserName());
		ps.setDouble(6, feedback.getRating());
		ps.setTimestamp(7, Timestamp.valueOf(feedback.getCreatedAt()));
		ps.setString(8, feedback.getAckStatus().toString().toUpperCase());
	

		int num = ps.executeUpdate();

		return num > 0;

	}

	public Feedback getFeedbackByParcelId(int pID) throws Exception {
		String sql = "SELECT * FROM " + tableName + " WHERE " + parcelId + " = ?";
		Connection connection = DBUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, pID);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Feedback feedbackk = new Feedback(rs.getInt(feedbackId), rs.getString(userName), rs.getString(feedback),
					rs.getDouble(rating), FeedbackAckStatus.valueOf(rs.getString(ackStatus)),
					rs.getTimestamp(createdAt).toLocalDateTime());

			return feedbackk;

		}
		return null;
	}

	public List<Feedback> getFeedbackByUserId(int uid) throws Exception {
		String sql = "SELECT * FROM " + tableName + " WHERE " + userId + " = ?";
		Connection connection = DBUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, uid);
		ResultSet rs = ps.executeQuery();
		List<Feedback> feedbacks = new ArrayList<>();

		while (rs.next()) {
			Feedback feedbackk = new Feedback(rs.getInt(feedbackId), rs.getString(userName), rs.getString(feedback),
					rs.getDouble(rating), FeedbackAckStatus.valueOf(rs.getString(ackStatus)),
					rs.getTimestamp(createdAt).toLocalDateTime());

			feedbacks.add(feedbackk);

		}
		return feedbacks;
	}

	public boolean updateAckStatus(int fid, FeedbackAckStatus status) throws SQLException {
		String sql = "UPDATE " + tableName + " SET " + ackStatus + "=?" + " WHERE " + feedbackId + "=?";

		Connection connection = DBUtil.getConnection();

		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setString(1, status.toString());
		ps.setInt(2, fid);

		int rs = ps.executeUpdate();

		return rs>0;
	}

}
