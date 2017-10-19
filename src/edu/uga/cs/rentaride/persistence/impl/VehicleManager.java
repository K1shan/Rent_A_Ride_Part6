package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.rentaride.RARException;
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
import edu.uga.cs.rentaride.object.ObjectLayer;



public class VehicleManager {

	private ObjectLayer objectLayer = null;
	private Connection con = null;
	
	public VehicleManager(Connection con, ObjectLayer objectLayer){
		this.con = con;
		this.objectLayer = objectLayer;
	}//constructor
	
	
	/*
	 * 
	 * Vehicle
	 * 
	 */
	public List<Vehicle> restore( Vehicle modelVehicle ) throws RARException{
		// TODO
		return null;
	}
	
    public void store( Vehicle vehicle ) throws RARException{
    	// TODO
    }
    
    public void delete( Vehicle vehicle ) throws RARException{
    	// TODO
    }
    
    
    /*
     * 
     * Location @ Vehicle
     * 
     */
    public void storeLocation(Vehicle vehicle, RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
	}
    
    public RentalLocation restoreLocation(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}
    
    public List<Vehicle> restoreLocation(RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}
    
    public void deleteLocation(Vehicle vehicle, RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
	}
    
    
    /*
     * 
     * VehicleType @ Vehicle
     * 
     */
    public void storeType(Vehicle vehicle, VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
	}
    
    public VehicleType restoreType(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}
    
    public List<Vehicle> restoreType(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}
    
    public void deleteType(Vehicle vehicle, VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
	}
    
    
    /*
     * 
     * Rental @ Vehicle
     * 
     */
	public void storeRental(Vehicle vehicle, Rental rental) throws RARException {
		// TODO Auto-generated method stub
	}

	public List<Rental> restoreRental(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	public Vehicle restoreRental(Rental rental) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteRental(Vehicle vehicle, Rental rental) throws RARException {
		// TODO Auto-generated method stub
	}
}
