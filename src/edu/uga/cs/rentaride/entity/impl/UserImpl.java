package edu.uga.cs.rentaride.entity.impl;



import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.RentARideParams;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.persistence.impl.Persistent;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;


public class UserImpl extends Persistent implements User {

	
		private long id;
		private String firstName;
		private String lastName;
		private String userName;
		private String email;
		private String password;
		private Date createdDate;
		private String address;
		private UserStatus userStatus;
	
	public UserImpl() {
		
		super(-1);
		this.firstName = null;
		this.lastName = null;
		this.userName = null;
		this.email = null;
		this.password = null;
		this.createdDate = null;
		this.address = null;
		this.userStatus = null;
	}
	
	public UserImpl(String firstName, String lastName, String userName, String email, String password, Date createdDate, String address, UserStatus userStatus) {
		super(-1);
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.createdDate = createdDate;
		this.address = address;
		this.userStatus = userStatus;
	}
	
	@Override
	public String getFirstName() {

		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		
		this.firstName = firstName;
		
	}

	@Override
	public String getLastName() {

		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		
		this.lastName = lastName;
	}

	@Override
	public String getUserName() {

		return userName;
	}

	@Override
	public void setUserName(String userName) throws RARException {
		
		this.userName = userName;
	}

	@Override
	public String getEmail() {

		return email;
	}

	@Override
	public void setEmail(String email) {

		this.email = email;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public void setPassword(String password) {

		this.password = password;
	}

	@Override
	public Date getCreatedDate() {

		return createdDate;
	}

	@Override
	public void setCreateDate(Date createdDate) {
		
		this.createdDate = createdDate;
	}

	@Override
	public String getAddress() {

		return address;
	}

	@Override
	public void setAddress(String address) {

		this.address = address;
	}

	@Override
	public UserStatus getUserStatus() {

		return userStatus;
	}

	@Override
	public void setUserStatus(UserStatus userStatus) {

		this.userStatus = userStatus;
	}
	
}