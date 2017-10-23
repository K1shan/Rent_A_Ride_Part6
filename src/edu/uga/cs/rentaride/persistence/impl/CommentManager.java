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



public class CommentManager {

	private ObjectLayer objectLayer = null;
	private Connection con = null;
	
	public CommentManager(Connection con, ObjectLayer objectLayer){
		this.con = con;
		this.objectLayer = objectLayer;
	}//constructor
	
	public List<Comment> restore( Comment modelComment ) throws RARException{
		// TODO
		return null;
	}
	
    public void store( Comment comment ) throws RARException{
    	// TODO
    }
    
    public void delete( Comment comment ) throws RARException{
    	
		String delteComment = "DELETE FROM COMMENT WHERE comment_id = ?";              
		PreparedStatement stmt = null;
		int inscnt = 0;
		             
        if( !comment.isPersistent() ) 
            return;
        
        try {
            stmt = (PreparedStatement) con.prepareStatement( delteComment );         
            stmt.setLong( 1, comment.getId() );
            inscnt = stmt.executeUpdate();          
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new RARException( "CommentManager.delete: failed to delete a Comment" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "CommentManager.delete: failed to delete a Comment: " + e );       
            }
    }
}
