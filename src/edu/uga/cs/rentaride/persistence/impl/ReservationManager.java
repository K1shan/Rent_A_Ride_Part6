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
		
		String insertReservationQuery =
				"INSERT INTO RESERVATION "
				+ "(location_id, type_id, customer_id, pickup_date, length, cancelled ) "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?, ?)";
		
		String updateReservationQuery =
				"UPDATE INTO RESERVATION "
				+ "(location_id, type_id, customer_id, pickup_date, length, cancelled ) "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?, ?)";
		
		PreparedStatement pstmt;
		int inscnt;
		long reservationId;
		
		try {
			
			// check persist
			if( !reservation.isPersistent() ){
				pstmt = (PreparedStatement) con.prepareStatement( insertReservationQuery );
			}else{
				pstmt = (PreparedStatement) con.prepareStatement( updateReservationQuery );
			}
			
			// update pstmt
			if( reservation.getRentalLocation().getId() != 0 ){
				pstmt.setLong( 1, reservation.getRentalLocation().getId() );
			}else{
				throw new RARException( "Reservation.save: can't save a reservation: location undefined" );
			}
			
			if( reservation.getVehicleType().getId() != 0 ){
				pstmt.setLong( 2, reservation.getVehicleType().getId() );
			}else{
				throw new RARException( "Reservation.save: can't save a reservation: vehicleType undefined" );
			}
			
			if( reservation.getCustomer().getId() != 0 ){
				pstmt.setLong( 3, reservation.getCustomer().getId() );
			}else{
				throw new RARException( "Reservation.save: can't save a reservation: customer undefined" );
			}
			
			if( reservation.getPickupTime() != null ){
				java.util.Date myDate = reservation.getPickupTime();
	        	java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
				pstmt.setDate( 4, sqlDate );
			}else{
				throw new RARException( "Reservation.save: can't save a reservation: pickup undefined" );
			}
			
			if( reservation.getLength() != 0 ){
				pstmt.setLong( 5, reservation.getLength() );
			}else{
				throw new RARException( "Reservation.save: can't save a reservation: length undefined" );
			}
			
			pstmt.setLong( 6, 0 );
			
			System.out.println("query: " + pstmt.asSql());
            inscnt = pstmt.executeUpdate();
			
            // auto_inc pk to object pk
            if ( !reservation.isPersistent() ){
            	if( inscnt == 1 ){
            		String sql = "select last_insert_id()";
            		if( pstmt.execute( sql ) ){
            			ResultSet rs = pstmt.getResultSet();
            			while( rs.next() ){
            				reservationId = rs.getLong( 1 );
            				if( reservationId > 0 ){
            					reservation.setId( reservationId );
            				}
            			}
            		}
            	}
            }else{
            	if( inscnt < 1 ){
            		throw new RARException( "RentalLocationManager.save: failed to save a location" );
            	}
            }
			
		} catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "RentalLocationManager.store: failed to store a location: " + e );
		}			
	}
	
	public List<Reservation> restore( Reservation modelReservation ) throws RARException{
    	// TODO
		
		String selectReservationQuery =
				"SELECT RESERVATION.*, "
				+ "CUSTOMER.customer_id, "
				+ "USER.fname, USER.lname, "
				+ "VEHICLE_TYPE.type_id, VEHICLE_TYPE.name, "
				+ "LOCATION.location_id, LOCATION.name "
				+ "FROM RESERVATION "
				+ "INNER JOIN CUSTOMER ON CUSTOMER.customer_id=RESERVATION.customer_id "
				+ "INNER JOIN USER ON USER.user_id=CUSTOMER.user_id "
				+ "INNER JOIN LOCATION ON LOCATION.location_id=RESERVATION.location_id "
				+ "INNER JOIN VEHICLE_TYPE ON VEHICLE_TYPE.type_id=RESERVATION.type_id"
				+ "INNER JOIN RENTAL ON RENTAL.reservation_id=RESERVATION.reservation_id";
		
		List<Reservation> reservations = new ArrayList<Reservation>();
		Statement stmt = null;
		System.out.println("query: "+selectReservationQuery);
		
		// NULL CHECKER
		if( modelReservation != null ){
			
		}
				
		try {
			stmt = con.createStatement();
			
			if( stmt.execute(selectReservationQuery) ){
				ResultSet rs = stmt.getResultSet();
				
				//"(location_id, type_id, customer_id, pickup_date, length, cancelled ) "

				
				// RESERVATION
				int reservation_id;
				int location_id;
				int type_id;
				int reservation_customer_id;
				Date pickupTime;
				int rentalLength;
				int cancelled;
				
				// USER / CUSTOMER
				int 	customer_customer_id;
	            String 	user_fname;
	            String 	user_lname;
	            
	            // VEHICLE_TYPE
	            int		vehicleType_type_id;
	            String	vehicleType_name;
	            
	            // LOCATION
	            int		location_location_id;
	            String	location_name;
				
				
				VehicleType vehicleType = null;
				RentalLocation rentalLocation = null;
				Customer customer = null;
				Reservation reservation = null;
				
				while( rs.next() ){
					
					// RESERVATION
					reservation_id = rs.getInt(1);
					location_id = rs.getInt(2);
					type_id = rs.getInt(3);
					reservation_customer_id = rs.getInt(4);
					pickupTime = rs.getDate(5);
					rentalLength = rs.getInt(6);
					cancelled = rs.getInt(7);
					
					// USER
					customer_customer_id	= rs.getInt(8);
					user_fname = rs.getString(9);
					user_lname = rs.getString(10);
					
					// VEHICLE_TYPE
					vehicleType_type_id = rs.getInt(11);
					vehicleType_name = rs.getString(12);
					
					// LOCATION
					location_location_id = rs.getInt(13);
					location_name = rs.getString(14);
					
					
					customer = objectLayer.createCustomer();
					customer.setId(customer_customer_id);
					customer.setFirstName(user_fname);
					customer.setLastName(user_lname);
					
					vehicleType = objectLayer.createVehicleType();
					vehicleType.setId(vehicleType_type_id);
					vehicleType.setName(vehicleType_name);
					
					rentalLocation = objectLayer.createRentalLocation();
					rentalLocation.setId(location_location_id);
					rentalLocation.setName(location_name);
//					
//					rental = objectLayer.createRental(rental_pickupTime, reservation, vehicle);
//					rental.setId(rental_id);
//					rental.setRental(rental);
					
					reservation = objectLayer.createReservation(pickupTime, rentalLength, vehicleType, rentalLocation, customer);
					reservation.setId(reservation_id);
//					reservation.setRental(rental);
					reservations.add(reservation);
				}
			}
			return reservations;
			
		} catch (SQLException e){
			e.printStackTrace();
			throw new RARException( "RentalLocationManager.get: failed to get any locations: " + e );
		}
    }

	public Customer restoreCustomerReservation(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void delete( Reservation reservation ) throws RARException{
		
		String deleteReserv = "DELETE FROM RESERVATION WHERE reservation_id = ?";              
		PreparedStatement stmt = null;
		int inscnt = 0;
		             
        if( !reservation.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) con.prepareStatement(deleteReserv);         
            stmt.setLong( 1, reservation.getId() );
            inscnt = stmt.executeUpdate();          
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new RARException( "ReservationManager.delete: failed to delete a reservation" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "ReservationManager.delete: failed to delete a reservation: " + e );       
            }
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
