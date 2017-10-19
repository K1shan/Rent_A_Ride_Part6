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


public class AdministratorImpl 
	extends Persistent
	implements Administrator 
{

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFirstName(String firstName) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLastName(String lastName) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserName(String userName) throws RARException {
		// TODO Auto-generated method stub
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
	}

	@Override
	public Date getCreatedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreateDate(Date createDate) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAddress(String address) {
		// TODO Auto-generated method stub
	}

	@Override
	public UserStatus getUserStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserStatus(UserStatus userStatus) {
		// TODO Auto-generated method stub
	}

	@Override
	public int getUserId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setUserId(int userId) {
		// TODO Auto-generated method stub	
	}
}