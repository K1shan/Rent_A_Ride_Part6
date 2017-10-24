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

<<<<<<< HEAD
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String password;
	private Date createdDate;
	private String address;
	private UserStatus userStatus;
	
	public UserImpl () {
		super ( -1 );
		this.firstName = null;
		this.lastName = null;
		this.userName = null;
		this.email =  null;
		this.password =  null;
		this.createdDate = null;
		this.address =  null;
		this.userStatus = null;
	}
	
	public UserImpl (String firstName, String lastName, String userName, String password, String email,
			String address, Date createdDate, UserStatus userStatus) {
		super( -1 );
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email =  email;
		this.password =  password;
		this.address =  address;
=======
	
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
>>>>>>> master
		this.userStatus = userStatus;
	}
	
	@Override
	public String getFirstName() {
<<<<<<< HEAD
		return this.firstName;
=======

		return firstName;
>>>>>>> master
	}

	@Override
	public void setFirstName(String firstName) {
<<<<<<< HEAD
		this.firstName = firstName;
=======
		
		this.firstName = firstName;
		
>>>>>>> master
	}

	@Override
	public String getLastName() {
<<<<<<< HEAD
		return this.lastName;
=======

		return lastName;
>>>>>>> master
	}

	@Override
	public void setLastName(String lastName) {
<<<<<<< HEAD
=======
		
>>>>>>> master
		this.lastName = lastName;
	}

	@Override
	public String getUserName() {
<<<<<<< HEAD
		return this.userName;
=======

		return userName;
>>>>>>> master
	}

	@Override
	public void setUserName(String userName) throws RARException {
<<<<<<< HEAD
=======
		
>>>>>>> master
		this.userName = userName;
	}

	@Override
	public String getEmail() {
<<<<<<< HEAD
		return this.email;
=======

		return email;
>>>>>>> master
	}

	@Override
	public void setEmail(String email) {
<<<<<<< HEAD
=======

>>>>>>> master
		this.email = email;
	}

	@Override
	public String getPassword() {
<<<<<<< HEAD
		return this.password;
=======

		return password;
>>>>>>> master
	}

	@Override
	public void setPassword(String password) {
<<<<<<< HEAD
=======

>>>>>>> master
		this.password = password;
	}

	@Override
	public Date getCreatedDate() {
<<<<<<< HEAD
		return this.createdDate;
=======

		return createdDate;
>>>>>>> master
	}

	@Override
	public void setCreateDate(Date createDate) {
<<<<<<< HEAD
=======
		
>>>>>>> master
		this.createdDate = createdDate;
	}

	@Override
	public String getAddress() {
<<<<<<< HEAD
		return this.address;
=======

		return address;
>>>>>>> master
	}

	@Override
	public void setAddress(String address) {
<<<<<<< HEAD
=======

>>>>>>> master
		this.address = address;
	}

	@Override
	public UserStatus getUserStatus() {
<<<<<<< HEAD
		return this.userStatus;
=======

		return userStatus;
>>>>>>> master
	}

	@Override
	public void setUserStatus(UserStatus userStatus) {
<<<<<<< HEAD
=======

>>>>>>> master
		this.userStatus = userStatus;
	}
	
}