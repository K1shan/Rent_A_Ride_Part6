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
        	 System.out.println("date" + mydate);
        	 
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
