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


public class AdministratorManager {
	
	private ObjectLayer objectLayer = null;
	private Connection con = null;
	
	public AdministratorManager(Connection con, ObjectLayer objectLayer){
		this.con = con;
		this.objectLayer = objectLayer;
	}//constructor
	
	@SuppressWarnings("resource")
	public void store(Administrator administrator) throws RARException{
		
		boolean persist = false;
		
		String userInsertQuery = 
				"INSERT INTO USER "
				+ "(fname, lname, uname, pword, email, address, create_date) "
				+ "VALUES "
				+ "( ?, ?, ?, ?, ?, ?, ?)";
		
		String administratorInsertQuery = 
				"INSERT INTO ADMIN "
				+ "(user_id) "
				+ "VALUES ( ?)";
		
		String updateUserQuery = 
				"UPDATE USER SET "
				+ "fname=?, lname=?, uname=?, pword=?, email=?, create_date=? "
				+ "WHERE user_id=?";
		
		String updateAdministratorQuery = 
				"UPDATE ADMIN SET "
						+ "user_id=? "
						+ "WHERE administrator_id=?";
		
		String selectUserIdQuery = 
				"SELECT user_id "
				+ "FROM USER "
				+ "WHERE USER.email=?";
		
		PreparedStatement	pstmt;
		int 				inscnt;
		long 				administratorID;
		long userId = 0;
		
		try {
			//check if exists
			if(!administrator.isPersistent()){
				persist = false;
				pstmt = (PreparedStatement) con.prepareStatement(userInsertQuery);
			}else{
				persist = true;
				pstmt = (PreparedStatement) con.prepareStatement(updateUserQuery);
			}
			
			if( administrator.getFirstName() != null )
                pstmt.setString( 1, administrator.getFirstName() );
            else{
                throw new RARException( "AdministratorManager.save: can't save a user: FirstName undefined" );
            }if( administrator.getLastName() != null )
                pstmt.setString( 2, administrator.getLastName() );
            else
                throw new RARException( "AdministratorManager.save: can't save a user: LastName undefined" );
            if( administrator.getUserName() != null )
                pstmt.setString( 3, administrator.getUserName() );
            else
                throw new RARException( "AdministratorManager.save: can't save a user: UserName undefined" );
            if( administrator.getPassword() != null )
                pstmt.setString( 4, administrator.getPassword());
            else
                throw new RARException( "AdministratorManager.save: can't save a user: Password undefined" );
            if( administrator.getEmail() != null )
                pstmt.setString( 5, administrator.getEmail());
            else
                throw new RARException( "AdministratorManager.save: can't save a user: Email undefined" );
            if( administrator.getAddress() != null )
                pstmt.setString( 6, administrator.getAddress());
            else
                throw new RARException( "AdministratorManager.save: can't save a user: Address undefined" );
         
            if( administrator.getCreatedDate() != null ){
            	java.util.Date myDate = administrator.getCreatedDate();
            	java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
            	pstmt.setDate(7, sqlDate);
            }else
                throw new RARException( "AdministratorManager.save: can't save a user: CreatedDate undefined" );
            if( administrator.isPersistent() )
                pstmt.setLong( 8, administrator.getId() );

			System.out.println("query: "+pstmt.asSql());
            inscnt = pstmt.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
            throw new RARException( "AdministratorManager.save: failed to save an Administrator: " + e );
		}
		
		/*
		 * Get userId
		 */
		try {
			pstmt = (PreparedStatement) con.prepareStatement( selectUserIdQuery );
			pstmt.setString(1, administrator.getEmail());
			System.out.println("query: "+pstmt.asSql());
			ResultSet rs = pstmt.executeQuery();
			while( rs.next() ) {
                userId = rs.getLong( 1 );
            }
		} catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "AdministratorManager.save: failed to get userId: " + e );
		}

		/*
		 * ADMIN
		 */
		try {
			if( !persist ){
				pstmt = (PreparedStatement) con.prepareStatement( administratorInsertQuery );
			}else{
				pstmt = (PreparedStatement) con.prepareStatement( updateAdministratorQuery );
			}
			if( userId != 0 )
                pstmt.setLong( 1, userId );
            else{
                throw new RARException( "AdminsitratorManager.save: can't save a administrator: userId undefined" );
            }
			
            if( persist )
                pstmt.setLong( 8, administrator.getId() );

            System.out.println("query: "+pstmt.asSql());
            inscnt = pstmt.executeUpdate();
            

            if( !administrator.isPersistent() ) {
                // in case this this object is stored for the first time,
                // we need to establish its persistent identifier (primary key)
                if( inscnt == 1 ) {
                    String sql = "select last_insert_id()";
                    if( pstmt.execute( sql ) ) { // statement returned a result
                        // retrieve the result
                        ResultSet r = pstmt.getResultSet();
                        // we will use only the first row!
                        while( r.next() ) {
                            // retrieve the last insert auto_increment value
                            administratorID = r.getLong( 1 );
                            if( administratorID > 0 )
                                administrator.setId( administratorID ); // set this person's db id (proxy object)
                        }
                    }
                }
            }
            else {
                if( inscnt < 1 )
                    throw new RARException( "AdministratorManager.save: failed to save a administrator" ); 
            }
		}catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "AdministratorManager.save: failed to save a administrator: " + e );
		}
	}//store
	
	
	public List<Administrator> restore(Administrator modelAdministrator)

			throws RARException{
		
		String selectAdministratorQuery = 
				"SELECT "
				+ "USER.user_id, USER.fname, USER.lname, USER.uname, USER.pword, USER.email, USER.address, USER.create_date, "
				+ "ADMIN.admin_id "
				+ "FROM USER INNER JOIN ADMIN ON USER.user_id = ADMIN.user_id";
		
		System.out.println("query: "+selectAdministratorQuery);
		List<Administrator> administrators = new ArrayList<Administrator>();
		Statement stmt = null;
		
		try {
			
			stmt = con.createStatement();
			
			if( stmt.execute(selectAdministratorQuery)){
				
				ResultSet r = stmt.getResultSet();
				
				long id;
	            String fname;
	            String lname;
	            String uname;
	            String pword;
	            String email;
	            String address;
	            Date createDate;
	            
	            long administratorId;
	            
	            while( r.next() ) {
	            	id	= r.getLong(1);
	           	 	fname = r.getString(2);
	                lname = r.getString(3);
	                uname = r.getString(4);
	                pword = r.getString(5);
	                email = r.getString(6);
	                address = r.getString(7);
	                createDate = r.getDate(8);
	                
	                administratorId = r.getLong(9);
	                
	                
	                Administrator administrator = 
	                		objectLayer.createAdministrator(fname, lname, uname, pword, email,
	            			address, createDate);
	                administrator.setId(administratorId);
	                administrators.add(administrator);
	            }
			}
            return administrators;
            
		} catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "AdministratorManager.get: failed to get any administrators: " + e );
		}
	}//restore
	
	public void delete(Administrator admin) throws RARException {
		String deleteAdministratorSql = "DELETE ADMIN FROM ADMIN INNER JOIN `USER` ON ADMIN.user_id = USER.user_id WHERE USER.uname = ?;";
		PreparedStatement stmt;
		int inscnt;

		if (!admin.isPersistent()) // checks if Administrator object is persistent. If not, nothing to delete
			return;
		
		try {
			stmt = (PreparedStatement) con.prepareStatement(deleteAdministratorSql);
			stmt.setString(1, admin.getUserName());

			inscnt = stmt.executeUpdate();
			if(inscnt == 1) {
				return;
			}
			else
				throw new RARException("AdministratorManager.delete: failed to delete an administrator");
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new RARException("AdministratorManager.delete: failed to delete an administrator" + e);
		}
	}
}
