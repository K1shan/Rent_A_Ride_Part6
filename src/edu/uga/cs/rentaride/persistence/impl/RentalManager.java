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



public class RentalManager {

	private ObjectLayer objectLayer = null;
	private Connection con = null;
	
	public RentalManager(Connection con, ObjectLayer objectLayer){
		this.con = con;
		this.objectLayer = objectLayer;
	}//constructor
	
	
	/*
	 * 
	 * Rental
	 * 
	 */
	public List<Rental> restore( Rental modelRental ) throws RARException{
		// TODO
		return null;
	}
	
	
    public void store( Rental rental ) throws RARException{
    	// TODO
    }
    
    public void delete(Rental rental) throws RARException{
    	// TODO
    }
    
    
    /*
     * 
     * Comment @ Rental
     * 
     */
	public void storeComment(Rental rental, Comment comment) throws RARException {
		// TODO Auto-generated method stub
	}

	public Rental restoreComment(Comment comment) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Comment> restoreComment(Rental rental) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteComment(Rental rental, Comment comment) throws RARException {
		// TODO Auto-generated method stub
	}
	
	
	/*
	 * 
	 * Reservation @ Rental
	 * 
	 */
	public void storeReservation(Rental rental, Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
	}

	public Rental restoreReservation(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	public Reservation restoreReservation(Rental rental) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteReservation(Rental rental, Reservation reservation) throws RARException {
		
	}
}
