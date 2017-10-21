package edu.uga.cs.rentaride.object.impl;

import java.util.Date;
import java.util.List;


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

import edu.uga.cs.rentaride.entity.impl.AdministratorImpl;
import edu.uga.cs.rentaride.entity.impl.CommentImpl;
import edu.uga.cs.rentaride.entity.impl.CustomerImpl;
import edu.uga.cs.rentaride.entity.impl.HourlyPriceImpl;
import edu.uga.cs.rentaride.entity.impl.RentalImpl;
import edu.uga.cs.rentaride.entity.impl.RentalLocationImpl;
import edu.uga.cs.rentaride.entity.impl.RentARideParamsImpl;
import edu.uga.cs.rentaride.entity.impl.ReservationImpl;
import edu.uga.cs.rentaride.entity.impl.UserImpl;
import edu.uga.cs.rentaride.entity.impl.UserStatusImpl;
import edu.uga.cs.rentaride.entity.impl.VehicleImpl;
import edu.uga.cs.rentaride.entity.impl.VehicleConditionImpl;
import edu.uga.cs.rentaride.entity.impl.VehicleStatusImpl;
import edu.uga.cs.rentaride.entity.impl.VehicleTypeImpl;
import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;
import edu.uga.cs.rentaride.persistence.impl.Persistent;

public class ObjectLayerImpl
	implements ObjectLayer 
{
	PersistenceLayer persistence = null;

	public ObjectLayerImpl()
	{
			this.persistence = null;	
	
			System.out.println( "ObjectLayerImpl.ObjectLayerImpl(): initialized" );
	}
	
	public ObjectLayerImpl( PersistenceLayer persistence )
	{
		this.persistence = persistence;
		System.out.println( "ObjectLayerImpl.ObjectLayerImpl(persistence): initialized" );
	
	}
	
	@Override
	public Administrator createAdministrator(String firstName, String lastName, String userName, String password,
			String email, String address, Date createDate) throws RARException {
		AdministratorImpl administrator = new AdministratorImpl(firstName, lastName, userName, password, email, address, createDate);
        Persistent.setPersistenceLayer( persistence);
		return administrator;
	}

	@Override
	public Administrator createAdministrator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Administrator> findAdministrator(Administrator modelAdministrator) throws RARException {
		return persistence.restoreAdministrator(modelAdministrator);
	}

	@Override
	public void storeAdministrator(Administrator administrator) throws RARException {
		persistence.storeAdministrator(administrator);
	}

	@Override
	public void deleteAdministrator(Administrator administrator) throws RARException {
		// TODO Auto-generated method stub
	}

	
	@Override
	public Customer createCustomer(String firstName, String lastName, String userName, String password, String email,
			String address, Date createDate, Date membershipExpiration, String licenseState, String licenseNumber,
			String cardNumber, Date cardExpiration) throws RARException {
		
		
		CustomerImpl customer = new CustomerImpl(firstName, lastName, userName, password, email,
				address,  createDate,  membershipExpiration,   licenseState,   licenseNumber,
				  cardNumber,   cardExpiration);
		
        Persistent.setPersistenceLayer( persistence);
        return customer;
	}

	@Override
	public Customer createCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> findCustomer(Customer modelCustomer) throws RARException {
		return persistence.restoreCustomer(modelCustomer);
	}

	@Override
	public void storeCustomer(Customer customer) throws RARException {
		persistence.storeCustomer(customer);
	}

	@Override
	public RentalLocation createRentalLocation(String name, String address, int capacity) throws RARException {
		RentalLocationImpl rentalLocation = new RentalLocationImpl(name, address, capacity);
		Persistent.setPersistenceLayer( persistence );
		return rentalLocation;
	}

	@Override
	public RentalLocation createRentalLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RentalLocation> findRentalLocation(RentalLocation modelRentalLocation) throws RARException {
		return persistence.restoreRentalLocation(modelRentalLocation);
	}

	@Override
	public void storeRentalLocation(RentalLocation rentalLocation) throws RARException {
		persistence.storeRentalLocation(rentalLocation);
	}

	@Override
	public void deleteRentalLocation(RentalLocation rentalLocation) throws RARException {
		persistence.deleteRentalLocation(rentalLocation);
	}

	@Override
	public Reservation createReservation(Date pickupTime, int rentalLength, VehicleType vehicleType, 
            RentalLocation rentalLocation, Customer customer){
		ReservationImpl reservation = new ReservationImpl(pickupTime, rentalLength, vehicleType, rentalLocation, customer);
		Persistent.setPersistenceLayer( persistence );
		return reservation;
	}

	@Override
	public Reservation createReservation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> findReservation(Reservation modelReservation) throws RARException {
		return persistence.restoreReservation(modelReservation);
	}

	@Override
	public void storeReservation(Reservation reservation) throws RARException {
		persistence.storeReservation(reservation);
	}

	@Override
	public void deleteReservation(Reservation reservation) throws RARException {
		persistence.deleteReservation(reservation);
	}

	@Override
	public Rental createRental(Date pickupTime, Reservation reservation, Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rental createRental() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rental> findRental(Rental modelRental) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeRental(Rental rental) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRental(Rental rental) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VehicleType createVehicleType(String name) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VehicleType createVehicleType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehicleType> findVehicleType(VehicleType modelVehicleType) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeVehicleType(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteVehicleType(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vehicle createVehicle(String make, String model, int year, String registrationTag, int mileage,
			Date lastServiced, VehicleType vehicleType, RentalLocation rentalLocation,
			VehicleCondition vehicleCondition, VehicleStatus vehicleStatus) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehicle createVehicle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehicle> findVehicle(Vehicle modelVehicle) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeVehicle(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteVehicle(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Comment createComment(String text, Date date, Rental rental) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment createComment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findComment(Comment modelComment) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeComment(Comment comment) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComment(Comment coment) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HourlyPrice createHourlyPrice(int maxHrs, int price, VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HourlyPrice createHourlyPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HourlyPrice> findHourlyPrice(HourlyPrice modelHourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RentARideParams createRentARideParams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RentARideParams findRentARideParams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeRentARideParams(RentARideParams rentARideParams) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPersistence(PersistenceLayer persistence) {
		this.persistence = persistence;
	}
	


}