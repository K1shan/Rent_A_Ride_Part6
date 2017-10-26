package edu.uga.cs.rentaride.persistence.impl;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.persistence.Persistable;



public class VehicleTypeManager{
	
	private ObjectLayer objectLayer = null;
	private Connection con = null;
	
	public VehicleTypeManager(Connection con, ObjectLayer objectLayer){
		this.con = con;
		this.objectLayer = objectLayer;
		
	}//constructor
	
	
	/*
	 * 
	 * VehicleType
	 * 
	 */
	public void store( VehicleType vehicleType ) throws RARException{
    	String insertVehicleTypeQuery = 
				"INSERT INTO VEHICLE_TYPE "
				+ "(name) "
				+ "VALUES "
				+ "(?)";
    	
    	String updateVehicleTypeQuery = 
				"UPDATE VEHICLE_TYPE SET "
				+ "name = ?"
				+ "WHERE type_id=?"; 
    	
    	PreparedStatement pstmt;
    	long vehicleTypeId;
		int inscnt;
		
		try{
			
			if( !vehicleType.isPersistent() ){
                pstmt = (PreparedStatement) con.prepareStatement( insertVehicleTypeQuery );
			}else{
                pstmt = (PreparedStatement) con.prepareStatement( updateVehicleTypeQuery );
			}
			
			if( vehicleType.getName() != null )
                pstmt.setString( 1, vehicleType.getName());
            else{
                throw new RARException( "CustomerManager.save: can't save a user: FirstName undefined" );
            }
			
            inscnt = pstmt.executeUpdate();
			
            if( !vehicleType.isPersistent() ) {
            	
                if( inscnt == 1 ) {
                	
                    String sql = "select last_insert_id()";
                    if( pstmt.execute( sql ) ) {

                        ResultSet r = pstmt.getResultSet();
                        while( r.next() ) {

                            vehicleTypeId = r.getLong( 1 );
                            if( vehicleTypeId > 0 )
                            	vehicleType.setId( vehicleTypeId );
                        }
                    }
                }
            }{
				if(inscnt < 1)
					throw new RARException("VehicleTypeManager.save: failed to save a VehicleType");
			}
            
		} catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "VehicleType.save: failed to save a type: " + e );
		}
    }
    
	
	public List<VehicleType> restore( VehicleType modelVehicleType ) throws RARException{
		String selectVehicleTypeQuery =
				"SELECT "
				+ "VEHICLE_TYPE.type_id, VEHICLE_TYPE.name, "
				+ "VEHICLE.*, "
				+ "RESERVATION.*, "
				+ "HOURLY_PRICE.*, "
				+ "LOCATION.*, "
				+ "USER.*, "
				+ "CUSTOMER.* "
				+ "FROM VEHICLE_TYPE "
				+ "INNER JOIN VEHICLE ON VEHICLE.type_id=VEHICLE_TYPE.type_id "
				+ "INNER JOIN RESERVATION ON RESERVATION.type_id=VEHICLE_TYPE.type_id "
				+ "INNER JOIN HOURLY_PRICE ON HOURLY_PRICE.type_id=VEHICLE_TYPE.type_id "
				+ "INNER JOIN LOCATION ON LOCATION.location_id=VEHICLE.location_id "
				+ "INNER JOIN CUSTOMER ON CUSTOMER.customer_id=RESERVATION.customer_id "
				+ "INNER JOIN USER ON USER.user_id=CUSTOMER.user_id";
		
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<VehicleType> vehicleTypes = new ArrayList<VehicleType>();
        condition.setLength( 0 );
        query.append( selectVehicleTypeQuery );
        System.out.println("query: "+ query.toString());
        
        if( modelVehicleType != null ) {
            if( modelVehicleType.getId() >= 0 ) // id is unique, so it is sufficient to get a person
                query.append( " where VEHICLE_TYPE.type_id = " + modelVehicleType.getId() );
            else if( modelVehicleType.getName() != null ) // userName is unique, so it is sufficient to get a person
                query.append( " where VEHICLE_TYPE.name = '" + modelVehicleType.getName() + "'" );
        }
        
        try {
            stmt = (Statement) con.createStatement();
            if( stmt.execute( query.toString() ) ) { // statement returned a result
                ResultSet rs = stmt.getResultSet();
                
                // VEHICLE_TYPE
                int   	type_type_id;
                String 	type_name;
                
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
				int 	vehicle_status = 0;
				int 	vehicle_cond = 0;
                
				// RESERVATION
				int 	reservation_reservation_id;
				int 	reservation_location_id;
				int 	reservation_type_id;
				int 	reservation_customer_id;
				Date 	reservation_pickupTime;
				int 	reservation_rentalLength;
				int		reservation_cancelled;
				
				// HOURLY_PRICE
				int		hourly_hourly_id;
				int		hourly_type_id;
				int		hourly_max_hrs;
				int		hourly_price;
				
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
	            int		customer_customer_id;
	            int		customer_user_id;
	            Date 	customer_memberUntil;
	            String 	customer_licState;
	            String 	customer_licNum;
	            String 	customer_ccNum;
	            Date 	customer_ccExp;
	            int 	customer_status;
				
                Vehicle vehicle = null;
                Reservation reservation = null;
                Customer customer = null;
                HourlyPrice hourlyPrice = null;
                RentalLocation rentalLocation = null;
                VehicleType vehicleType = null;
                VehicleStatus vehicleStatus = VehicleStatus.INLOCATION;
				VehicleCondition vehicleCondition = VehicleCondition.GOOD;
                
				while( rs.next() ){
					
					 // VEHICLE_TYPE
	                type_type_id = rs.getInt(1);
	                type_name = rs.getString(2);
	                
	                // VEHICLE
	                vehicle_vehicle_id = rs.getInt(3);
					vehicle_type_id = rs.getInt(4);
					vehicle_location_id = rs.getInt(5);
					vehicle_make = rs.getString(6);
					vehicle_model = rs.getString(7);
					vehicle_year = rs.getInt(8);
					vehicle_mileage = rs.getInt(9);
					vehicle_tag = rs.getString(10);
					vehicle_service_date = rs.getDate(11);
					vehicle_status = rs.getInt(12);
					if(vehicle_status == 1){
						vehicleStatus = VehicleStatus.INRENTAL;
					}
					vehicle_cond = rs.getInt(13);
					if(vehicle_cond == 1){
						vehicleCondition = VehicleCondition.NEEDSMAINTENANCE;
					}
					
					// RESERVATION
					reservation_reservation_id = rs.getInt(14);
					reservation_location_id = rs.getInt(15);
					reservation_type_id = rs.getInt(16);
					reservation_customer_id = rs.getInt(17);
					reservation_pickupTime = rs.getDate(18);
					reservation_rentalLength = rs.getInt(19);
					reservation_cancelled = rs.getInt(20);
					
					// HOURLY_PRICE
					hourly_hourly_id = rs.getInt(21);
					hourly_type_id = rs.getInt(22);
					hourly_max_hrs = rs.getInt(23);
					hourly_price = rs.getInt(24);
					
					// LOCATION
					location_location_id = rs.getInt(25);
					location_name = rs.getString(26);
					location_address = rs.getString(27);
					location_capacity = rs.getInt(28);

					// CUSTOMER
					user_user_id	= rs.getInt(29);
	           	 	user_fname = rs.getString(30);
	           	 	user_lname = rs.getString(31);
	           	 	user_uname = rs.getString(32);
	           	 	user_pword = rs.getString(33);
	           	 	user_email = rs.getString(34);
	           	 	user_address = rs.getString(35);
	           	 	user_createDate = rs.getDate(36);
	                customer_customer_id = rs.getInt(37);
	                customer_user_id = rs.getInt(38);
	                customer_memberUntil = rs.getDate(39);
	                customer_licState = rs.getString(40);
	                customer_licNum = rs.getString(41);
	                customer_ccNum = rs.getString(42);
	                customer_ccExp = rs.getDate(43);
	                customer_status = rs.getInt(44);
	
					
					// VEHICLE_TYPE
                    vehicleType = objectLayer.createVehicleType(type_name);
                    vehicleType.setId( type_type_id );
             
                    // LOCATION
                    rentalLocation = objectLayer.createRentalLocation(location_name, location_address, location_capacity);
                    rentalLocation.setId(location_location_id);

                    // VEHICLE
					vehicle = objectLayer.createVehicle(vehicle_make, vehicle_model, vehicle_year, vehicle_tag, vehicle_mileage, vehicle_service_date, vehicleType, rentalLocation, vehicleCondition, vehicleStatus);
					vehicle.setId(vehicle_vehicle_id);
                    vehicleType.setVehicles(vehicle);
                    
                    // CUSTOMER
                    customer = objectLayer.createCustomer(user_fname, user_lname, user_uname, user_pword, user_email, user_address, user_createDate, customer_memberUntil, customer_licState, customer_licNum, customer_ccNum, customer_ccExp);
                    customer.setId(customer_customer_id);

					// RESERVATION
					reservation = objectLayer.createReservation(reservation_pickupTime, reservation_rentalLength, vehicleType, rentalLocation, customer);
					reservation.setId(reservation_reservation_id);
                    vehicleType.setReservations(reservation);

					// HOURLY_PRICES
                    hourlyPrice = objectLayer.createHourlyPrice(hourly_max_hrs, hourly_price, vehicleType);
                    vehicleType.setHourlyPrices(hourlyPrice);
					vehicleTypes.add( vehicleType );
                }
                return vehicleTypes;
            }
        }
        catch( Exception e ) {      // just in case...
            throw new RARException( "VehicleTypeManager.restore: Could not restore persistent VehicleType object; Root cause: " + e );
        }
        
        // if we get to this point, it's an error
        throw new RARException( "VehicleTypeManager.restore: Could not restore persistent VehicleType objects" );
	}
	
	
    public void delete( VehicleType vehicleType ) throws RARException{
    	String deleteVehicleT = "DELETE FROM VEHICLE_TYPE WHERE type_id = ?";              
		PreparedStatement stmt = null;
		int inscnt = 0;
		             
        if( !vehicleType.isPersistent() )
            return;
        
        try {
            stmt = (PreparedStatement) con.prepareStatement( deleteVehicleT );         
            stmt.setLong( 1, vehicleType.getId() );
            inscnt = stmt.executeUpdate();          
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new RARException( "VehicleTypeManager.delete: failed to delete a vehicleType" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "VehicleTypeManager.delete: failed to delete a vehicleType: " + e );       
            }
    }
    
    
    /*
     * 
     * HourlyPrice @ VehicleType
     * 
     */
    public void storePrice(VehicleType vehicleType, HourlyPrice hourlyPrice) throws RARException {
		// TODO
	}
    
    public VehicleType restorePrice(HourlyPrice hourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}
    
    public List<HourlyPrice> restorePrice(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}
    
    public void deletePrice(VehicleType vehicleType, HourlyPrice hourlyPrice) throws RARException {
		// TODO Auto-generated method stub
	}
}
