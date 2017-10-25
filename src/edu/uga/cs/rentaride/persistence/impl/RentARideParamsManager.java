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



public class RentARideParamsManager {

	private ObjectLayer objectLayer = null;
	private Connection con = null;
	
	public RentARideParamsManager(Connection con, ObjectLayer objectLayer){
		this.con = con;
		this.objectLayer = objectLayer;
	}//constructor
	
	@SuppressWarnings("resource")
	public void store( RentARideParams rentARideConfig ) throws RARException{
    	
		boolean persist = false;

		String paramsInsertQuery = 
		"INSERT INTO RENT_A_RIDE_PARAMS "
		+ "(member_fee, late_fee)"

		+ "VALUES "
		+ "( ?, ?)";

		String paramsUpdateQuery =
		"UPDATE RENT_A_RIDE_PARAMS "
			+ "member_fee=?, late_fee=? ";


		PreparedStatement	pstmt;
		int					inscnt;
		long				paramsId;


		try{
			if( !rentARideConfig.isPersistent()) {
				pstmt = (PreparedStatement) con.prepareStatement(paramsInsertQuery);
			}
			else{
				pstmt = (PreparedStatement) con.prepareStatement(paramsUpdateQuery);
			}

			if(rentARideConfig.getMembershipPrice() >= 0 ) {
				pstmt.setLong(1, rentARideConfig.getMembershipPrice());
			}
			else {
				throw new RARException("RentARideParamsManager.save: can't save a params: member_fee undefined");
			}

			if(rentARideConfig.getLateFee() >= 0 ) {
				pstmt.setLong(2, rentARideConfig.getLateFee());
			}
			else {
				throw new RARException("RentARideParamsManager.save: can't save a params: late_fee undefined");
			}

			pstmt.executeUpdate();
			
		} //try
		catch(SQLException e) {
			e.printStackTrace();
			throw new RARException( "RentARideParamsManager.save: failed to save a params: " + e );
		}

    }
	

	public RentARideParams restore() throws RARException{
		// TODO
		return null;
	}
	
    
}
