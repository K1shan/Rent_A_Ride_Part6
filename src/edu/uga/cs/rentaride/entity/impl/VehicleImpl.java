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


public class VehicleImpl 
	extends Persistent
	implements Vehicle 
{

	@Override
	public String getMake() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMake(String make) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModel(String model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getYear() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setYear(int year) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getRegistrationTag() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRegistrationTag(String registrationTag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMileage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMileage(int mileage) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getLastServiced() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLastServiced(Date lastServiced) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VehicleStatus getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStatus(VehicleStatus status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VehicleCondition getCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCondition(VehicleCondition condition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VehicleType getVehicleType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVehicleType(VehicleType vehicleType) {
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
	public List<Rental> getRentals() {
		// TODO Auto-generated method stub
		return null;
	}
	
}