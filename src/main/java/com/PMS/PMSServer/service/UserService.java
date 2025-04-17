package com.PMS.PMSServer.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.PMS.PMSServer.exception.user.UserException;
import com.PMS.PMSServer.exception.user.UserNotFoundException;
import com.PMS.PMSServer.model.Address;
import com.PMS.PMSServer.model.PMSUser;
import com.PMS.PMSServer.repository.AddressRepository;
import com.PMS.PMSServer.repository.UserRepository;
import com.PMS.PMSServer.util.Utils;
import com.PMS.PMSServer.util.Validators;;

@Service
public class UserService {

	//// Apply all the business logic before navigating to dao's

//	@Autowired
	private UserRepository repository;
	private AddressRepository addressRepository;
	
//	@Autowired 
	private BCryptPasswordEncoder passwordEncoder;

	public UserService() {
		repository = new UserRepository();
		addressRepository = new AddressRepository();
		passwordEncoder = new BCryptPasswordEncoder();
	}

	public boolean createTable() throws SQLException {
		try {

			boolean res = repository.createTable();

			System.out.println("Created User Table Successfully!");

			return res;

		} catch (SQLException e) {

			if (!e.getLocalizedMessage().toLowerCase().contains("already exist")) {
				e.printStackTrace();
				throw e;
			}

		}
		System.out.println("Connected User Table Successfully!");

		return true;

	}

	public PMSUser addUser(PMSUser user) throws Exception {
		try {

			Validators.validateName("fullName", user.getName());
			Validators.validateEmail("Email", user.getEmail());
			Validators.validatePassword("Password", user.getPassword());
			Validators.validatePhoneNumber("Mobile Phone Number", user.getMobile());
			Validators.validateUserRole("User Role", user.getRole().toString());
			user.setId(Utils.generateRandomId());
			
			String encodedPaaword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPaaword);
			boolean res = repository.createUser(user);
			if (res) {
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;

		}
		return null;
	}

	public PMSUser updateUser(Integer id, PMSUser user) {
		try {
			if (id != null) {
				Validators.validateId("User ID", id);
			}
			if (user.getName() != null) {
				Validators.validateName("Name", user.getName());
			}
			if (user.getEmail() != null) {
				Validators.validateEmail("email", user.getEmail());
			}
//			if(user.getAddress()!=null)
//			{
//				 Validators.validateAddress("Address",user.getAddress());
//			}
			if (user.getPassword() != null) {
				Validators.validatePassword("Password", user.getPassword());
			}
			if (user.getMobile() != null) {
				Validators.validatePhoneNumber("Mobile Phone Number", user.getMobile());
			}
			if (user.getRole() != null) {
				Validators.validateUserRole("User Role", user.getRole().toString());
			}
			return repository.updateUser(id, user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public PMSUser getUser(int id) throws UserException {
		try {
			PMSUser user = repository.readUserById(id);

			if (user != null) {
				List<Address> addresses = addressRepository.readAllAddressesByUId(id);
				user.setAddresses(addresses);
				return user;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException(e.toString());
		}
		throw new UserNotFoundException(id);
	}

	public Boolean userExistById(int uid) throws Exception {
		try {
			PMSUser user = repository.readUserById(uid);

			if (user != null) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		throw new UserNotFoundException(uid);
	}

	public PMSUser getUserByEmailNPassword(String email,String password) throws Exception {
		Validators.validateEmail("Email", email);

		try {
			PMSUser user = repository.readUserByEmail(email);

			if (user != null) {
				if(passwordEncoder.matches(password, user.getPassword()))
				{
				List<Address> addresses = addressRepository.readAllAddressesByUId(user.getId());
				user.setAddresses(addresses);
				return user;
				}
				else
				{
					throw new UserException("Invalid Password.");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		throw new UserNotFoundException(email);
	}

	public List<PMSUser> getAllUsers() throws Exception {
		try {
			List<PMSUser> users = repository.readAllUsers();

			for (PMSUser user : users) {
				if (user != null) {
					List<Address> addresses = addressRepository.readAllAddressesByUId(user.getId());
					user.setAddresses(addresses);

				}
			}

			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

	}

	public PMSUser updatePasswordBYEmail(String email, String password) {
		try {
			Validators.validateEmail("email", email);
			Validators.validatePassword("Password", password);

			return repository.updatePasswordByEmailAddress(email, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
