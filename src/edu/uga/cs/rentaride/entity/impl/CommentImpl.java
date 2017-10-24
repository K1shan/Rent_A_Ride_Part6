package edu.uga.cs.rentaride.entity.impl;



import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.persistence.impl.Persistent;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;


public class CommentImpl
	extends Persistent
	implements Comment 
{

	private String text;
	private Date date;
	private Rental rental;
	private Customer customer;
	
	public CommentImpl(){
		
		super( -1 );
		this.date = null;
		this.rental = null;
		this.text = null;
		this.customer = null;

	}
	
	public CommentImpl(String text, Date date, Rental rental, Customer customer){
		
		super( -1 );
		
		this.date = date;
		this.rental = rental;
		this.text = text;
		this.customer = customer;
		
	}
	
	
	@Override
	public String getText() {

		return this.text;
	}

	@Override
	public void setText(String text) {
		this.text = text;
		
	}

	@Override
	public Date getDate() {
		
		return this.date;
	}

	@Override
	public void setDate(Date date) {
		
		this.date = date;
		
	}

	@Override
	public Rental getRental() {
		
		return this.rental;
	}

	@Override
	public void setRental(Rental rental) throws RARException {
		
		this.rental = rental;
		
	}

	@Override
	public Customer getCustomer() {
		return this.customer;
	}
	
}