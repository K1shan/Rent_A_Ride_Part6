package edu.uga.cs.rentaride.test.object;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
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

public class WriteTest
{
    public static void main(String[] args) throws RARException
    {
         Connection con = null;
         ObjectLayer objectLayer = null;
         PersistenceLayer persistence = null;
         
         Administrator  admin1;
         Administrator  admin2;
         Administrator  admin3;
         Administrator  admin4;
         Administrator  admin5;

         VehicleType    truckVehicleType;
         VehicleType	convertibleVehicleType;
         
         HourlyPrice	truckHourlyPrice1;
         
         Customer       customer1;
         Customer       customer2;
         Customer       customer3;
         Customer       customer4;
         Customer       customer5;

         RentalLocation	rentalLocation1;
         RentalLocation	rentalLocation2;
         RentalLocation	rentalLocation3;
         RentalLocation	rentalLocation4;
         RentalLocation	rentalLocation5;

         Vehicle 		vehicle1;
         Vehicle 		vehicle2;
         Vehicle 		vehicle3;
         Vehicle 		vehicle4;
         Vehicle 		vehicle5;
         
         Reservation 	reservation1;
         Reservation 	reservation2;
         Reservation 	reservation3;
         Reservation 	reservation4;

         Rental			rental1;
         Rental			rental2;
         
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
             customer1 = objectLayer.createCustomer( "Alex", "shit", "bird1", "password", "email1@uga.edu", "133 Maple St., Big Town, AZ. 87888", mydate, mydate, "GA", "222221", "11111", mydate );
             customer2 = objectLayer.createCustomer( "Alex", "shit", "bird2", "password", "email2@uga.edu", "133 Maple St., Big Town, AZ. 87888", mydate, mydate, "GA", "222222", "11112", mydate );
             customer3 = objectLayer.createCustomer( "Wayne", "shit", "bird3", "password", "email3@uga.edu", "133 Maple St., Big Town, AZ. 87888", mydate, mydate, "GA", "222223", "11113", mydate );
             customer4 = objectLayer.createCustomer( "Wayne", "shit", "bird4", "password", "email4@uga.edu", "133 Maple St., Big Town, AZ. 87888", mydate, mydate, "GA", "222224", "11114", mydate );
             customer5 = objectLayer.createCustomer( "Mike", "shit", "bird5", "password", "email5@uga.edu", "133 Maple St., Big Town, AZ. 87888", mydate, mydate, "GA", "222225", "11115", mydate );
             
             persistence.storeCustomer( customer1 );
             persistence.storeCustomer( customer2 );
             persistence.storeCustomer( customer3 );
             persistence.storeCustomer( customer4 );
             persistence.storeCustomer( customer5 );

             // create 2 admins
             admin1 = objectLayer.createAdministrator("Admin1", "Kung", "wayne1", "password", "email6@uga.edu", "000 Hello St., Small Town, GA. 30129", mydate);             
             admin2 = objectLayer.createAdministrator("Admin1", "LAST", "admin2user", "password", "email7@uga.edu", "111 Goodbye St., Big Town, GA. 30129", mydate);
             admin3 = objectLayer.createAdministrator("Wayne1", "Kung", "wayne3", "password", "email8@uga.edu", "000 Hello St., Small Town, GA. 30129", mydate);             
             admin4 = objectLayer.createAdministrator("Wayne1", "Kung", "wayne4", "password", "email9@uga.edu", "000 Hello St., Small Town, GA. 30129", mydate);             
             admin5 = objectLayer.createAdministrator("Wayne2", "Kung", "wayne5", "password", "email10@uga.edu", "000 Hello St., Small Town, GA. 30129", mydate);             
             
             persistence.storeAdministrator(admin1);
             persistence.storeAdministrator(admin2);
             persistence.storeAdministrator(admin3);
             persistence.storeAdministrator(admin4);
             persistence.storeAdministrator(admin5);

             // create rental locations
             rentalLocation1 = objectLayer.createRentalLocation("Atlanta", "999 cool street", 400);
             rentalLocation2 = objectLayer.createRentalLocation("Atlantis", "999 cool2 street", 400);
             rentalLocation3 = objectLayer.createRentalLocation("Miami", "999 cool2 street", 400);
             rentalLocation4 = objectLayer.createRentalLocation("Tampa", "999 cool street", 500);
             rentalLocation5 = objectLayer.createRentalLocation("Savannah", "999 cool street", 500);

             persistence.storeRentalLocation(rentalLocation1);
             persistence.storeRentalLocation(rentalLocation2);
             persistence.storeRentalLocation(rentalLocation3);
             persistence.storeRentalLocation(rentalLocation4);
             persistence.storeRentalLocation(rentalLocation5);

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
             
            
             System.out.println("");
             List<Reservation> reservations = customer1.getReservations();
             System.out.println( "\nReservation objects LIST:" );
             for (Reservation reservation : reservations) {
            	 System.out.println(reservation);
             }
             //System.out.println("\n\nAlex Reservations:\t" + customer1.getReservations());
        	 //System.out.println("Alex Rentals:\t\t" + customer1.getRentals());
             
             
             

             System.out.println( "\nEntity objects created and saved in the persistence module" );
             
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
