package edu.uga.cs.rentaride.entity.impl;



import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.persistence.impl.Persistent;

import java.util.Date;


public class AdministratorImpl 
	extends Persistent
	implements Administrator 
{
	private String firstName; 
	private String lastName; 
	private String userName; 
	private String password; 
	private String email;
	private String address; 
	private Date createdDate;
	private UserStatus userStatus;
	
	public AdministratorImpl(){
		super( -1 );
		this.firstName = null;
		this.lastName = null;
		this.userName = null;
		this.email =  null;
		this.password =  null;
		this.address =  null;
		this.createdDate = null;
		this.userStatus = null;
	}
	
	public AdministratorImpl(String firstName, String lastName, String userName, String password, String email,
			String address, Date createdDate){
		super( -1 );
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email =  email;
		this.password =  password;
		this.createdDate = createdDate;
		this.address =  address;
<<<<<<< HEAD
		this.userStatus = userStatus;
		
	}

	@Override
	public int getUserId() {
		return this.userId;
	}

	@Override
	public void setUserId(int userId) {
		this.userId = userId;
=======
		this.userStatus = UserStatus.ACTIVE;
>>>>>>> master
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public UserStatus getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

<<<<<<< HEAD
	@Override
	public Date getCreatedDate() {
		return this.createdDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		this.createdDate = createDate;
=======
	public void setCreateDate(Date createDate) {
		this.createdDate = createDate;
	}

	@Override
	public String toString() {
		return "AdministratorImpl [adminId=" + this.getId() + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", password=" + password + ", email=" + email + ", address=" + address + ", createdDate="
				+ createdDate + ", userStatus=" + userStatus + "]";
>>>>>>> master
	}


}