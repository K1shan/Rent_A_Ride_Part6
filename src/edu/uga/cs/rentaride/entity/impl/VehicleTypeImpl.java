package edu.uga.cs.rentaride.entity.impl;



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


public class VehicleTypeImpl 
	extends Persistent
	implements VehicleType 
{
	private String name;
	private List<HourlyPrice> hourlyPrices;
	private List<Vehicle> vehicles;
	private List<Reservation> reservations;
	
	public VehicleTypeImpl(){
		super( -1 );
		this.name = null;
		this.hourlyPrices = null;
		this.vehicles = null;
		this.reservations = null;
	}
	
	public VehicleTypeImpl(String name){
		super( -1 );
		this.name = name;
		this.hourlyPrices = hourlyPrices;
		this.vehicles = vehicles;
		this.reservations = reservations;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) throws RARException {
		this.name = name;
	}

	public void setHourlyPrices(List<HourlyPrice> hourlyPrices) {
		this.hourlyPrices = hourlyPrices;
	}
	
	@Override
	public List<HourlyPrice> getHourlyPrices() {
		return this.hourlyPrices;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
	@Override
	public List<Vehicle> getVehicles() {
		return this.vehicles;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	@Override
	public List<Reservation> getReservations() {
		return this.reservations;
	}

	@Override
	public String toString() {
		return "VehicleTypeImpl [type_id=" +this.getId()+", name=" + name + ", hourlyPrices=" + hourlyPrices + ", vehicles=" + vehicles
				+ ", reservations=" + reservations + "]";
	}
	
}
