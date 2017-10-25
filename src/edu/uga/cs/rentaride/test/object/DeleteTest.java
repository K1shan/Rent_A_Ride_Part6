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
         

         Iterator<Administrator> adminIter = null;
         Iterator<Customer> customerIter = null;
         Iterator<Comment> commentIter = null;
         Iterator<Rental> rentalIter = null;
         //TODO

         try {
        	 
 	 		Comment runningComment = null;
 	 		Comment modelComment = objectLayer.createComment();
 	 		modelComment.setId(1);;
 	 		List<Comment> comments = objectLayer.findComment(modelComment);
 	 		commentIter = comments.iterator();
 	 		while(commentIter.hasNext()) {
 	 			runningComment = commentIter.next();
 	 			System.out.println( runningComment );
 	 			if( runningComment == null ) {
 	 				System.out.println( "bird1 does not exist" );
 	 				return;
 	 			}
 	 		}

   	 	
		    if( runningComment != null ) {
	
	            objectLayer.deleteComment(runningComment);
	            System.out.println( "Change Customer Status" );
	        }
	        else
	            System.out.println( "Failed to find bird1" );
	    	 
	 		Customer runningCustomer = null;
	    	 	Customer modelCustomer = objectLayer.createCustomer();
	        modelCustomer.setUserName("atticus");
	        List<Customer> customers = objectLayer.findCustomer(modelCustomer);
	        customerIter = customers.iterator();
	   	 	while( customerIter.hasNext() ) {
	   	 		runningCustomer = customerIter.next();
	            System.out.println( runningCustomer );
	            if( runningCustomer == null ) {
	                System.out.println( "bird1 does not exist" );
	                return;
	            }
	    	 	}
       	 	
        	    if( runningCustomer != null ) {

                    objectLayer.deleteCustomer(runningCustomer);
                    System.out.println( "Change Customer Status" );
                }
                else
                    System.out.println( "Failed to find bird1" );
            
            
        	 	Administrator runningAdmin = null;   	 	
        	 	Administrator modelAdmin = objectLayer.createAdministrator();
        	 	modelAdmin.setUserName("wayne");;
        	 	List<Administrator> admin = objectLayer.findAdministrator(modelAdmin);
        	 	adminIter = admin.iterator();
        	 	
        	 	while( adminIter.hasNext() ) {
                runningAdmin = adminIter.next();
                System.out.println( runningAdmin );
                if( runningAdmin == null ) {
                    System.out.println( "bird1 does not exist" );
                    return;
                }
        	 	}
        	 	
        	    if( runningAdmin != null ) {

                    objectLayer.deleteAdministrator(runningAdmin);;
                    System.out.println( "Deleted Wayne" );
                }
                else
                    System.out.println( "Failed to find wayne" );
        	    
        	    
        	 
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
