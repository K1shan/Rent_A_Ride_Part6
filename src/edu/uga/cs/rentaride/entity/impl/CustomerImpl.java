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

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
	private String lastName;
	private String userName;
	private String email;
	private String password;
	private String address;
	private String state;
	private String licenseNumber;
	private String cardNumber;
	private UserStatus userStatus;
	private List<Reservation> reservations;
	private List<Comment> comments;
	private List<Rental> rentals;
	
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
		this.reservations = null;
		this.comments = null;
		this.rentals = null;
		this.reservations = null;
		this.comments = null;
		this.rentals = null;
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
		this.userStatus = UserStatus.ACTIVE;
		this.reservations = null;
		this.comments = null;
		this.rentals = null;
	}
  
	@Override
	public String getFirstName() {
		return this.firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Override
	public String getLastName() {
		return this.lastName;
	}

	@Override
	public void setLastName(String lastName) {
		
		this.lastName = lastName;
	}

	@Override
	public String getUserName() {
		return this.userName;
	}

	@Override
	public void setUserName(String userName) throws RARException {
		this.userName = userName;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
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
		return this.address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public UserStatus getUserStatus() {
		return this.userStatus;
	}

	@Override
	public void setUserStatus(UserStatus userStatus) {
		
		this.userStatus = userStatus;
	}

	@Override
	public Date getMemberUntil() {
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

		return this.licenseNumber;
	}

	@Override
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	@Override
	public String getCreditCardNumber() {
		return this.cardNumber;
	}

	@Override
	public void setCreditCardNumber(String cardNumber) {
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
	public List<Reservation> getReservations() throws RARException{
		if(reservations == null){
			if(isPersistent() ){
				reservations = getPersistenceLayer().restoreCustomerReservation( this );
			}else{
                throw new RARException( "This Customer object is not persistent" );
			}
		}
        return reservations;
	}

	@Override
	public List<Comment> getComments() {
		if(comments == null){
			if(isPersistent() ){
				//comments = getPersistenceLayer().restoreComm
			}else{
                //throw new RARException( "This Customer object is not persistent" );
			}
		}
        return comments;
	}

	@Override
	public List<Rental> getRentals() throws RARException {
		if(rentals == null){
			if(reservations != null){
				Iterator<Reservation> reservationIter = reservations.iterator();
				if(isPersistent() ){
					Reservation reservation = null;
					Rental rental = null;
					while(reservationIter.hasNext()){
		        		 reservation = reservationIter.next();
		        		 rental = getPersistenceLayer().restoreRentalReservation(reservation);
		        		 rentals.add(rental);
		        	 }
				}
			}
		}
        return rentals;
	}

	@Override
	public String toString() {
		return "CustomerImpl [createDate=" + createDate + ", memberUntil=" + memberUntil + ", cardExpiration="
				+ cardExpiration + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", email=" + email + ", password=" + password + ", address=" + address + ", state=" + state
				+ ", licenseNumber=" + licenseNumber + ", cardNumber=" + cardNumber + ", userStatus=" + userStatus
				+ ", reservations=" + reservations + ", comments=" + comments + ", rentals=" + rentals + "]";
	}

	
}