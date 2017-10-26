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

public class UpdateTest
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
         catch (Exception seq) {
             System.err.println( "UpdateTest: Unable to obtain a database connection" );
         }
         
         if( con == null ) {
             System.out.println( "UpdateTest: failed to connect to the database" );
             return;
         }
         
         // obtain a reference to the ObjectModel module      
         objectLayer = new ObjectLayerImpl();
         // obtain a reference to Persistence module and connect it to the ObjectModel        
         persistence = new PersistenceLayerImpl( con, objectLayer ); 
         // connect the ObjectModel module to the Persistence module
         objectLayer.setPersistence( persistence ); 
         

         //Club tennisClub = null;
		
         
         List<Vehicle> vehicles = null;
         List<Customer> customers= null;
         List<RentalLocation> locations = null;
         
         Iterator<Vehicle> vehicleIter = null;
         Iterator<Customer> customerIter = null;
         Iterator<RentalLocation> locationIter = null;
         
         Vehicle vehicleCheck = null;
         Customer customerAlex = null;
         RentalLocation locationAttributeName = null;
         RentalLocation locationAttributeAddress = null;
         RentalLocation locationAttributeCapacity = null;
         
         try {
        	 
        	 Customer modelCustomer = objectLayer.createCustomer();
        	 modelCustomer.setFirstName("Alex");
        	 customers = objectLayer.findCustomer(modelCustomer);
        	 customerIter = customers.iterator();
        	 
        	 System.out.println("\nMatching customer objects\n");
        	 while(customerIter.hasNext()){
        		 customerAlex = customerIter.next();
        		 System.out.println(customerAlex);
        	 }
        	 
        	 RentalLocation modelLocation1 = objectLayer.createRentalLocation();
        	 RentalLocation modelLocation2 = objectLayer.createRentalLocation();
        	 RentalLocation modelLocation3 = objectLayer.createRentalLocation();

        	 modelLocation1.setName("Atlanta");
        	 modelLocation2.setAddress("999 cool street");
        	 modelLocation3.setCapacity(400);
        	 
        	 locations = objectLayer.findRentalLocation(modelLocation1);
        	 locationIter = locations.iterator();
        	 System.out.println("\nMatching location name objects\n");
        	 while(locationIter.hasNext()){
        		 locationAttributeName = locationIter.next();
        		 System.out.println(locationAttributeName);
        	 }
        	 
        	 locations = objectLayer.findRentalLocation(modelLocation2);
        	 locationIter = locations.iterator();
        	 System.out.println("\nMatching location address objects\n");
        	 while(locationIter.hasNext()){
        		 locationAttributeAddress = locationIter.next();
        		 System.out.println(locationAttributeAddress);
        	 }
        	 
        	 locations = objectLayer.findRentalLocation(modelLocation3);
        	 locationIter = locations.iterator();
        	 System.out.println("\nMatching location capacity objects\n");
        	 while(locationIter.hasNext()){
        		 locationAttributeCapacity = locationIter.next();
        		 System.out.println(locationAttributeCapacity);
        	 }
        	 
//        	 Vehicle modelVehicle = objectLayer.createRentalLocation();
//        	 modelLocation.setName("Atlanta");
//        	 locations = objectLayer.findRentalLocation(modelLocation);
//        	 locationIter = locations.iterator();
//        	 
//        	 System.out.println("\nMatching location objects\n");
//        	 while(locationIter.hasNext()){
//        		 locationAtlanta = locationIter.next();
//        		 System.out.println(locationAtlanta);
//        	 }
//             
//        	 List<Rental> rentals2 = objectLayer.findRental(myrental);
//        	 System.out.println( "\n\nSELECT NULL TEST" );
//             for (Rental rental : rentals2) {
//            	 System.out.println(rental);
//             }
        	 
//        	 Vehicle modelVehicle = objectLayer.createVehicle();
//        	 modelVehicle.setMake("Honda");
//        	 vehicles = objectLayer.findVehicle(modelVehicle);
//        	 vehicleIter = vehicles.iterator();
//        	 while( vehicleIter.hasNext() ){
//        		 hondaVehicle = vehicleIter.next();
//        		 System.out.println(hondaVehicle);
//        		 RentalLocation rentalLocation = hondaVehicle.getRentalLocation();
//        		 System.out.println("	Located at: " + rentalLocation );
//        	 }
//        	 
//        	 if( hondaVehicle == null ){
//        		 System.out.println( "Honda vehicle does not exist" );
//        		 return;
//        	 }
//        	 
//        	 hondaVehicle.setMake( "Super Honda" );
//        	 objectLayer.storeVehicle(hondaVehicle);
//        	 System.out.println( "Updated the name of the Honda make to Super Honda" );
//         
//        	 Customer alex = null;
//        	 Customer modelCustomer = objectLayer.createCustomer();
//        	 modelCustomer.setFirstName("Alex");
//        	 modelCustomer.setLastName("White");
//        	 List<Customer> customers = objectLayer.findCustomer(modelCustomer);
//        	 Iterator<Customer> customerIter = customers.iterator();
//        	 while(customerIter.hasNext()){
//        		 alex = customerIter.next();
//        		 System.out.println( alex );
//        		 System.out.println( "	Reservation at: " );
//        		 System.out.println(alex.getReservations());
//        	 }
         }
         catch( RARException ce)
         {
             System.err.println( "RARException: " + ce );
         }
         catch( Exception e)
         {
             System.err.println( "Exception: " + e );
         }
         finally {
             // close the connection
             try {
                 con.close();
                 System.out.println( "\n\nUpdateTest.java: Connection closed successfully.\n\n" );
             }
             catch( Exception e ) {
                 System.err.println( "Exception: " + e );
             }
         }   
         
    }    
}
