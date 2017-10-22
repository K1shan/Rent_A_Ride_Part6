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
	public void store( Vehicle vehicle ) throws RARException{
    	// TODO
		
		String insertVehicleQuery = 
				"INSERT INTO VEHICLE "
				+ "(type_id, location_id, make, model, year, mileage, tag, service_date, status, cond) "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	
    	String updateVehicleQuery = 
				"UPDATE VEHICLE SET "
				+ "name = ?"
				+ "WHERE type_id=?"; 
    	
    	PreparedStatement pstmt;
    	long vehicleId;
		int inscnt;
		
		try {
			if( !vehicle.isPersistent() ){
	            pstmt = (PreparedStatement) con.prepareStatement( insertVehicleQuery );
			}else{
	            pstmt = (PreparedStatement) con.prepareStatement( updateVehicleQuery );
			}
			
			if( vehicle.getVehicleType().getId() != 0 )
	            pstmt.setLong( 1, vehicle.getVehicleType().getId() );
	        else{
	            throw new RARException( "VehicleManager.save: can't save a vehicle: vehicletype undefined" );
	        }
			
			if( vehicle.getRentalLocation().getId() != 0 )
	            pstmt.setLong( 2, vehicle.getRentalLocation().getId() );
	        else{
	            throw new RARException( "VehicleManager.save: can't save a vehicle: location undefined" );
	        }
			
			if( vehicle.getMake() != null )
	            pstmt.setString( 3, vehicle.getMake() );
	        else{
	            throw new RARException( "VehicleManager.save: can't save a vehicle: make undefined" );
	        }
			
			if( vehicle.getModel() != null )
	            pstmt.setString( 4, vehicle.getModel() );
	        else{
	            throw new RARException( "VehicleManager.save: can't save a vehicle: model undefined" );
	        }
			
			if( vehicle.getYear() != 0 )
	            pstmt.setLong( 5, vehicle.getYear() );
	        else{
	            throw new RARException( "VehicleManager.save: can't save a vehicle: year undefined" );
	        }
			
			if( vehicle.getMileage() != 0 )
	            pstmt.setLong( 6, vehicle.getMileage() );
	        else{
	            throw new RARException( "VehicleManager.save: can't save a vehicle: mileage undefined" );
	        }
			
			if( vehicle.getRegistrationTag() != null )
	            pstmt.setString( 7, vehicle.getRegistrationTag() );
	        else{
	            throw new RARException( "VehicleManager.save: can't save a vehicle: tag undefined" );
	        }
			
			java.util.Date myDate = vehicle.getLastServiced();
        	java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
    		pstmt.setDate(8, sqlDate);
			
    		if( vehicle.getStatus() != null )
	            pstmt.setLong( 9, 0 );
	        else{
	            throw new RARException( "VehicleManager.save: can't save a vehicle: status undefined" );
	        }
    		
    		if( vehicle.getCondition() != null )
    			pstmt.setLong(10, 0);
    		else{
    			throw new RARException( "VehicleManager.save: can't save a vehicle: condition undefined" );
    		}
			
			System.out.println("query: "+pstmt.asSql());
	        inscnt = pstmt.executeUpdate();
	        
	        if( !vehicle.isPersistent() ) {
                // in case this this object is stored for the first time,
                // we need to establish its persistent identifier (primary key)
                if( inscnt == 1 ) {
                    String sql = "select last_insert_id()";
                    if( pstmt.execute( sql ) ) { // statement returned a result
                        // retrieve the result
                        ResultSet r = pstmt.getResultSet();
                        // we will use only the first row!
                        while( r.next() ) {
                            // retrieve the last insert auto_increment value
                            vehicleId = r.getLong( 1 );
                            if( vehicleId > 0 )
                            	vehicle.setId( vehicleId ); // set this person's db id (proxy object)
                        }
                    }
                }
            }
	        
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RARException( "Vehicle.save: failed to save a vehicle: " + e );
		}
	}
	
	public List<Vehicle> restore( Vehicle modelVehicle ) throws RARException{
		// TODO
		return null;
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
