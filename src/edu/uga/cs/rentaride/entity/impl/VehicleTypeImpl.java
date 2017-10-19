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

import java.util.List;

import edu.uga.cs.rentaride.RARException;


public class VehicleTypeImpl 
	extends Persistent
	implements VehicleType 
{
	
	private String name;

	public VehicleTypeImpl() {
		
		super( -1 );
		
		this.name = null;
	}
	
	public VehicleTypeImpl(String name) {
		
		super( -1 );
		
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) throws RARException {
		// TODO Auto-generated method stub
		this.name = name;
	}

	@Override
	public List<HourlyPrice> getHourlyPrices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehicle> getVehicles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> getReservations() {
		// TODO Auto-generated method stub
		return null;
	}
	
}