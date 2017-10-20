package edu.uga.cs.rentaride.persistence.impl;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.HourlyPrice;
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
	public List<VehicleType> restore( VehicleType modelVehicleType ) throws RARException{
		// TODO
		return null;
	}

    public void store( VehicleType vehicleType ) throws RARException{
    	
    	boolean persist = false;
    	
    	String vehicleTypeInsertQuery = 
				"INSERT INTO VEHICLE_TYPE "
				+ "(name) "
				+ "VALUES "
				+ "(?)";
    	
    	String updateVehicleTypeQuery = 
				"UPDATE VEHICLE_TYPE SET "
				+ "name = ?"
				+ "WHERE type_id=?"; 
    	
    	PreparedStatement pstmt;
		int inscnt;
		
		try{
			
			if( !vehicleType.isPersistent() ){
				persist = false;
                pstmt = (PreparedStatement) con.prepareStatement( vehicleTypeInsertQuery );
			}else{
				persist = true;
                pstmt = (PreparedStatement) con.prepareStatement( updateVehicleTypeQuery );
			}
			
			if( vehicleType.getName() != null )
                pstmt.setString( 1, vehicleType.getName());
            else{
                throw new RARException( "CustomerManager.save: can't save a user: FirstName undefined" );
            }
			
			System.out.println("query: "+pstmt.asSql());
            inscnt = pstmt.executeUpdate();
			
		} catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "VehicleType.save: failed to save a type: " + e );
		}
		
    	
    }
    
    public void delete( VehicleType vehicleType ) throws RARException{
    	// TODO
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
