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
		
		String       selectVehicleTypeQuery = 
				"SELECT VEHICLE_TYPE.type_id, VEHICLE_TYPE.name, VEHICLE.*, RESERVATION.* name FROM VEHICLE_TYPE "
				+ "INNER JOIN VEHICLE ON VEHICLE.type_id=VEHICLE_TYPE.type_id "
				+ "INNER JOIN RESERVATION ON RESERVATION.type_id=VEHICLE_TYPE.type_id";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<VehicleType> vehicleTypes = new ArrayList<VehicleType>();

        condition.setLength( 0 );
        System.out.println("query: "+selectVehicleTypeQuery);
        
        // form the query based on the given Person object instance
        query.append( selectVehicleTypeQuery );
        
        if( modelVehicleType != null ) {
            if( modelVehicleType.getId() >= 0 ) // id is unique, so it is sufficient to get a person
                query.append( " where VEHICLE_TYPE.type_id = " + modelVehicleType.getId() );
            else if( modelVehicleType.getName() != null ) // userName is unique, so it is sufficient to get a person
                query.append( " where VEHICLE_TYPE.name = '" + modelVehicleType.getName() + "'" );
            else {
            	if( condition.length() > 0 ) {
                    query.append(  " where " );
                    query.append( condition );
                }
            }
        }
        
        try {

            stmt = (Statement) con.createStatement();

            // retrieve the persistent Person objects
            //
            if( stmt.execute( query.toString() ) ) { // statement returned a result
                ResultSet rs = stmt.getResultSet();
                
                // VEHICLE_TYPE
                int   type_id;
                String name;
                
                // VEHICLE
                int vehicle_id;
				int vehicle_type_id;
				int location_id;
				String make;
				String model;
				int year;
				int mileage;
				String tag;
				Date service_date;
				int status = 0;
				int cond = 0;
                
                // RESERVATION
				int reservation_id;
				int reservation_location_id;
				int reservation_type_id;
				int customer_id;
				Date pickupTime;
				int rentalLength;
				
				
                Vehicle vehicle = null;
                Reservation reservation = null;
                Customer customer = null;
                VehicleStatus vehicleStatus = VehicleStatus.INLOCATION;
				VehicleCondition vehicleCondition = VehicleCondition.GOOD;
                
                while( rs.next() ) {

                	// VEHICLE_TYPE
                    type_id = rs.getInt( 1 );
                    name = rs.getString( 2 );
                    
                    // VEHICLE
                    vehicle_id = rs.getInt(3);
					vehicle_type_id = rs.getInt(4);
					location_id = rs.getInt(5);
					make = rs.getString(6);
					model = rs.getString(7);
					year = rs.getInt(8);
					mileage = rs.getInt(9);
					tag = rs.getString(10);
					service_date = rs.getDate(11);
					status = rs.getInt(12);
					if(status == 1){
						vehicleStatus = VehicleStatus.INRENTAL;
					}
					cond = rs.getInt(13);
					if(cond == 1){
						vehicleCondition = VehicleCondition.NEEDSMAINTENANCE; 
					}
					
					// RESERVATION
					reservation_id = rs.getInt(14);
					reservation_location_id = rs.getInt(15);
					reservation_type_id = rs.getInt(16);
					customer_id = rs.getInt(17);
					pickupTime = rs.getDate(18);
					rentalLength = rs.getInt(19);

					
					// VEHICLE_TYPE
                    VehicleType vehicleType = objectLayer.createVehicleType(name);
                    vehicleType.setId( type_id );
                    
                    
                    // VEHICLE
					vehicle = objectLayer.createVehicle(make, model, year, tag, mileage, service_date, vehicleType, null, vehicleCondition, vehicleStatus);
					vehicle.setId(vehicle_id);
					
					// RESERVATION
					reservation = objectLayer.createReservation(pickupTime, rentalLength, vehicleType, null, customer);
					reservation.setId(reservation_id);
					
					//reservation.setRentalLocation(rentalLocation);
					//reservation.setCustomer(customer);
					
					
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
