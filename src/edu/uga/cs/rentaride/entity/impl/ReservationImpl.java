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

	@Override
	public Date getPickupTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPickupTime(Date pickupTime) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLength(int length) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer getCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCustomer(Customer customer) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VehicleType getVehicleType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVehicleType(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RentalLocation getRentalLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRentalLocation(RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rental getRental() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRental(Rental rental) {
		// TODO Auto-generated method stub
		
	}
	
}