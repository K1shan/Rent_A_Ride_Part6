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
         

         /* TODO
         *
         *  Example
         *  Needs change
         */

         /*

         List<Club> clubs = null;
         Iterator<Club> clubIter = null;

         Club tennisClub = null;
		*/
         
         /*
         try {
        	 System.out.println("hi");
        	*/
        	 
        	 /*
             Club modelClub = objectLayer.createClub();
             modelClub.setName( "Tennis" );
             clubs = objectLayer.findClub( modelClub );
             clubIter = clubs.iterator();


             while( clubIter.hasNext() ) {
                 tennisClub = clubIter.next();
                 System.out.println( tennisClub );
                 Person founder = tennisClub.getPersonFounder();
                 System.out.println( "   Founded by: " + founder );
                 System.out.println( "   Members: " );
                 List<Membership> memberships = tennisClub.getPersonsMembership();
                 Iterator<Membership> membershipIter = memberships.iterator();
                 while( membershipIter != null && membershipIter.hasNext() ) {
                     Membership m = membershipIter.next();
                     System.out.println( "      " + m.getPerson() );
                 }
             }
             
             if( tennisClub == null ) {
                 System.out.println(  "Tenis club does not exist" );
                 return;
             }
             // modify the name of the Tennis club to Advanced Tennis
             tennisClub.setName( "Advanced Tennis" );
             objectLayer.storeClub( tennisClub );
             System.out.println( "Updated the name of the Tenis club to Advanced Tennis" );
             
             Person marySwift = null;
             Person modelPerson = objectLayer.createPerson();
             modelPerson.setFirstName( "Mary" );
             modelPerson.setLastName( "Swift" );
             List<Person> persons = objectLayer.findPerson( modelPerson );
             Iterator<Person> personIter = persons.iterator();
             while( personIter.hasNext() ) {
                 marySwift = personIter.next();
                 System.out.println( marySwift );
                 System.out.println( "   Founder of: " );
                 clubs = marySwift.getClubsFounded();
                 clubIter = clubs.iterator();
                 while( clubIter != null && clubIter.hasNext() ) {
                     Club c = clubIter.next();
                     System.out.print( c + " " );
                 }
                 System.out.println();
                 System.out.println( "   Member of: " );
                 List<Membership> memberships = marySwift.getClubsMembership();
                 Iterator<Membership> membershipIter = memberships.iterator();
                 while( membershipIter != null && membershipIter.hasNext() ) {
                     Membership m = membershipIter.next();
                     System.out.println( "      " + m.getClub() );
                 }
             }
             
             if( marySwift == null ) {
                 System.out.println(  "Mary Swift does not exist" );
                 return;
             }
             // modify Mary Swift's telephone number
             marySwift.setPhone( "(111) 123-4567" );
             objectLayer.storePerson( marySwift );
             System.out.println( "Updated the phone number of Mary Swift to (111) 123-4567" );
            */
         
         
         /*
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
             }
             catch( Exception e ) {
                 System.err.println( "Exception: " + e );
             }
         }   
         */     
    }    
}
