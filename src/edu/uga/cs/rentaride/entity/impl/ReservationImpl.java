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


public class ReservationImpl 
	extends Persistent
	implements Reservation 
{
	private Date pickupTime;
	private int rentalLength;
	private VehicleType vehicleType;
	private RentalLocation rentalLocation;
	private Customer customer;
	private Rental rental;
	
	public ReservationImpl(){
		super( -1 );
		this.pickupTime = null;
		this.rentalLength = 0;
		this.vehicleType = null;
		this.rentalLocation = null;
		this.customer = null;
		this.rental = null;
	}
	
	public ReservationImpl(Date pickupTime, int rentalLength, VehicleType vehicleType, RentalLocation rentalLocation,
			Customer customer) {
		super(-1);
		this.pickupTime = pickupTime;
		this.rentalLength = rentalLength;
		this.vehicleType = vehicleType;
		this.rentalLocation = rentalLocation;
		this.customer = customer;
	}

<<<<<<< HEAD
	private Date pickupTime;
	private int length;
	private Customer customer;
	private VehicleType vehicleType;
	private RentalLocation rentalLocation;
	private Rental rental;
	
	public ReservationImpl() {
		super ( -1 );
		this.pickupTime = null;
		this.length = 0;
		this.customer = null;
		this.vehicleType = null;
		this.rentalLocation = null;
		this.rental = null;
	}
	
	public ReservationImpl(Date pickupTime, int length, Customer customer, VehicleType vehicleType, RentalLocation rentalLocation, Rental rental) {
		super ( -1 );
		this.pickupTime = pickupTime;
		this.length = length;
		this.customer = customer;
		this.vehicleType = vehicleType;
		this.rentalLocation = rentalLocation;
		this.rental = rental;
	}
	
	@Override
	public Date getPickupTime() {
		return this.pickupTime;
	}

	@Override
	public void setPickupTime(Date pickupTime) throws RARException {
		this.pickupTime = pickupTime;
	}

	@Override
	public int getLength() {
		return this.length;
	}

	@Override
	public void setLength(int length) throws RARException {
		this.length = length;
	}

	@Override
	public Customer getCustomer() {
		return this.customer;
	}

	@Override
	public void setCustomer(Customer customer) throws RARException {
		this.customer = customer;
	}

	@Override
	public VehicleType getVehicleType() {
		return this.vehicleType;
	}

	@Override
	public void setVehicleType(VehicleType vehicleType) throws RARException {
		this.vehicleType = vehicleType;
	}

	@Override
	public RentalLocation getRentalLocation() {
		return this.rentalLocation;
	}

	@Override
	public void setRentalLocation(RentalLocation rentalLocation) throws RARException {
		this.rentalLocation = rentalLocation;
=======
	public Date getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(Date pickupTime) {
		this.pickupTime = pickupTime;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public RentalLocation getRentalLocation() {
		return rentalLocation;
	}

	public void setRentalLocation(RentalLocation rentalLocation) {
		this.rentalLocation = rentalLocation;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public int getLength() {
		return this.rentalLength;
	}

	@Override
	public void setLength(int length) throws RARException {
		this.rentalLength = length;
>>>>>>> master
	}

	@Override
	public Rental getRental() {
		return this.rental;
	}

	@Override
	public void setRental(Rental rental) {
		this.rental = rental;
<<<<<<< HEAD
=======
	}

	@Override
	public String toString() {
		return "ReservationImpl [pickupTime=" + this.pickupTime + ", rentalLength=" + this.rentalLength 
				+ ", vehicleTypeName=" + this.vehicleType.getName()
				+ ", rentalLocationName=" + this.rentalLocation.getName()
				+ ", customerName=" + this.customer.getFirstName()+" "+this.customer.getLastName()
				+
				"]";
>>>>>>> master
	}
}