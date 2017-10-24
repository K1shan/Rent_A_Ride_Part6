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
	
    public void store( Comment comment ) throws RARException{
    	// TODO
    	
    	String insertCommentQuery =
				"INSERT INTO COMMENT "
				+ "( rental_id, text, comment_date ) "
				+ "VALUES "
				+ "( ?, ?, ? )";
		
		String updateCommentQuery =
				"UPDATE INTO COMMENT "
				+ "( rental_id, text, comment_date ) "
				+ "VALUES "
				+ "( ?, ?, ? )";
		
		PreparedStatement pstmt;
		int inscnt;
		long commentId;
		
		try {
			
			// check persist
			if( !comment.isPersistent() ){
				pstmt = (PreparedStatement) con.prepareStatement( insertCommentQuery );
			}else{
				pstmt = (PreparedStatement) con.prepareStatement( updateCommentQuery );
			}
			
			// update pstmt
			if( comment.getRental().getId() > 0 ){
				pstmt.setLong( 1, comment.getRental().getId() );
			}else{
				throw new RARException( "Comment.save: can't save a comment: rental undefined" );
			}
			
			if( comment.getText() != null ){
				pstmt.setString( 2, comment.getText() );
			}else{
				throw new RARException( "Comment.save: can't save a comment: text undefined" );
			}
			
			if( comment.getDate() != null ){
				java.util.Date myDate = comment.getDate();
	        	java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
				pstmt.setDate( 3, sqlDate );
			}else{
				throw new RARException( "Comment.save: can't save a comment: date undefined" );
			}
			
			System.out.println("query: " + pstmt.asSql());
            inscnt = pstmt.executeUpdate();
            
            // auto_inc pk to object pk
            if ( !comment.isPersistent() ){
            	if( inscnt == 1 ){
            		String sql = "select last_insert_id()";
            		if( pstmt.execute( sql ) ){
            			ResultSet rs = pstmt.getResultSet();
            			while( rs.next() ){
            				commentId = rs.getLong( 1 );
            				if( commentId > 0 ){
            					comment.setId( commentId );
            				}
            			}
            		}
            	}
            }else{
            	if( inscnt < 1 ){
            		throw new RARException( "CommentManager.save: failed to save a comment" );
            	}
            }
			
		} catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "CommentManager.store: failed to store a comment: " + e );
		}
    }
    
    public List<Comment> restore( Comment modelComment ) throws RARException{
		// TODO
    	
    	String selectCommentQuery =
				"SELECT * FROM COMMENT";
		
		List<Comment> comments = new ArrayList<Comment>();
		Statement stmt = null;
		System.out.println("query: "+selectCommentQuery);
		
		try {
			stmt = con.createStatement();

			if( stmt.execute(selectCommentQuery) ){
				ResultSet rs = stmt.getResultSet();

				int		comment_comment_id;
				int		comment_rental_id;
				String	comment_text;
				Date	comment_date;
				
				Comment	comment = null;
				Rental rental = null;
				
				while( rs.next() ){
					comment_comment_id = rs.getInt(1);
					comment_rental_id = rs.getInt(2);
					comment_text = rs.getString(3);
					comment_date = rs.getDate(4);
					
					rental = objectLayer.createRental();
					rental.setId(comment_rental_id);
					
					comment = objectLayer.createComment(comment_text, comment_date, rental);
					comment.setId(comment_comment_id);
					comments.add(comment);
				}
			}
			return comments;
			
		} catch (SQLException e){
			e.printStackTrace();
			throw new RARException( "CommentManager.get: failed to get any comments: " + e );
		}
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
