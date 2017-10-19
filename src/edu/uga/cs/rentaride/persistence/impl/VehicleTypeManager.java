package edu.uga.cs.rentaride.persistence.impl;


import java.sql.Connection;
import java.util.List;

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
    	// TODO
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
