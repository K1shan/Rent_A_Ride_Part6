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
         Vehicle        vehicle1;
         Customer       customer1;
         Customer       customer2;


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
            
            // TODO 
            // create a few customers
        	 
        	 
        	 Date mydate = new Date();
        	 mydate.getDate();
        	 
             customer1 = objectLayer.createCustomer( "alex1", "shit", "bird1", "password", "emailemail.com1", "133 Maple St., Big Town, AZ. 87888", mydate, mydate, "GA", "22222", "1111", mydate );
             persistence.storeCustomer( customer1 );

             customer2 = objectLayer.createCustomer( "alex2", "shit", "bird2", "password", "emailemail.com", "133 Maple St., Big Town, AZ. 87888", mydate, mydate, "GA", "22222", "1111", mydate );
             persistence.storeCustomer( customer2 );

            // TODO
            // create rental locations
       




            // TODO
            // create vehicle types




            // TODO
            // create vehicles
             



            // TODO
            // create 


             /*
             joe = objectLayer.createPerson( "joe", "joepass", "joe@mail.com", "Joe", "Doe", "133 Maple St., Big Town, AZ. 87888", "333-4456" );
             mary = objectLayer.createPerson( "mary", "marypass", "mary@mail.com", "Mary", "Swift", "14 Oak Dr., Small Town, TX. 77888", "444-9876" );
             bob = objectLayer.createPerson( "bob", "bobpass", "bob@mail.com", "Robert", "Wilson", "33 Cedar Cr., Middle Town, NV. 81888", "567-7788" );
             julie = objectLayer.createPerson( "julie", "juliepass", "julie@mail.com", "Julie", "Hart", "99 Magnolia St., Splash Town, NY. 21888", "364-7592" );
             heather = objectLayer.createPerson( "heather", "heatherpass", "julie@mail.com", "Heather", "Brooks", "1 Pine Ave., Boom Town, GA. 30688", "339-9923" );
             
             persistence.storePerson( joe );
             persistence.storePerson( mary );
             persistence.storePerson( bob );
             persistence.storePerson( julie );
             persistence.storePerson( heather );


              bridge = objectLayer.createClub( "Bridge", "33 Leaf St., Blossom, OR. 88888", new Date(), joe );
             persistence.storeClub( bridge );
             
             chess = objectLayer.createClub( "Chess", "734 Pine Straw Dr., Bloom, KY. 48878", new Date(), mary );
             persistence.storeClub( chess );
             
             tennis = objectLayer.createClub( "Tennis", "333 Wide St., Flower, RI. 17345", new Date(), mary );
             persistence.storeClub( tennis );
             
             running = objectLayer.createClub( "Running", "445 Pace St., Quicker, Wy. 77546", new Date(), bob );
             persistence.storeClub( running );


             membership = objectLayer.createMembership( joe, bridge, new Date() );
             persistence.storeMembership( membership );
             
             membership = objectLayer.createMembership( bob, bridge, new Date() );
             persistence.storeMembership( membership );
             
             membership = objectLayer.createMembership( heather, bridge, new Date() );
             persistence.storeMembership( membership );
             
             membership = objectLayer.createMembership( mary, chess, new Date() );
             persistence.storeMembership( membership );
             
             membership = objectLayer.createMembership( mary, tennis, new Date() );
             persistence.storeMembership( membership );
             
             membership = objectLayer.createMembership( julie, tennis, new Date() );
             persistence.storeMembership( membership );
             
             membership = objectLayer.createMembership( bob, tennis, new Date() );
             persistence.storeMembership( membership );
             
             membership = objectLayer.createMembership( joe, chess, new Date() );
             persistence.storeMembership( membership );
             */

             System.out.println( "Entity objects created and saved in the persistence module" );
             
         }
         catch( Exception e ) {
             e.printStackTrace();
         }
         finally {
             // close the connection
             try {
                 con.close();
             }
             catch( Exception e ) {
                 System.err.println( "Exception: " + e );
             }
         }
    }  
}
