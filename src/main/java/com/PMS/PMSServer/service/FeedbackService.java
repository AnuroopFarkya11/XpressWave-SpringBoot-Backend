package com.PMS.PMSServer.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PMS.PMSServer.exception.user.UserException;
import com.PMS.PMSServer.exception.user.UserNotFoundException;
import com.PMS.PMSServer.model.Feedback;
import com.PMS.PMSServer.model.FeedbackAckStatus;
import com.PMS.PMSServer.repository.FeedbackRepository;
import com.PMS.PMSServer.util.Utils;
import com.PMS.PMSServer.util.Validators;

@Service
public class FeedbackService {
	
	@Autowired
	private FeedbackRepository repository;
	
	public FeedbackService() {
		repository=new FeedbackRepository();
	}
	
	
	public boolean createTable() throws SQLException {
		try {

			boolean res = repository.createTable();

			System.out.println("Created FeedBack Table Successfully!");

			return res;

		} catch (SQLException e) {

			if (!e.getLocalizedMessage().toLowerCase().contains("already exist")) {
				e.printStackTrace();
				throw e;
			}

		}
		System.out.println("Connected FeedBack Table Successfully!");

		return true;

	}
	
	
	public Feedback createFeedBack(int userId,int parcelId,Feedback feedback) throws Exception{
		try {
			Validators.validateFeedBack("FeedBack", feedback.getFeedBack());
            Validators.validateFeedBackRating("Rating", feedback.getRating());
			int id = Utils.generateRandomId();
			feedback.setCreatedAt(LocalDateTime.now());
			feedback.setFeedbackId(id);
			feedback.setAckStatus(FeedbackAckStatus.BLANK);
			boolean res=repository.createFeedBack(feedback,userId,parcelId);
			if(res) {
				return feedback;
			}
		} catch (SQLException e) {
	
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return null;
	}
	
	
	
	public List<Feedback> getFeedbackByUserId(int id) throws Exception{
		if (id <= 0) {
			throw new UserException("Invalid User ID");
		}
		try {
			List<Feedback> feedback = repository.getFeedbackByUserId(id);
			if (feedback != null) {
				return feedback;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		throw new UserNotFoundException(id);
	}
		
	
	public List<Feedback> readAllFeedbacks() {
		try {
			return repository.readAllFeedback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateFeedbackAckStatus(int fid,String status) throws SQLException
	{
		/// Add a validator 
		return repository.updateAckStatus(fid, FeedbackAckStatus.valueOf(status));
	}
	
	
}
