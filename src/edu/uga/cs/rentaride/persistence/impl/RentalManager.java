package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
	public void store( Rental rental ) throws RARException{
    	// TODO
		
		String insertRentalQuery = 
				"INSERT INTO RENTAL "
				+ "(reservation_id, vehicle_id, pickup_date, return_date, late, charges) "
				+ "VALUES "
				+ "( ?, ?, ?, ?, ?, ? )";
    	
    	String updateRentalQuery = 
				"UPDATE RENTAL SET "
				+ "reservation_id = ?, vehicle_id = ?, pickup_date = ?, return_date = ?, "
				+ "late = ?, charges = ?"
				+ "WHERE rental_id = ?"; 
    	
    	PreparedStatement pstmt;
    	long rentalId;
		int inscnt;
		
		try {
			if( !rental.isPersistent() ){
	            pstmt = (PreparedStatement) con.prepareStatement( insertRentalQuery );
			}else{
	            pstmt = (PreparedStatement) con.prepareStatement( updateRentalQuery );
			}
			
			if( rental.getReservation().getId() != 0 ){
				pstmt.setLong( 1, rental.getReservation().getId() );
			}else{
	            throw new RARException( "RentalManager.save: can't save a rental: reservation undefined" );
	        }
			
			if( rental.getVehicle().getId() != 0 ){
				pstmt.setLong( 2, rental.getVehicle().getId() );
			}else{
	            throw new RARException( "RentalManager.save: can't save a rental: vehicle undefined" );
	        }
			
			if( rental.getPickupTime() != null ){
				java.util.Date myDate = rental.getPickupTime();
	        	java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
	    		pstmt.setDate( 3, sqlDate );
			}else{
	            throw new RARException( "RentalManager.save: can't save a rental: pickupTime undefined" );
	        }
		
			if(rental.getReturnTime() == null){
				pstmt.setDate(4, null);
			}else{
				java.util.Date myDate = rental.getReturnTime();
				java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
				pstmt.setDate( 4, sqlDate );
			}
			
    		if( rental.getLate() == false) {
    			pstmt.setLong( 5, 0 );
    		}else if( rental.getLate() == true ){
    			pstmt.setLong( 5, 1 );
    		}else{
    			throw new RARException( "RentalManager.save: can't save a rental: late undefined" );
    		}
			
    		
    		if( rental.getCharges() >= 0 ){
    			pstmt.setLong( 6, rental.getCharges()) ;
    		}else{
    			throw new RARException( "RentalManager.save: can't save a rental: charges negative" );
    		}
    		
    		System.out.println("query: "+pstmt.asSql());
	        inscnt = pstmt.executeUpdate();
	        
	        if( !rental.isPersistent() ) {
                if( inscnt == 1 ) {
                    String sql = "select last_insert_id()";
                    if( pstmt.execute( sql ) ) {
                        ResultSet r = pstmt.getResultSet();
                        while( r.next() ) {
                            rentalId = r.getLong( 1 );
                            if( rentalId > 0 )
                            	rental.setId( rentalId ); 
                        }
                    }
                }
            }else {
				if(inscnt < 1)
					throw new RARException("RentalManager.save: failed to save a rental");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RARException( "Rental.save: failed to save a rental: " + e );
		}
    }
	
	public List<Rental> restore( Rental modelRental ) throws RARException{
		// TODO
		
		String selectRentalQuery = "SELECT "
				+ "RENTAL.*, "
				+ "RESERVATION.*, "
				+ "VEHICLE.*, "
				+ "VEHICLE_TYPE.*, "
				+ "LOCATION.*, "
				+ "USER.*, "
				+ "CUSTOMER.*, "
				+ "COMMENT.* "
				+ "FROM RENTAL "
				+ "INNER JOIN RESERVATION on RESERVATION.reservation_id=RENTAL.reservation_id "
				+ "INNER JOIN VEHICLE ON VEHICLE.vehicle_id=RENTAL.vehicle_id "
				+ "INNER JOIN VEHICLE_TYPE ON VEHICLE_TYPE.type_id=VEHICLE.type_id "
				+ "AND VEHICLE_TYPE.type_id=RESERVATION.type_id "
				+ "INNER JOIN LOCATION ON LOCATION.location_id=RESERVATION.location_id "
				+ "INNER JOIN CUSTOMER ON CUSTOMER.customer_id=RESERVATION.customer_id "
				+ "INNER JOIN USER ON USER.user_id=CUSTOMER.user_id "
				+ "INNER JOIN COMMENT ON COMMENT.rental_id=RENTAL.rental_id";

		
		Statement stmt = null;
		StringBuffer query = new StringBuffer(100);
		StringBuffer condition = new StringBuffer(100);
		List<Rental> rentals = new ArrayList<Rental>();
		
		condition.setLength(0);
		
		query.append(selectRentalQuery);
		
		System.out.println("query: "+ selectRentalQuery);
		
		if( modelRental != null ){
			
		}
		
		try {
			stmt = con.createStatement();
			if( stmt.execute(query.toString() ) ){
				ResultSet rs = stmt.getResultSet();
				
				// RENTAL
				int		rental_id;
				int 	rental_reservation_id;
				int		rental_vehicle_id;
				Date 	rental_pickupTime = null;
				Date 	rental_returnTime = null;
				int 	rental_late;
				int 	rental_charges;
				
				// RESERVATION
				int 	reservation_reservation_id;
				int 	reservation_location_id;
				int 	reservation_type_id;
				int 	reservation_customer_id;
				Date 	reservation_pickupTime;
				int 	reservation_rentalLength;
				int 	reservation_cancelled;
				
				// VEHICLE
				int 	vehicle_vehicle_id;
				int 	vehicle_type_id;
				int 	vehicle_location_id;
				String 	vehicle_make;
				String 	vehicle_model;
				int 	vehicle_year;
				int 	vehicle_mileage;
				String 	vehicle_tag;
				Date 	vehicle_service_date;
				int 	vehicle_status;
				int 	vehicle_cond;
				
				// VEHICLE_TYPE
				int   	vehicleType_type_id;
                String 	vehicleType_name;
                
                // LOCATION
                int 	location_location_id;
				String 	location_name;
				String 	location_address;
				int 	location_capacity;
                
				// USER
				int 	user_user_id;
	            String 	user_fname;
	            String 	user_lname;
	            String 	user_uname;
	            String 	user_pword;
	            String 	user_email;
	            String 	user_address;
	            Date 	user_createDate;
				
				// CUSTOMER
	            int 	customer_customer_id;
	            int 	customer_user_id;
	            Date 	customer_memberUntil;
	            String 	customer_licState;
	            String 	customer_licNum;
	            String 	customer_ccNum;
	            Date 	customer_ccExp;
	            int 	customer_status;
	            
	            // COMMENT
	            int		comment_comment_id;
				int		comment_rental_id;
				String	comment_text;
				Date	comment_date;
				
				
				VehicleStatus vehicle_vehicleStatus = VehicleStatus.INLOCATION;
				VehicleCondition vehicle_vehicleCondition = VehicleCondition.GOOD;
				Reservation	reservation = null;
				Vehicle vehicle = null;
				Rental rental = null;
				VehicleType vehicleType = null;
				Customer customer = null;
				RentalLocation rentalLocation = null;
				Comment comment = null;
				
				
				while( rs.next() ){
					
					// RENTAL
					rental_id = rs.getInt(1);
					rental_reservation_id = rs.getInt(2);
					rental_vehicle_id = rs.getInt(3);
					rental_pickupTime = rs.getDate(4);
					rental_returnTime = rs.getDate(5);
					rental_late = rs.getInt(6);
					rental_charges = rs.getInt(7);
					
					// RESERVATION
					reservation_reservation_id = rs.getInt(8);
					reservation_location_id = rs.getInt(9);
					reservation_type_id = rs.getInt(10);
					reservation_customer_id = rs.getInt(11);
					reservation_pickupTime = rs.getDate(12);
					reservation_rentalLength = rs.getInt(13);
					reservation_cancelled = rs.getInt(14);
					
					// VEHICLE
					vehicle_vehicle_id = rs.getInt(15);
					vehicle_type_id = rs.getInt(16);
					vehicle_location_id = rs.getInt(17);
					vehicle_make = rs.getString(18);
					vehicle_model = rs.getString(19);
					vehicle_year = rs.getInt(20);
					vehicle_mileage = rs.getInt(21);
					vehicle_tag = rs.getString(22);
					vehicle_service_date = rs.getDate(23);
					vehicle_status = rs.getInt(24);
					if(vehicle_status == 1){
						vehicle_vehicleStatus = VehicleStatus.INRENTAL;
					}
					vehicle_cond = rs.getInt(25);
					if(vehicle_cond == 1){
						vehicle_vehicleCondition = VehicleCondition.NEEDSMAINTENANCE;
					}
					
					// VEHICLE_TYPE
					vehicleType_type_id = rs.getInt(26);
					vehicleType_name = rs.getString(27);
					
					// LOCATION
					location_location_id = rs.getInt(28);
					location_name = rs.getString(29);
					location_address = rs.getString(30);
					location_capacity = rs.getInt(31);
					
					// USER
					user_user_id	= rs.getInt(32);
					user_fname = rs.getString(33);
					user_lname = rs.getString(34);
					user_uname = rs.getString(35);
					user_pword = rs.getString(36);
					user_email = rs.getString(37);
					user_address = rs.getString(38);
					user_createDate = rs.getDate(39);
					
					// CUSTOMER
					customer_customer_id = rs.getInt(40);
					customer_user_id = rs.getInt(41);
					customer_memberUntil = rs.getDate(42);
					customer_licState = rs.getString(43);
					customer_licNum = rs.getString(44);
					customer_ccNum = rs.getString(45);
					customer_ccExp = rs.getDate(46);
					customer_status = rs.getInt(47);
					
					// COMMENT
					comment_comment_id = rs.getInt(48);
					comment_rental_id = rs.getInt(49);
					comment_text = rs.getString(50);
					comment_date = rs.getDate(51);
					
					vehicleType = objectLayer.createVehicleType(vehicleType_name);
					vehicleType.setId(vehicleType_type_id);
					vehicleType.setName(vehicleType_name);
					
					rentalLocation = objectLayer.createRentalLocation(location_name, location_address, location_capacity);
					rentalLocation.setId(location_location_id);
					rentalLocation.setName(location_name);
					
					customer = objectLayer.createCustomer(user_fname, user_lname, user_uname, user_pword, user_email, user_address, user_createDate, customer_memberUntil, customer_licState, customer_licNum, customer_ccNum, customer_ccExp);
					customer.setId(customer_customer_id);
					customer.setFirstName(user_fname);
					customer.setLastName(user_lname);
					
					reservation = objectLayer.createReservation(reservation_pickupTime, reservation_rentalLength, vehicleType, rentalLocation, customer);
					reservation.setId(reservation_reservation_id);
					
					vehicle = objectLayer.createVehicle(vehicle_make, vehicle_model, vehicle_year, vehicle_tag, vehicle_mileage, vehicle_service_date, vehicleType, rentalLocation, vehicle_vehicleCondition, vehicle_vehicleStatus);
					vehicle.setId(vehicle_vehicle_id);
					
					rental = objectLayer.createRental(rental_pickupTime, reservation, vehicle);
					rental.setId(rental_id);
					
					comment = objectLayer.createComment();
					rental = objectLayer.createRental();
					
					comment.setId(comment_comment_id);
					comment.setText(comment_text);
					comment.setDate(vehicle_service_date);
					
					rental.setId(rental_id);
					rental.setPickupTime(rental_pickupTime);
					rental.setReservation(reservation);
					rental.setVehicle(vehicle);

					comment.setRental(rental);
					rental.setComment(comment);
					comment.setRental(rental);

					rentals.add(rental);
				}
			}
			return rentals;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RARException("RentalManager.get: failed to get any rentals: " + e);
		}
  }

    public void delete(Rental rental) throws RARException{
    	
		String deleteRental = "DELETE FROM RENTAL WHERE rental_id = ?";              
		PreparedStatement stmt = null;
		int inscnt = 0;
		             
        if( !rental.isPersistent() )
            return;
        
        try {
        	
            stmt = (PreparedStatement) con.prepareStatement( deleteRental );         
            stmt.setLong( 1, rental.getId() );
            inscnt = stmt.executeUpdate();          
            if( inscnt == 1 ) {
            	
                return;
            }
            else
                throw new RARException( "Rental.delete: failed to delete a Rental" );
        }
        catch( SQLException e ) {
        	
            e.printStackTrace();
            throw new RARException( "Rental.delete: failed to delete a Rental: " + e );       
            }
    	
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
