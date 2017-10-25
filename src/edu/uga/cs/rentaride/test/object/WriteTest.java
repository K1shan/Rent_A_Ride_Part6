package edu.uga.cs.rentaride.test.object;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;

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

public class WriteTest
{
    public static void main(String[] args) throws RARException
    {
         Connection con = null;
         ObjectLayer objectLayer = null;
         PersistenceLayer persistence = null;
         
         Administrator  admin1;
         Administrator  admin2;
         VehicleType    truckVehicleType;
         VehicleType	convertibleVehicleType;
         HourlyPrice	truckHourlyPrice1;
         Customer       customer1;
         Customer       customer2;
         RentalLocation	rentalLocation1;
         Vehicle 		vehicle1;
         Reservation 	reservation1;
         Rental			rental1;
         Comment		comment1;


         /*
         * MORE CLASSES HERE
         */

         
         // get a database connection
         try {
             con = DbUtils.connect();
         } 
         catch (Exception seq) {
             System.err.println( "WriteTest: Unable to obtain a database connection" );
         }
         
         if( con == null ) {
             System.out.println( "WriteTest: failed to connect to the database" );
             return;
         }
         
         // obtain a reference to the ObjectModel module      
         objectLayer = new ObjectLayerImpl();
         // obtain a reference to Persistence module and connect it to the ObjectModel        
         persistence = new PersistenceLayerImpl( con, objectLayer ); 
         // connect the ObjectModel module to the Persistence module
         objectLayer.setPersistence( persistence );   

         try {
            
        	 Date mydate = new Date();
        	 mydate.getDate();
        	 
        	 // create 2 users
             customer1 = objectLayer.createCustomer( "alex1", "shit", "bird1", "password", "emailemail.com1", "133 Maple St., Big Town, AZ. 87888", mydate, mydate, "GA", "22222", "1111", mydate );
             persistence.storeCustomer( customer1 );

             customer2 = objectLayer.createCustomer( "alex2", "shit", "bird2", "password", "emailemail.com", "133 Maple St., Big Town, AZ. 87888", mydate, mydate, "GA", "22222", "1111", mydate );
             persistence.storeCustomer( customer2 );
           
             // create 2 admins
             admin1 = objectLayer.createAdministrator("Wayne", "Kung", "wayne", "password", "wayne@email.com", "000 Hello St., Small Town, GA. 30129", mydate);
             persistence.storeAdministrator(admin1);
             
             admin2 = objectLayer.createAdministrator("AdminFirstName", "AdminLastName", "admin2user", "password", "email@email.com", "111 Goodbye St., Big Town, GA. 30129", mydate);
             persistence.storeAdministrator(admin2);
            
             // create rental locations
             rentalLocation1 = objectLayer.createRentalLocation("atlanta", "999 cool street", 500);
             persistence.storeRentalLocation(rentalLocation1);

             // create vehicle types
             truckVehicleType = objectLayer.createVehicleType("truck");
             persistence.storeVehicleType(truckVehicleType);
             
             convertibleVehicleType = objectLayer.createVehicleType("convertible");
             persistence.storeVehicleType(convertibleVehicleType);
             
             // create hourly prices
             truckHourlyPrice1 = objectLayer.createHourlyPrice(20, 50, truckVehicleType);
             persistence.storeHourlyPrice(truckHourlyPrice1);

             // create vehicles
             vehicle1 = objectLayer.createVehicle("honda", "civic", 2012, "123456789", 20000, mydate, truckVehicleType, rentalLocation1, VehicleCondition.GOOD, VehicleStatus.INLOCATION);
             persistence.storeVehicle(vehicle1);
            		
             // create reservations
             reservation1 = objectLayer.createReservation(mydate, 200, truckVehicleType, rentalLocation1, customer1);
             persistence.storeReservation(reservation1);
             
             // create rentals
             rental1 = objectLayer.createRental(mydate, reservation1, vehicle1);
             persistence.storeRental(rental1);
             
             // create comments
             comment1 = objectLayer.createComment("great experience", mydate, rental1);
             persistence.storeComment(comment1);
             
             

             System.out.println( "Entity objects created and saved in the persistence module" );
             
         }
         catch( Exception e ) {
             e.printStackTrace();
         }
         finally {
             // close the connection
             try {
                 con.close();
                 System.out.println( "\n\nWriteTest.java: Connection closed successfully.\n\n" );
             }
             catch( Exception e ) {
                 System.err.println( "Exception: " + e );
             }
         }
    }  
}
