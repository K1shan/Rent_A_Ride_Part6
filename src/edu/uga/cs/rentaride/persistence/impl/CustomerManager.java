package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

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
import edu.uga.cs.rentaride.persistence.PersistenceLayer;



public class CustomerManager{

	private ObjectLayer objectLayer = null;
	private Connection con = null;
	
	public CustomerManager(Connection con, ObjectLayer objectLayer){
		this.con = con;
		this.objectLayer = objectLayer;
	}//constructor
	
	@SuppressWarnings("resource")
	public void store(Customer customer) throws RARException{
		
		// TODO
		boolean persist = false;
		
		String userInsertQuery = 
				"INSERT INTO USER "
				+ "(fname, lname, uname, pword, email, address, create_date) "
				+ "VALUES "
				+ "( ?, ?, ?, ?, ?, ?, ?)"; 
		
		String customerInsertQuery = 
				"INSERT INTO CUSTOMER "
				+ "(user_id, member_until, lic_state, lic_num, cc_num, cc_exp, status) "
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ? )";
				//+ "(SELECT USER.user_id FROM USER WHERE USER.uname = " + customer.getUserName() + ")";
		
		String updateUserQuery = 
				"UPDATE USER SET "
				+ "fname=?, lname=?, uname=?, pword=?, email=?, create_date=? "
				+ "WHERE user_id=?";               
		
		String updateCustomerQuery = 
				"UPDATE CUSTOMER SET "
				+ "user_id=?, member_until=?, lic_state=?, lic_num=?, cc_num=?, cc_exp=?, status=? "
				+ "WHERE customer_id=?";
		
		String selectUserIdQuery = 
				"SELECT user_id "
				+ "FROM USER "
				+ "WHERE USER.email=?";
		
