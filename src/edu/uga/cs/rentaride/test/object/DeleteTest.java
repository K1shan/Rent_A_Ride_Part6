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
         

         /* TODO
         *
         *  Example
         *  Needs change
         */

         /*
         Iterator<Club> clubIter = null;
                  
         try {
             
             // find the Running club
             Club runningClub = null;
             Club modelClub = objectLayer.createClub();
             modelClub.setName( "Running" );
             List<Club> clubs = objectLayer.findClub( modelClub );
             clubIter = clubs.iterator();
             while( clubIter.hasNext() ) {
                 runningClub = clubIter.next();
                 System.out.println( runningClub );
                 Person founder = runningClub.getPersonFounder();
                 System.out.println( "   Founded by: " + founder );
                 System.out.println( "   Members: " );
                 Membership modelMembership = objectLayer.createMembership();
                 modelMembership.setClub( runningClub );
                 List<Membership> memberships = objectLayer.findMembership( modelMembership );
                 Iterator<Membership> membershipIter = memberships.iterator();
                 while( membershipIter != null && membershipIter.hasNext() ) {
                     Membership m = membershipIter.next();
                     System.out.println( "      " + m.getPerson() );
                 }
             }
             
             // delete the Running club
             if( runningClub != null ) {
                 objectLayer.deleteClub( runningClub );
                 System.out.println( "Deleted the Running club" );
             }
             else
                 System.out.println( "Failed to find the Running club" );
             
             // find Heather Brooks
             Person heatherBrooks = null;
             Person modelPerson = objectLayer.createPerson();
             modelPerson.setFirstName( "Heather" );
             modelPerson.setLastName( "Brooks" );
             List<Person> persons = objectLayer.findPerson( modelPerson );
             Iterator<Person> personIter = persons.iterator();
             while( personIter.hasNext() ) {
                 heatherBrooks = personIter.next();
                 System.out.println( heatherBrooks );
                 System.out.print( "   Founder of: " );
                 if( heatherBrooks == null ) {
                     System.out.println( "Heather Brooks does not exist" );
                     return;
                 }
                 clubs = heatherBrooks.getClubsFounded();
                 clubIter = clubs.iterator();
                 while( clubIter != null && clubIter.hasNext() ) {
                     Club c = clubIter.next();
                     System.out.print( c + " " );
                 }
                 System.out.println();
                 System.out.println( "   Member of: " );
                 Membership modelMembership = objectLayer.createMembership();
                 modelMembership.setPerson( heatherBrooks );
                 List<Membership> memberships = objectLayer.findMembership( modelMembership );
                 Iterator<Membership> membershipIter = memberships.iterator();
                 while( membershipIter != null && membershipIter.hasNext() ) {
                     Membership m = membershipIter.next();
                     System.out.println( "      " + m.getClub() );
                 }
             }
             
             // delete Heather Brooks
             if( heatherBrooks != null ) {
                 objectLayer.deletePerson( heatherBrooks );
                 System.out.println( "Deleted Heather Brooks" );
             }
             else
                 System.out.println( "Failed to find Heather Brooks" );
             
             // remove membership of Robert Wilson in the Bridge club
             // locate the Bridge club
             Club bridgeClub = null;
             modelClub = objectLayer.createClub();
             modelClub.setName( "Bridge" );
             clubs = objectLayer.findClub( modelClub );
             clubIter = clubs.iterator();
             while( clubIter.hasNext() ) {
                 bridgeClub = clubIter.next();
                 System.out.println( bridgeClub );
             }
             
             if( bridgeClub == null ) {
                 System.out.println( "Failed to find the Bridge club" );
                 return;
             }
             
             // locate Robert Wilson
             Person robertPerson = null;
             modelPerson = objectLayer.createPerson();
             modelPerson.setFirstName( "Robert" );
             modelPerson.setLastName( "Wilson" );
             persons = objectLayer.findPerson( modelPerson );
             personIter = persons.iterator();
             while( personIter.hasNext() ) {
                 robertPerson = personIter.next();
                 System.out.println( robertPerson );
             }
             
             if( robertPerson == null ) {
                 System.out.println( "Failed to find Robert Wilson person" );
                 return;
             }
             
             // locate the Membership record of Robert Wilson in the Bridge club
             if( bridgeClub != null && robertPerson != null ) {
                 Membership robertInBridgeMembership = null;
                 Membership modelMembership = objectLayer.createMembership();
                 modelMembership.setClub( bridgeClub );
                 modelMembership.setPerson( robertPerson );
                 List<Membership> memberships = objectLayer.findMembership( modelMembership );
                 Iterator<Membership> membershipIter = memberships.iterator();
                 while( membershipIter.hasNext() ) {
                     robertInBridgeMembership = membershipIter.next();
                     System.out.println( robertInBridgeMembership );
                 }

                 // delete the Membership record of Robert Wilson in the Bridge club
                 if( robertInBridgeMembership != null ) {
                     objectLayer.deleteMembership( robertInBridgeMembership );
                     System.out.println( "Deleted the membership of Robert Wilson in the Bridge club" );
                 }
                 else
                     System.out.println( "Failed to find membership of Robert Wilson in the Bridge club" );
             }
             */

//         }
//         catch( RARException ce )
//         {
//             System.err.println( "RARException: " + ce );
//             ce.printStackTrace();
//         }
//         catch( Exception e )
//         {
//             System.err.println( "Exception: " + e );
//             e.printStackTrace();
//         }
//         finally {
//             // close the connection
//             try {
//                 con.close();
//             }
//             catch( Exception e ) {
//                 System.err.println( "Exception: " + e );
//             }
//         }   
    }
}
