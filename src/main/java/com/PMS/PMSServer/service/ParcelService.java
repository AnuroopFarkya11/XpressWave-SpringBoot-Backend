
package com.PMS.PMSServer.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.PMS.PMSServer.exception.parcel.ParcelException;
import com.PMS.PMSServer.exception.parcel.ParcelNotFoundException;
import com.PMS.PMSServer.exception.user.UserException;
import com.PMS.PMSServer.model.PMSParcel;
import com.PMS.PMSServer.repository.ParcelRepository;
import com.PMS.PMSServer.util.Validators;

@Component
public class ParcelService {

	public ParcelRepository repository;

	public UserService userService;

	public ParcelService() {
		repository = new ParcelRepository();
		userService = new UserService();
	}

	public boolean createTable() throws SQLException {
		try {

			boolean res = repository.createTable();
			System.out.println("Parcel table Created Sucessfully. !");

		} catch (SQLException e) {

			if (!e.getLocalizedMessage().toLowerCase().contains("already exist")) {
				e.printStackTrace();
				throw e;
			}
		}
		System.out.println("Connected Parcel Table Sucessfully!..");
		return true;
	}

	public PMSParcel addParcel(int uid, PMSParcel parcel) throws Exception {

		try {
			if (userService.userExistById(uid)) {

				Validators.validateAddress("Sender Address", parcel.getSenderAddress());
				Validators.validateAddress("Receiver Address", parcel.getReceiverAddress());
				Validators.validateName("Sender name", parcel.getSenderName());
				Validators.validateParcelPin("Sender Pin", parcel.getSenderPin());
				Validators.validateParcelPin("Receiver Pin", parcel.getReceiverPin());
				Validators.validateName("Receiver name", parcel.getReceiverName());
				Validators.validateParcelStatus("Parcel status", parcel.getParcelStatus());
				Validators.validateDeliveryType("Delivery Type", parcel.getParcelDeliveryType());
				Validators.validatePaymentStatus("Payment Status", parcel.getPaymentStatus());
				Validators.validatePhoneNumber("Sender Phone Number", parcel.getSenderMobile());
				Validators.validatePhoneNumber("Receiver Phone Number", parcel.getReceiverMobile());
				Validators.validateTrackingId("Tracking Id", parcel.getParcel_Id());
				Validators.validateWeight("Parcel Weight", parcel.getParcelWeightInGram());
				Validators.validatePackingPreference("Packing Preferences", parcel.getParcelPackingPreference());
				Validators.validateDeliveryType("Delivery Type", parcel.getParcelDeliveryType());
				Validators.validateServiceCost("Service Cost", parcel.getServiceCost());
				Validators.validateWeight("Parcel Length", parcel.getParcelLength());
	            Validators.validateWeight("Parcel Breadth", parcel.getParcelBreadth());
	            Validators.validateWeight("Parcel Size", parcel.getParcelSize());
				Validators.validateParcelContentsDescription("Contents Description ",
						parcel.getParcelContentsDescription());
				int id = ParcelRepository.generateRandomBookingId();
				parcel.setParcel_Id(id);
				parcel.setParcelPlacedTime(LocalDateTime.now());
				boolean res = repository.createParcel(uid, parcel);
				if (res) {
					return parcel;
				}
			}
		} catch (UserException e) {
			e.printStackTrace();
			throw new UserException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return null;

	}

	public PMSParcel getParcelByParcelID(int id) throws Exception {

		if (id <= 0) {
			throw new ParcelException("Invalid Parcel ID");
		}

		try {
			Validators.validateTrackingId("Parcel Id", id);
			PMSParcel parcel = repository.readParcelByParcelId(id);
			if (parcel != null) {
				return parcel;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		throw new ParcelNotFoundException(id);
	}

	public List<PMSParcel> getAllParcels() throws Exception {
		try {
			return repository.readAllParcel();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public List<PMSParcel> getAllParcelByUserID(int uid) throws Exception {
		try {
			Validators.validateId("User Id", uid);
			return repository.readParcelsByUserId(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public PMSParcel updateParcel(Integer pid, PMSParcel parcel) throws Exception {
		try {
			if (pid != null || pid < 0) {
				Validators.validateTrackingId("Parcel Id", parcel.getParcel_Id());
			}
			if (parcel.getSenderName() != null) {
				Validators.validateName("Sender name", parcel.getSenderName());
			}
			if (parcel.getSenderAddress() != null) {
				Validators.validateAddress("Sender Address", parcel.getSenderAddress());
			}
			if (parcel.getSenderPin() != null) {
				Validators.validateParcelPin("Sender Pin", parcel.getSenderPin());
			}
			if (parcel.getSenderMobile() != null) {
				Validators.validatePhoneNumber("Sender Phone Number", parcel.getSenderMobile());
			}
			if (parcel.getReceiverName() != null) {
				Validators.validateName("Receiver name", parcel.getReceiverName());
			}
			if (parcel.getReceiverAddress() != null) {
				Validators.validateAddress("Receiver Address", parcel.getReceiverAddress());
			}
			if (parcel.getReceiverPin() != null) {
				Validators.validateParcelPin("Receiver Pin", parcel.getReceiverPin());
			}
			if (parcel.getReceiverMobile() != null) {
				Validators.validatePhoneNumber("Receiver Phone Number", parcel.getReceiverMobile());
			}
			if (parcel.getParcelWeightInGram() != null) {
				Validators.validateWeight("Parcel Weight", parcel.getParcelWeightInGram());
			}
			if (parcel.getParcelPackingPreference() != null) {
				Validators.validatePackingPreference("Packing Preferences", parcel.getParcelPackingPreference());
			}
			if (parcel.getParcelDeliveryType() != null) {
				Validators.validateDeliveryType("Delivery Type", parcel.getParcelDeliveryType());
			}
			if (parcel.getServiceCost() != null) {
				Validators.validateServiceCost("Service Cost", parcel.getServiceCost());
			}
			if (parcel.getPaymentStatus() != null) {
				Validators.validatePaymentStatus("Payment Status", parcel.getPaymentStatus());
			}
			if (parcel.getParcelStatus() != null) {
				Validators.validateParcelStatus("Parcel status", parcel.getParcelStatus());
			}
			if (parcel.getParcelContentsDescription() != null) {
				Validators.validateParcelContentsDescription("Contents Description ",
						parcel.getParcelContentsDescription());
			}
			if(parcel.getParcelLength()!=null) {
                Validators.validateWeight("Parcel Length", parcel.getParcelLength());
            }
            if(parcel.getParcelBreadth()!=null) {
                Validators.validateWeight("Parcel Breadth", parcel.getParcelBreadth());
            }
            if(parcel.getParcelSize() !=null) {
                Validators.validateWeight("Parcel Size", parcel.getParcelSize());
            }

			return repository.updateParcel(pid, parcel);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}

	}

	public boolean deleteParcel(int pid) throws Exception {
		// todo add checks wheather the id is present or not
		if (pid <= 0) {
			throw new ParcelException("Invalid Parcel ID");
		}
		try {
			boolean res = repository.deleteParcel(pid);
			if (res) {
				System.out.println("Parcel Deleted Sucessfully ..!");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return false;

	}

}