		PreparedStatement stmt;
		int inscnt;
		long customerID;
	    
		
		try {
			
			/*
			 * USER
			 */
			if( !customer.isPersistent() ){
				System.out.println("Insert");
				persist = false;
                stmt = (PreparedStatement) con.prepareStatement( userInsertQuery );
			}else{
				System.out.println("Update");
				persist = true;
                stmt = (PreparedStatement) con.prepareStatement( updateUserQuery );
			}


            if( customer.getFirstName() != null )
                stmt.setString( 1, customer.getFirstName() );
            else{
                throw new RARException( "CustomerManager.save: can't save a customer: userName undefined" );
            }

            
            if( customer.getLastName() != null )
                stmt.setString( 2, customer.getLastName() );
            else
                throw new RARException( "CustomerManager.save: can't save a customer: last name undefined" );
            
            
            if( customer.getUserName() != null )
                stmt.setString( 3, customer.getUserName() );
            else
                throw new RARException( "CustomerManager.save: can't save a customer: last name undefined" );
         
            if( customer.getPassword() != null )
                stmt.setString( 4, customer.getPassword());
            else
                throw new RARException( "CustomerManager.save: can't save a customer: last name undefined" );
         
            if( customer.getEmail() != null )
                stmt.setString( 5, customer.getEmail());
            else
                throw new RARException( "CustomerManager.save: can't save a customer: last name undefined" );
         
            if( customer.getAddress() != null )
                stmt.setString( 6, customer.getAddress());
            else
                throw new RARException( "CustomerManager.save: can't save a customer: last name undefined" );
         
            if( customer.getCreatedDate() != null ){
            	
            	java.sql.Date sqlDate = new java.sql.Date(customer.getCreatedDate().getTime());
            	stmt.setDate(7, sqlDate);
            }

            else
                throw new RARException( "CustomerManager.save: can't save a customer: last name undefined" );
     
            
            if( customer.isPersistent() )
                stmt.setLong( 8, customer.getId() );

            inscnt = stmt.executeUpdate();

            if( !customer.isPersistent() ) {
                // in case this this object is stored for the first time,
                // we need to establish its persistent identifier (primary key)
                if( inscnt == 1 ) {
                    String sql = "select last_insert_id()";
                    if( stmt.execute( sql ) ) { // statement returned a result
                        // retrieve the result
                        ResultSet r = stmt.getResultSet();
                        // we will use only the first row!
                        while( r.next() ) {
                            // retrieve the last insert auto_increment value
                            customerID = r.getLong( 1 );
                            if( customerID > 0 )
                                customer.setId( customerID ); // set this person's db id (proxy object)
                        }
                    }
                }
            }
            else {
                if( inscnt < 1 )
                    throw new RARException( "CustomerManager.save: failed to save a customer" ); 
            }
		}catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "CustomerManager.save: failed to save a customer: " + e );
		}
		
		
		
		/*
		 * Get userId
		 */
		long userId = 0;
		
		try {
			stmt = (PreparedStatement) con.prepareStatement( selectUserIdQuery );
			stmt.setString(1, customer.getEmail());
			System.out.println(stmt.asSql());

			ResultSet rs = stmt.executeQuery();
			
            while( rs.next() ) {
                userId = rs.getLong( 1 );
            }
        
			
		} catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "CustomerManager.save: failed to save a customer: " + e );
		}

		
		try {
			
			/*
			 * CUSTOMER
			 */
			if( !persist ){
				System.out.println("Insert");
				stmt = (PreparedStatement) con.prepareStatement( customerInsertQuery );
			}else{
				System.out.println("Update");
				stmt = (PreparedStatement) con.prepareStatement( updateCustomerQuery );
			}


            if( userId != 0 )
                stmt.setLong( 1, userId );
            else{
                throw new RARException( "CustomerManager.save: can't save a customer: userName undefined" );
            }

            
            if( customer.getMemberUntil() != null ){
            	java.sql.Date sqlDate = new java.sql.Date(customer.getMemberUntil().getTime());
        		stmt.setDate(2, sqlDate);
            }
            else
                throw new RARException( "CustomerManager.save: can't save a customer: last name undefined" );
            
            
            if( customer.getLicenseState() != null )
                stmt.setString( 3, customer.getLicenseState() );
            else
                throw new RARException( "CustomerManager.save: can't save a customer: last name undefined" );
         
            if( customer.getLicenseNumber() != null )
                stmt.setString( 4, customer.getLicenseNumber());
            else
                throw new RARException( "CustomerManager.save: can't save a customer: last name undefined" );
         
            if( customer.getCreditCardNumber() != null )
                stmt.setString( 5, customer.getCreditCardNumber());
            else
                throw new RARException( "CustomerManager.save: can't save a customer: last name undefined" );
         
            if( customer.getCreditCardExpiration() != null ){
            	java.sql.Date sqlDate = new java.sql.Date(customer.getCreditCardExpiration().getTime());
        		stmt.setDate(6, sqlDate);
            } else
                throw new RARException( "CustomerManager.save: can't save a customer: last name undefined" );
         
            if(customer.getUserStatus() == null ) 
                stmt.setLong( 7, 0);
            else
                throw new RARException( "CustomerManager.save: can't save a customer: last name undefined" );
         
        
            if( persist )
                stmt.setLong( 8, customer.getId() );

            System.out.println(stmt.asSql());
            inscnt = stmt.executeUpdate();
            

            if( !customer.isPersistent() ) {
                // in case this this object is stored for the first time,
                // we need to establish its persistent identifier (primary key)
                if( inscnt == 1 ) {
                    String sql = "select last_insert_id()";
                    if( stmt.execute( sql ) ) { // statement returned a result
                        // retrieve the result
                        ResultSet r = stmt.getResultSet();
                        // we will use only the first row!
                        while( r.next() ) {
                            // retrieve the last insert auto_increment value
                            customerID = r.getLong( 1 );
                            if( customerID > 0 )
                                customer.setId( customerID ); // set this person's db id (proxy object)
                        }
                    }
                }
            }
            else {
                if( inscnt < 1 )
                    throw new RARException( "CustomerManager.save: failed to save a customer" ); 
            }
		}catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "CustomerManager.save: failed to save a customer: " + e );
		}
	        
	}

	public List<Customer> restore(Customer modelCustomer ) 
		throws RARException{
		
		String selectCustomerQuery = 
				"SELECT * FROM CUSTOMER INNER JOIN USER ON USER.user_id = CUSTOMER.user_id";
		
		List<Customer> customers = new ArrayList<Customer>();
		Statement stmt = null;
		int inscnt;
		long customerID;
		
		try {
			
			long id;
            String fname;
            String lname;
            String uname;
            String pword;
            String email;
            String address;
            Date createDate;
            long customerId;
            Date memberUntil;
            String licState;
            String licNum;
            String ccNum;
            Date ccExp;
            long status;
            
            
			stmt = con.createStatement();
			ResultSet r = stmt.executeQuery(selectCustomerQuery);
			
            while( r.next() ) {
            	id	= r.getLong(1);
           	 	fname = r.getString(2);
                lname = r.getString(3);
                uname = r.getString(4);
                pword = r.getString(5);
                email = r.getString(6);
                address = r.getString(7);
                createDate= r.getDate(8);
                customerId = r.getLong(9);
                memberUntil = r.getDate(10);
                licState = r.getString(11);
                licNum = r.getString(12);
                ccNum = r.getString(13);
                ccExp = r.getDate(14);
                status = r.getLong(15);
                
                
                Customer customer = objectLayer.createCustomer(fname, lname, uname, pword, email,
            			address, createDate, memberUntil, licState, licNum,
           			ccNum, ccExp);
                customer.setId(id);
                customers.add(customer);
            }
        
            
       
            
            return customers;
            
            
			
		} catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "CustomerManager.get: failed to get any customers: " + e );
		}
		//throw new RARException( "CustomerManager.restore: Could not restore persistent Customer objects" );
	}
}
