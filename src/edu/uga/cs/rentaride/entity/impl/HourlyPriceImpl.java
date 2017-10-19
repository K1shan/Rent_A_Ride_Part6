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

public class HourlyPriceImpl 
	extends Persistent
	implements HourlyPrice 
{

	@Override
	public int getMaxHours() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxHours(int maxHours) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPrice(int price) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VehicleType getVehicleType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVehicleType(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		
	}
	
}