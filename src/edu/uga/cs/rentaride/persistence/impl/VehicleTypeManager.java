package edu.uga.cs.rentaride.persistence.impl;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

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
			
			System.out.println("query: "+pstmt.asSql());
            inscnt = pstmt.executeUpdate();
			
            if( !vehicleType.isPersistent() ) {
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
                            vehicleTypeId = r.getLong( 1 );
                            if( vehicleTypeId > 0 )
                            	vehicleType.setId( vehicleTypeId ); // set this person's db id (proxy object)
                        }
                    }
                }
            }
            
		} catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "VehicleType.save: failed to save a type: " + e );
		}
    }
    
	
	public List<VehicleType> restore( VehicleType modelVehicleType ) throws RARException{
		
		String       selectVehicleTypeQuery = 
				"SELECT type_id, name FROM VEHICLE_TYPE";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<VehicleType> vehicleTypes = new ArrayList<VehicleType>();

        condition.setLength( 0 );
        
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
                long   id;
                String name;
                
                while( rs.next() ) {

                    id = rs.getLong( 1 );
                    name = rs.getString( 2 );

                    VehicleType vehicleType = objectLayer.createVehicleType(name);
                    vehicleType.setId( id );
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
