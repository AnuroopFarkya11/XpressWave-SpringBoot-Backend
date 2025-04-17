package com.PMS.PMSServer.service;

import java.sql.SQLException;

import java.util.List;

import org.springframework.stereotype.Component;

import com.PMS.PMSServer.exception.address.AddressException;
import com.PMS.PMSServer.exception.address.AddressNotFoundException;
import com.PMS.PMSServer.model.Address;
import com.PMS.PMSServer.repository.AddressRepository;
import com.PMS.PMSServer.util.Utils;
import com.PMS.PMSServer.util.Validators;

@Component
public class AddressService {

	private AddressRepository repository;
	
	public AddressService() {
		repository = new AddressRepository();
	}
	
	public boolean createTable() throws SQLException {
		try {

			boolean res = repository.createTable();

			System.out.println("Created Address Table Successfully!");

			return res;

		} catch (SQLException e) {

			if (!e.getLocalizedMessage().toLowerCase().contains("already exist")) {
				e.printStackTrace();
				throw e;
			}

		}
		System.out.println("Connected Address Table Successfully!");

		return true;

	}
	public Address addAddress(int uid,Address address) throws Exception {
		try {
			/// add validation also add code for random user id
			/// add validation also add code for random user id
			
			Validators.validateId("User Id", uid);
			//Validators.validateId("Address Id", addressId);
			Validators.validateName("Address Street", address.getStreet());
			Validators.validateName("Address City", address.getCity());
			Validators.validateName("Address State", address.getState());
			Validators.validateParcelPin("Address Zip Code", address.getZipCode());
			Validators.validateName("Address Country", address.getCountry());
			Validators.validateAddressType("Address Type", address.getAddressType().toString());
			address.setAddressId(Utils.generateRandomId());
			boolean res = repository.createAddress(uid,address);
			if (res) {
				return address;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return null;
	}
	

	public Address updateAddress(Integer aid, Address address) {
		try {
			Integer addressId=address.getAddressId();
//			if(id!=null || id<0) {
//				Validators.validateId("UserId",id);
//			}
			if(aid!=null) {
				Validators.validateId("Address ID", aid);
			}
			if(address.getStreet()!=null) {
				Validators.validateName("Address Street", address.getStreet());
			}
			if(address.getCity()!=null) {
				Validators.validateName("Address City", address.getCity());
			}
			if(address.getState()!=null) {
				Validators.validateName("Address State", address.getState());
			}
			if(address.getCountry()!=null) {
				Validators.validateName("Address Country", address.getCountry());
			}
			if(address.getZipCode()!=null) {
				Validators.validateParcelPin("Address Zip Code", address.getZipCode());
			}
			if(address.getAddressType()!=null) {
				Validators.validateAddressType("Address Type", address.getAddressType().toString());
			}
			return repository.updateAddress(aid, address);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Address getAddressByAid(int aid) throws Exception{
		
		if (aid <= 0) {
			throw new AddressException("Invalid Address  ID");
		}
		try {
			Address address=repository.readAddressByAId(aid);
			if(address!=null) {
				return address;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		throw new AddressNotFoundException(aid);
		
	}
		public List<Address> getAllAddressesByUserId(int uid) throws Exception{
        
        if (uid <= 0) {
            throw new AddressException("Invalid User ID");
        }
        try {
            List<Address> addresses=repository.readAllAddressesByUId(uid);
            if(addresses!=null) {
                return addresses;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        throw new AddressNotFoundException(uid);
        
    }
	public List<Address> getAllAddresses() {
		try {
			return repository.readAllAddresses();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
