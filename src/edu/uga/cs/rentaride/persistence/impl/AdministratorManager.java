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
		
		String insertAdminQuery = 
				"INSERT INTO USER "
				+ "(fname, lname, uname, pword, email, address, create_date) "
				+ "VALUES "
				+ "( ?, ?, ?, ?, ?, ?, ?)";
		
		String updateAdminQuery = "UPDATE ADMIN SET user_id = ? WHERE admin_id = ?";
		PreparedStatement	pstmt = null;
		int 				inscnt;
		long 				adminId;
		
		try {
			//check if exists
			if(!admin.isPersistent()){
				pstmt = (PreparedStatement) con.prepareStatement(insertAdminQuery);
			}else{
				pstmt = (PreparedStatement) con.prepareStatement(updateAdminQuery);
			}
			
			pstmt.setInt(1, admin.getUserId());
			
			if(admin.isPersistent()){
				pstmt.setLong(2, admin.getId());
			}
			
			inscnt = pstmt.executeUpdate();
			if(!admin.isPersistent()){
				if(inscnt >= 1){
					String query = "select last_insert_id()";
					if(pstmt.execute(query)){
						ResultSet r = pstmt.getResultSet();
						while(r.next()){
							adminId = r.getLong(1);
							if(adminId > 0){
								admin.setId(adminId);
							}
						}
					}
				}else{
					throw new RARException("AdministratorManager.save: failed to save an Admin.");
				}
			}else{
				if(inscnt < 1){
					throw new RARException("AdministratorManager.save: failed to save an Admin.");
				}
			}
		} catch(SQLException e){
			e.printStackTrace();
            throw new RARException( "AdministratorManager.save: failed to save an Admin: " + e );
		}
	}//store
	
	
	public List<Administrator> restore(Administrator modelAdministrator)
		throws RARException
	{
		return null;
	}//restore
	
	
	public void delete(Administrator admin)
			throws RARException
	{
		String 				deleteAdminQuery = "DELETE FROM ADMIN WHERE admin_id = ?";
		PreparedStatement	pstmt = null;
		int					inscnt;
		
		if(!admin.isPersistent()){
			return;
		}
		
		try {
			pstmt = (PreparedStatement) con.prepareStatement(deleteAdminQuery);
			pstmt.setLong(1, admin.getId());
			inscnt = pstmt.executeUpdate();
			if(inscnt == 1){
				return;
			}else{
				throw new RARException("AdministratorManager.delete: failed to delete an Admin");
			}
		} catch(SQLException e){
			e.printStackTrace();
			throw new RARException("AdministratorManager.delete: failed to delete an Admin: " + e);
		}
	}
	
}
