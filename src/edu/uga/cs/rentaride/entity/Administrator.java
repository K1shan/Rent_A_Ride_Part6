package edu.uga.cs.rentaride.entity;

/** This class represents an Administrator user.  It has no additional attributes, and all are inherited from User.
 *
 */
public interface Administrator 
    extends User
{
	 /** Return the admin's user id.
     * @return the admin's user id.
     */
	public int getUserId();
	
	
	/** Set the admin's user id.
     * @param userId the new user id
     */
	public void setUserId(int userId);
}
