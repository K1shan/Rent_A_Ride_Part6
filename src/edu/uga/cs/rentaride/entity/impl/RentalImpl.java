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


public class RentalImpl 
	extends Persistent
	implements Rental 
{

	Date pickupTime;
	Date returnTime;
	int charges;
	Reservation reservation;
	Vehicle vehicle;
	Comment comment;
	
	public RentalImpl(){
		
		super( -1 );
		
		this.pickupTime = null;
		this.returnTime = null;
		this.charges = 0;
		this.reservation = null;
		this.vehicle = null;
		this.comment = null;

	}
	
	public RentalImpl(Date pickupTime, Date returnTime, int charges,Reservation reservation, Vehicle vehicle, Comment comment){
		
		super( -1 );
		
		this.pickupTime = pickupTime;
		this.returnTime = returnTime;
		this.charges = charges;
		this.reservation = reservation;
		this.vehicle = vehicle;
		this.comment = comment;
	}
	
	

	@Override
	public boolean getLate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Customer getCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(Date pickupTime) {
		this.pickupTime = pickupTime;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public int getCharges() {
		return charges;
	}

	public void setCharges(int charges) {
		this.charges = charges;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

}