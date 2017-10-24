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

import java.sql.Timestamp;
import java.util.Date;

import edu.uga.cs.rentaride.RARException;


public class RentalImpl 
	extends Persistent
	implements Rental 
{
<<<<<<< HEAD

=======
>>>>>>> master
	private Date pickupTime;
	private Date returnTime;
	private int charges;
	private Reservation reservation;
	private Vehicle vehicle;
	private Comment comment;
<<<<<<< HEAD
	private boolean late;
=======
>>>>>>> master
	private Customer customer;
	
	public RentalImpl(){
		super( -1 );
		this.pickupTime = null;
		this.returnTime = null;
		this.charges = 0;
		this.reservation = null;
		this.vehicle = null;
		this.comment = null;
<<<<<<< HEAD
		this.late = false;
		this.customer = null;

	}
	
	public RentalImpl(Date pickupTime, Date returnTime, int charges,Reservation reservation, Vehicle vehicle, Comment comment, boolean late, Customer customer){
		
=======
	}
	
	public RentalImpl(Date pickupTime, Reservation reservation, Vehicle vehicle){
>>>>>>> master
		super( -1 );
		this.pickupTime = pickupTime;
		this.returnTime = null;
		this.charges = 0;//vehicle.getVehicleType().getHourlyPrices().get(0).getPrice();
		this.reservation = reservation;
		this.vehicle = vehicle;
		this.comment = comment;
		this.late = late;
		this.customer = customer;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean getLate() {
<<<<<<< HEAD
		return this.late;
=======
		
		if(this.returnTime == null){
			Date date = new Date();
			Timestamp timedate = new Timestamp(date.getTime());
			if(timedate.getHours()-this.pickupTime.getTime() < this.reservation.getLength()){
				return false;
			}else{
				return true;
			}
		}
		
		if(this.returnTime.getTime() - this.pickupTime.getTime() > this.reservation.getLength()){
			return true;
		}else{
			return false;
		}
>>>>>>> master
	}

	@Override
	public Customer getCustomer() {
<<<<<<< HEAD
		return customer;
=======
		return this.customer;
>>>>>>> master
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

	@Override
	public String toString() {
		return "RentalImpl ["
				+ "pickupTime=" + pickupTime + ", returnTime=" + returnTime + ", charges=" + charges
				+ ", reservations=" + this.reservation
				+ ", vehicleId=" + this.vehicle
				+ ", customer=" + this.reservation.getCustomer() + 
				"]";
	}
}