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



public class ReservationManager {
	
	private ObjectLayer objectLayer = null;
	private Connection con = null;
	
	public ReservationManager(Connection con, ObjectLayer objectLayer){
		this.con = con;
		this.objectLayer = objectLayer;
	}//constructor
	
	
	/*
	 * 
	 * Reservation
	 * 
	 */
	public void store( Reservation reservation ) throws RARException{
		// TODO
	}
	
	public List<Reservation> restore( Reservation modelReservation ) throws RARException{
    	// TODO
    	return null;
    }

	public Customer restoreCustomerReservation(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void delete( Reservation reservation ) throws RARException{
    	// TODO
    }
	
	
	/*
	 * 
	 * Customer @ Reservation
	 * 
	 */
	public void store(Customer customer, Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		
	}

    public List<Reservation> restore(Customer customer) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}
    
    public void delete(Customer customer, Reservation reservation) throws RARException {
		// TODO
	}
    
    
    /*
     * 
     * RentalLocation @ Reservation
     * 
     */
    public void storeLocation(Reservation reservation, RentalLocation rentalLocation)
			throws RARException {
		// TODO Auto-generated method stub	
	}
    
    public RentalLocation restoreLocation(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}
    
    public List<Reservation> restoreLocation(RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}
    
    public void deleteLocation(Reservation reservation, RentalLocation rentalLocation)
			throws RARException {
		// TODO Auto-generated method stub
	}
    
    
    /*
     * 
     * VehicleType @ Reservation
     * 
     */
    public void storeType(Reservation reservation, VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
	}
    
    public VehicleType restoreType(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}
    
    public List<Reservation> restoreType(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}
    
    public void deleteType(Reservation reservation, VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
	}
}
