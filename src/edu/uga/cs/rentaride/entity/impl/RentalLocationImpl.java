package edu.uga.cs.rentaride.entity.impl;



import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.persistence.impl.Persistent;


public class RentalLocationImpl 
	extends Persistent
	implements RentalLocation 
{
	String name;
	String address;
	int capacity;
	
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
	
}