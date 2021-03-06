package edu.uga.cs.rentaride.test.object;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class RentARideTester
{
    public static void main(String[] args)
    {
         Connection  con = null;
         ObjectLayer objectLayer = null;
         PersistenceLayer persistence = null;

         Administrator  admin1;
         Administrator  admin2;
         VehicleType    truckVehicleType;
         VehicleType	convertibleVehicleType;
         HourlyPrice	truckHourlyPrice1;
         HourlyPrice	truckHourlyPrice2;
         HourlyPrice	convertibleHourlyPrice1;
         HourlyPrice	convertibleHourlyPrice2;
         Customer       customerMichael;
         Customer       customerLuke;
         RentalLocation	rentalLocationAtlanta;
         RentalLocation	rentalLocationAthens;
         Vehicle 		truck1;
         Vehicle		truck2;
         Vehicle		convertible1;
         Vehicle		convertible2;
         Reservation 	reservation1;
         Reservation 	reservation2;
         Reservation 	reservation3;
         Reservation 	reservation4;

         Rental			rental1;
         Rental			rental2;
         Comment		comment1;
         Comment		comment2;
         
         
         
         // get a database connection
         try {
             con = DbUtils.connect();
         } 
         catch (Exception seq) {
             System.err.println( "RentARideTester: Unable to obtain a database connection" );
         }
         
         if( con == null ) {
             System.out.println( "RentARideTester: failed to connect to the database" );
             return;
         }
         
         // obtain a reference to the ObjectModel module      
         objectLayer = new ObjectLayerImpl();
         // obtain a reference to Persistence module and connect it to the ObjectModel        
         persistence = new PersistenceLayerImpl( con, objectLayer ); 
         // connect the ObjectModel module to the Persistence module
         objectLayer.setPersistence( persistence ); 
         
         try {
        	 
        	 Date dateMemberTill = new Date();
        	 Date dateReservation1 = new Date();
        	 Date dateReservation2 = new Date();
        	 Date dateReservation3 = new Date();
        	 Date dateReservation4 = new Date();
        	 DateFormat df1 = new SimpleDateFormat( "yyyy-MM-dd" );
        	 dateMemberTill = df1.parse( "2017-10-05" );
        	 dateReservation1 = df1.parse( "2017-10-20" );
        	 dateReservation2 = df1.parse( "2017-10-22" );
        	 dateReservation3 = df1.parse( "2017-10-24" );
        	 dateReservation4 = df1.parse( "2017-10-30" );
        	 
        	 Date rentalPickup1 = new Date();
        	 Date rentalPickup2 = new Date();
        	 DateFormat df2 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        	 rentalPickup1 = df2.parse( "2017-10-20 17:00:00 " );
        	 rentalPickup2 = df2.parse( "2017-10-24 08:00:00 " );
        	 
        	 Date commentDate1 = new Date();
        	 Date commentDate2 = new Date();
        	 commentDate1 = df2.parse( "2017-10-21 15:00:00 " );
        	 commentDate2 = df2.parse( "2017-10-26 06:00:00 " );
        	 
        	 int lengthReservation1 = 24;
        	 int lengthReservation2 = 48;
        	 int lengthReservation3 = 72;
        	 int lengthReservation4 = 96;
        	 
        	 int maxHours = 96;

        	 
        	 // 2 ADMINS
        	 admin1 = objectLayer.createAdministrator("Alex", "White", "awhite", "password", "awhite@uga.com", "101 idk road", dateMemberTill);
             persistence.storeAdministrator( admin1 );
        	 
             admin2 = objectLayer.createAdministrator("Wayne", "Kung", "wayne", "password", "wayne@uga.com", "000 Hello St., Small Town, GA. 30129", dateMemberTill);
             persistence.storeAdministrator( admin2 );
             
        	 
        	 // 2 LOCATIONS
             rentalLocationAtlanta = objectLayer.createRentalLocation("Atlanta", "999 cool street, Atlanta, GA. 30101", 500);
             persistence.storeRentalLocation( rentalLocationAtlanta );
             
             rentalLocationAthens = objectLayer.createRentalLocation("Athens", "999 cool stree, Athens, GA. 30601", 250);
             persistence.storeRentalLocation( rentalLocationAthens );
        	 
        	 // 2 VEHICLE_TYPES
             truckVehicleType = objectLayer.createVehicleType("truck");
             persistence.storeVehicleType( truckVehicleType );
             
             convertibleVehicleType = objectLayer.createVehicleType("convertible");
             persistence.storeVehicleType( convertibleVehicleType );
        	 
             // 4 HOURLY PRICES
             truckHourlyPrice1 = objectLayer.createHourlyPrice(maxHours, 50, truckVehicleType);
             persistence.storeHourlyPrice( truckHourlyPrice1 );
             
             truckHourlyPrice2 = objectLayer.createHourlyPrice(maxHours, 75, truckVehicleType);
             persistence.storeHourlyPrice( truckHourlyPrice2 );
             
             convertibleHourlyPrice1 = objectLayer.createHourlyPrice(maxHours, 100, convertibleVehicleType);
             persistence.storeHourlyPrice( convertibleHourlyPrice1 );
             
             convertibleHourlyPrice2 = objectLayer.createHourlyPrice(maxHours, 150, convertibleVehicleType);
             persistence.storeHourlyPrice( convertibleHourlyPrice2 );
        	 
        	 // 4 VEHICLES
             truck1 = objectLayer.createVehicle("Chevrolet", "Avalanche", 2013, "111111111", 20000, dateMemberTill, truckVehicleType, rentalLocationAtlanta, VehicleCondition.GOOD, VehicleStatus.INLOCATION);
             persistence.storeVehicle( truck1 );
             
             truck2 = objectLayer.createVehicle("Toyota", "Tacoma", 2017, "222222222", 1000, dateMemberTill, truckVehicleType, rentalLocationAthens, VehicleCondition.GOOD, VehicleStatus.INLOCATION);
             persistence.storeVehicle( truck2) ;
        	 
             convertible1 = objectLayer.createVehicle("Honda", "Del Sol", 1997, "333333333", 120000, dateMemberTill, convertibleVehicleType, rentalLocationAtlanta, VehicleCondition.GOOD, VehicleStatus.INLOCATION);
             persistence.storeVehicle( convertible1 );
        	 
             convertible2 = objectLayer.createVehicle("Ford", "Mustang", 2017, "444444444", 2000, dateMemberTill, convertibleVehicleType, rentalLocationAthens, VehicleCondition.GOOD, VehicleStatus.INLOCATION);
             persistence.storeVehicle( convertible2 );
             
        	 // 2 CUSTOMERS
             customerMichael = objectLayer.createCustomer( "Michael", "Vincent", "atticus", "password", "mike@gmail.com", "12 Laurchris Drive SE, Rome, GA, 30161", dateMemberTill, dateMemberTill, "GA", "22222", "11111", dateMemberTill );
             persistence.storeCustomer( customerMichael );

             customerLuke = objectLayer.createCustomer( "Luke", "Robert", "blobby", "password", "luke@gmail.com", "999 Strickland Street, Medium Town, AZ. 87889", dateMemberTill, dateMemberTill, "GA", "22223", "1111", dateMemberTill );
             persistence.storeCustomer( customerLuke );
        	 
        	 // 4 RESERVATIONS
             reservation1 = objectLayer.createReservation(dateReservation1, lengthReservation1, truckVehicleType, rentalLocationAtlanta, customerMichael);
             persistence.storeReservation(reservation1);
             
             reservation2 = objectLayer.createReservation(dateReservation2, lengthReservation2, convertibleVehicleType, rentalLocationAthens, customerMichael);
             persistence.storeReservation(reservation2);
             
             reservation3 = objectLayer.createReservation(dateReservation3, lengthReservation3, truckVehicleType, rentalLocationAtlanta, customerLuke);
             persistence.storeReservation(reservation3);
             
             reservation4 = objectLayer.createReservation(dateReservation4, lengthReservation4, convertibleVehicleType, rentalLocationAthens, customerLuke);
             persistence.storeReservation(reservation4);
        	 
        	 // 2 RENTALS
             rental1 = objectLayer.createRental(rentalPickup1, reservation1, truck1);
             rental1.setCharges(100);
             
             rental2 = objectLayer.createRental(rentalPickup2, reservation3, convertible1);
             rental2.setCharges(200);
             
             persistence.storeRental(rental1);
             persistence.storeRental(rental2);
             
             // 2 COMMENTS
             comment1 = objectLayer.createComment("great experience", commentDate1, rental1);
             rental1.setComment(comment1);
             persistence.storeComment(comment1);
             
             comment2 = objectLayer.createComment("horrible experience", commentDate2, rental2);
             rental2.setComment(comment2);
             persistence.storeComment(comment2);
             
        	 // DELETE everything
        	 objectLayer.deleteAdministrator(admin1);
        	 objectLayer.deleteAdministrator(admin2);
        	 objectLayer.deleteCustomer(customerMichael);
        	 objectLayer.deleteCustomer(customerLuke);
        	 objectLayer.deleteVehicleType(truckVehicleType);
        	 objectLayer.deleteVehicleType(convertibleVehicleType);
        	 objectLayer.deleteRentalLocation(rentalLocationAthens);
        	 objectLayer.deleteRentalLocation(rentalLocationAtlanta);
         }
         
         catch (RARException re) {
        	 System.err.println("RARException: " + re);
        	 re.printStackTrace();
         }
         catch (Exception e) {
        	 System.err.println("Exception: " + e);
        	 e.printStackTrace();
         }
         finally {
        	 // close connection
        	 try {
        		 con.close();
                 System.out.println( "\n\nRentARideTester: Connection closed successfully.\n\n" );
        	 }
        	 catch( Exception e ) {
                 System.err.println( "Exception: " + e );
             }
         }
    }
}
