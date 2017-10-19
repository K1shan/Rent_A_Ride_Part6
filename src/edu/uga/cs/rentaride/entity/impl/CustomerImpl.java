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
import java.util.List;

import edu.uga.cs.rentaride.RARException;

public class CustomerImpl 
	extends Persistent
	implements Customer
{
	private Date createDate;
	private Date memberUntil;
	private Date cardExpiration;
	private String firstName;
	String lastName;
	String userName;
	String email;
	String password;
	String address;
	String state;
	String licenseNumber;
	String cardNumber;
	private UserStatus userStatus;
	
	
	public CustomerImpl(){
	
		
		super( -1 );
		this.createDate = null;
		this.memberUntil = null;
		this.cardExpiration = null;
		this.firstName = null;
		this.lastName = null;
		this.userName = null;
		this.email =  null;
		this.password =  null;
		this.address =  null;
		this.state =  null;
		this.licenseNumber = null;
		this.cardNumber = null;
		this.userStatus = null;
	}
	
	public CustomerImpl(String firstName, String lastName, String userName, String password, String email,
			String address, Date createDate, Date membershipExpiration, String licenseState, String licenseNumber,
			String cardNumber, Date cardExpiration){
		
		super( -1 );
		this.createDate = createDate;
		this.memberUntil = membershipExpiration;
		this.cardExpiration = cardExpiration;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email =  email;
		this.password =  password;
		this.address =  address;
		this.state =  licenseState;
		this.licenseNumber = licenseNumber;
		this.cardNumber = cardNumber;
		this.userStatus = userStatus;
		
	}
	
	
	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return this.firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
		
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return this.lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
		
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	@Override
	public void setUserName(String userName) throws RARException {
		this.userName = userName;
		
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
		
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public void setPassword(String password) {
		
		this.password = password;
		
	}

	@Override
	public Date getCreatedDate() {
		return this.createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String getAddress() {
		// TODO Auto-generated method stub
		return this.address;
	}

	@Override
	public void setAddress(String address) {
		
		this.address = address;
		
	}

	@Override
	public UserStatus getUserStatus() {
		// TODO Auto-generated method stub
		return this.userStatus;
	}

	@Override
	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
		
	}

	@Override
	public Date getMemberUntil() {
		// TODO Auto-generated method stub
		return this.memberUntil;
	}

	@Override
	public void setMemberUntil(Date memberUntil) throws RARException {
		this.memberUntil = memberUntil;
		
	}

	@Override
	public String getLicenseState() {
		
		return this.state;
	}

	@Override
	public void setLicenseState(String state) {
		
		this.state = state;
	}

	@Override
	public String getLicenseNumber() {
		// TODO Auto-generated method stub
		return this.licenseNumber;
	}

	@Override
	public void setLicenseNumber(String licenseNumber) {
		
		this.licenseNumber = licenseNumber;
		
	}

	@Override
	public String getCreditCardNumber() {
		// TODO Auto-generated method stub
		return this.cardNumber;
	}

	@Override
	public void setCreditCardNumber(String cardNumber) {
		// TODO Auto-generated method stub
		
		this.cardNumber = cardNumber;
		
	}

	@Override
	public Date getCreditCardExpiration() {
		return this.cardExpiration;
	}

	@Override
	public void setCreditCardExpiration(Date cardExpiration) {
		this.cardExpiration = cardExpiration;
		
	}

	@Override
	public List<Reservation> getReservations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> getComments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rental> getRentals() {
		// TODO Auto-generated method stub
		return null;
	}
	
}