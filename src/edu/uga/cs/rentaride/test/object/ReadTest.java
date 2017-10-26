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
             
        	 // select all customers
        	 System.out.println("");
        	 List<Customer> customers = objectLayer.findCustomer( null );
             System.out.println( "\nCustomer objects:" );
             for( Customer customer : customers) {
                 System.out.println( customer );
             }
             
             // select all administrators
             System.out.println("");
             List<Administrator> administrators = objectLayer.findAdministrator(null);
             System.out.println( "\nAdministrator objects:" );
             for (Administrator administrator : administrators) {
            	 System.out.println(administrator);
             }
             
             //select all rentalLocations
             System.out.println("");
             List<RentalLocation> rentalLocations = objectLayer.findRentalLocation( null );
             System.out.println( "\nRentalLocation objects:" );
             for (RentalLocation rentalLocation : rentalLocations) {
            	 System.out.println(rentalLocation);
             }
            
             // select all vehicleTypes
             System.out.println("");
             List<VehicleType> vehicleTypes = objectLayer.findVehicleType( null );
             System.out.println( "\nVehicleType objects:" );
             for (VehicleType vehicleType : vehicleTypes) {
            	 System.out.println(vehicleType);
             }
             
             // select all hourlyPrices
             System.out.println("");
             List<HourlyPrice> hourlyPrices = objectLayer.findHourlyPrice( null );
             System.out.println( "\nHourlyPrice objects:" );
             for (HourlyPrice hourlyPrice : hourlyPrices) {
            	 System.out.println(hourlyPrice);
             }
             
             // select all vehicles
             System.out.println("");
             List<Vehicle> vehicles = objectLayer.findVehicle( null );
             System.out.println( "\nVehicle objects:" );
             for (Vehicle vehicle : vehicles) {
            	 System.out.println(vehicle);
             }
             
             // select all reservations
             System.out.println("");
             List<Reservation> reservations = objectLayer.findReservation( null );
             System.out.println( "\nReservation objects:" );
             for (Reservation reservation : reservations) {
            	 System.out.println(reservation);
             }
             
             // select all rentals
             System.out.println("");
             List<Rental> rentals = objectLayer.findRental( null );
             System.out.println( "\nRental objects:" );
             for (Rental rental : rentals) {
            	 System.out.println(rental);
             }
             
             // select all comments
             System.out.println("");
             List<Comment> comments = objectLayer.findComment( null );
             System.out.println( "\nComment objects:" );
             for (Comment comment : comments) {
            	 System.out.println(comment);
             }
             
             
             

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
                 System.out.println( "\n\nReadTest.java: Connection closed successfully.\n\n" );
             }
             catch( Exception e ) {
                 System.err.println( "Exception: " + e );
             }
         }
    }   
}
