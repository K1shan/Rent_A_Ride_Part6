package edu.uga.cs.rentaride.test.object;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

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
import edu.uga.cs.rentaride.object.impl.ObjectLayerImpl;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;
import edu.uga.cs.rentaride.persistence.impl.DbUtils;
import edu.uga.cs.rentaride.persistence.impl.PersistenceLayerImpl;

public class ReadTest
{
    public static void main(String[] args)
    {
         Connection  con = null;
         ObjectLayer objectLayer = null;
         PersistenceLayer persistence = null;

         // get a database connection
         try {
             con = DbUtils.connect();
         } 
         catch(Exception seq) {
             System.err.println( "ReadTest: Unable to obtain a database connection" );
         }
         
         if( con == null ) {
             System.out.println( "ReadTest: failed to connect to the database" );
             return;
         }
         
         // obtain a reference to the ObjectModel module      
         objectLayer = new ObjectLayerImpl();
         // obtain a reference to Persistence module and connect it to the ObjectModel        
         persistence = new PersistenceLayerImpl( con, objectLayer ); 
         // connect the ObjectModel module to the Persistence module
         objectLayer.setPersistence( persistence );   
                  
         try {
             
        	 List<Customer> customers = objectLayer.findCustomer( null );
             System.out.println( "Customer objects:" );
             for( Customer customer : customers) {
                 System.out.println( customer );
             }
             
             List<Administrator> administrators = objectLayer.findAdministrator(null);
             System.out.println( "Administrator objects:" );
             for (Administrator administrator : administrators) {
            	 System.out.println(administrator);
             }
             
             List<RentalLocation> rentalLocations = objectLayer.findRentalLocation( null );
             System.out.println( "RentalLocation objects:" );
             for (RentalLocation rentalLocation : rentalLocations) {
            	 System.out.println(rentalLocation);
             }
            
             List<VehicleType> vehicleTypes = objectLayer.findVehicleType( null );
             System.out.println( "VehicleType objects:" );
             for (VehicleType vehicleType : vehicleTypes) {
            	 System.out.println(vehicleType);
             }
             
             List<HourlyPrice> hourlyPrices = objectLayer.findHourlyPrice( null );
             System.out.println( "HourlyPrice objects:" );
             for (HourlyPrice hourlyPrice : hourlyPrices) {
            	 System.out.println(hourlyPrice);
             }
             
             List<Vehicle> vehicles = objectLayer.findVehicle( null );
             System.out.println( "Vehicle objects:" );
             for (Vehicle vehicle : vehicles) {
            	 System.out.println(vehicle);
             }
             
             // TODO
             // reservations
             
             
             
             // TODO
             // rentals
             
             
             
             // TODO
             // comments
             
             

         }
         catch( RARException ce)
         {
             System.err.println( "RARException: " + ce );
         }
         catch( Exception e)
         {
             System.out.flush();
             System.err.println( "Exception: " + e );
         }
         finally {
             // close the connection!!!
             try {
                 con.close();
             }
             catch( Exception e ) {
                 System.err.println( "Exception: " + e );
             }
         }
    }   
}
