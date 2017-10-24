package edu.uga.cs.rentaride.entity.impl;



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
import edu.uga.cs.rentaride.persistence.impl.Persistent;
import edu.uga.cs.rentaride.RARException;


public class RentARideParamsImpl 
	extends Persistent
	implements RentARideParams 
{

	private int membershipPrice;
	private int lateFee;
	
	@Override
	public int getMembershipPrice() {
		return this.membershipPrice;
	}

	@Override
	public void setMembershipPrice(int membershipPrice) throws RARException {
		this.membershipPrice = membershipPrice;
	}

	@Override
	public int getLateFee() {
		return lateFee;
	}

	@Override
	public void setLateFee(int lateFee) throws RARException {
		this.lateFee = lateFee;
	}
	
}