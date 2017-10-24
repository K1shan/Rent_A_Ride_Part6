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

public class DeleteTest
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
             System.err.println( "DeleteTest: Unable to obtain a database connection" );
         }
         
         if( con == null ) {
             System.out.println( "DeleteTest: failed to connect to the database" );
             return;
         }
         
         // obtain a reference to the ObjectModel module      
         objectLayer = new ObjectLayerImpl();
         // obtain a reference to Persistence module and connect it to the ObjectModel        
         persistence = new PersistenceLayerImpl( con, objectLayer ); 
         // connect the ObjectModel module to the Persistence module
         objectLayer.setPersistence( persistence ); 
         

         //TODO

         try {
        	 
        	// find administrator
        	 Administrator wayne = null;
             Administrator modelAdmin = objectLayer.createAdministrator();
             modelAdmin.setUserName("wayne");
             List<Administrator> administrators = objectLayer.findAdministrator(modelAdmin);
             Iterator<Administrator> adminIter = administrators.iterator();
             while(adminIter.hasNext()){
            	wayne = adminIter.next();
            	if(wayne == null ) {
                    System.out.println( "Administrator with username " + modelAdmin.getUserName() +" does not exist" );
                    return;
                }
            	// the guidelines only say to delete, so I didn't bother printing out all the info like the example did
             }
             
             //delete administrator
             if (wayne!=null) {
            	 objectLayer.deleteAdministrator(wayne);
            	 System.out.println("Deleted " + modelAdmin.getUserName());
             }
             else
            	 System.out.println("Failed to find " + modelAdmin.getUserName()); 
         }
         
         catch (RARException re)
         {
        	 System.err.println("RARException: " + re);
        	 re.printStackTrace();
         }
         catch (Exception e)
         {
        	 System.err.println("Exception: " + e);
        	 e.printStackTrace();
         }
         finally {
        	 // close connection
        	 try {
        		 con.close();
                 System.out.println( "\n\nDeleteTest.java: Connection closed successfully.\n\n" );
        	 }
        	 catch( Exception e ) {
                 System.err.println( "Exception: " + e );
             }
        	 
         }

    }
}
