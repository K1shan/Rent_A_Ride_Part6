package edu.uga.cs.rentaride.entity.impl;



import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.persistence.impl.Persistent;

import java.util.Date;


public class AdministratorImpl 
	extends Persistent
	implements Administrator 
{

	int userId;
	String firstName; 
	String lastName; 
	String userName; 
	String password; 
	String email;
	String address; 
	Date createdDate;
	UserStatus userStatus;
	
	public AdministratorImpl(){
	
		super( -1 );
		this.userId = 0;
		this.firstName = null;
		this.lastName = null;
		this.userName = null;
		this.email =  null;
		this.password =  null;
		this.address =  null;
		this.createdDate = null;
		this.userStatus = null;
	}
	
	public AdministratorImpl(int userId, String firstName, String lastName, String userName, String password, String email,
			String address, Date createdDate, UserStatus userStatus){
		
		super( -1 );
		
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email =  email;
		this.password =  password;
		this.createdDate = createdDate;
		this.address =  address;
		this.userStatus = userStatus;
		
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	@Override
	public Date getCreatedDate() {
		// TODO Auto-generated method stub
		return createdDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		this.createdDate = createDate;
		
	}


}