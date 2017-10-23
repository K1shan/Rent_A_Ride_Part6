package edu.uga.cs.rentaride.entity.impl;



import java.util.List;

import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.persistence.impl.Persistent;


public class RentalLocationImpl 
	extends Persistent
	implements RentalLocation 
{
	private String name;
	private String address;
	private int capacity;
	private List<Reservation> reservations;
	private List<Vehicle> vehicles;
	
	public RentalLocationImpl(){
		super( -1 );
		this.name = null;
		this.address = null;
		this.capacity = 0;
	}
	
	public RentalLocationImpl(String name, String address, int capacity){
		super( -1 );
		this.name = name;
		this.address = address;
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "RentalLocationImpl [name=" + name + ", address=" + address + ", capacity=" + capacity + "]";
	}

	@Override
	public List<Reservation> getReservations() {
		return this.reservations;
	}

	@Override
	public List<Vehicle> getVehicles() {
		return this.vehicles;
	}
	
}