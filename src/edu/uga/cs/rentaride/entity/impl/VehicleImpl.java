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
	private String make;
	private String model;
	private int year;
	private String registrationTag;
	private int mileage;
	private Date lastServiced;
	private VehicleType vehicleType;
	private RentalLocation rentalLocation;
	private VehicleCondition condition;
	private VehicleStatus status;
	private List<Rental> rentals;
	
	public VehicleImpl(){
		super( -1 );
		this.make = null;
		this.model = null;
		this.year = 0;
		this.registrationTag = null;
		this.mileage = 0;
		this.lastServiced = null;
		this.vehicleType = null;
		this.rentalLocation = null;
		this.condition = null;
		this.status = null;
	}	
	
	public VehicleImpl(String make, String model, int year, String registrationTag, int mileage, Date lastServiced,
			VehicleType vehicleType, RentalLocation rentalLocation, VehicleCondition condition,
			VehicleStatus status) {
		super( -1 );
		this.make = make;
		this.model = model;
		this.year = year;
		this.registrationTag = registrationTag;
		this.mileage = mileage;
		this.lastServiced = lastServiced;
		this.vehicleType = vehicleType;
		this.rentalLocation = rentalLocation;
		this.condition = condition;
		this.status = status;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getRegistrationTag() {
		return registrationTag;
	}

	public void setRegistrationTag(String registrationTag) {
		this.registrationTag = registrationTag;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public Date getLastServiced() {
		return lastServiced;
	}

	public void setLastServiced(Date lastServiced) {
		this.lastServiced = lastServiced;
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

	@Override
	public String getModel() {
		return this.model;
	}

	@Override
	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public VehicleStatus getStatus() {
		return this.status;
	}

	@Override
	public void setStatus(VehicleStatus status) {
		this.status = status;
	}

	@Override
	public VehicleCondition getCondition() {
		return this.condition;
	}

	@Override
	public void setCondition(VehicleCondition condition) {
		this.condition = condition;
	}

	@Override
	public List<Rental> getRentals() {
		return this.rentals;
	}
	
	public void addRental(Rental rental) {
		this.rentals.add(rental);
	}

	@Override
	public String toString() {
		return "VehicleImpl "
				+ "[make=" + this.make + ", model=" + this.model + ", year=" + this.year 
				+ ", registrationTag=" + this.registrationTag + ", mileage=" + this.mileage 
				+ ", lastServiced=" + this.lastServiced 
				+ ", vehicleTypeName=" + this.vehicleType.getName() 
				+ ", rentalLocationName=" + this.rentalLocation.getName() 
				+ ", condition=" + this.condition + ", status=" + this.status +
				"]";
	}
}