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
<<<<<<< HEAD
	private Customer customer;
=======
>>>>>>> master
	
	public CommentImpl(){
		super( -1 );
		this.date = null;
		this.rental = null;
		this.text = null;
<<<<<<< HEAD
		this.customer = null;

	}
	
	public CommentImpl(String text, Date date, Rental rental, Customer customer){
		
=======
	}
	
	public CommentImpl(String text, Date date, Rental rental){
>>>>>>> master
		super( -1 );
		this.date = date;
		this.rental = rental;
		this.text = text;
<<<<<<< HEAD
		this.customer = customer;
		
=======
>>>>>>> master
	}
	
	@Override
	public String getText() {
<<<<<<< HEAD

		return this.text;
=======
		return text;
>>>>>>> master
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public Date getDate() {
<<<<<<< HEAD
		
		return this.date;
=======
		return date;
>>>>>>> master
	}

	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public Rental getRental() {
<<<<<<< HEAD
		
		return this.rental;
=======
		return rental;
>>>>>>> master
	}

	@Override
	public void setRental(Rental rental) throws RARException {
		this.rental = rental;
		
	}

	@Override
	public Customer getCustomer() {
<<<<<<< HEAD
		return this.customer;
=======
		return this.getCustomer();
	}

	@Override
	public String toString() {
		return "CommentImpl ["
				+ "text=" + text 
				+ ", date=" + date 
				+ ", rental=" + rental + 
				"]";
>>>>>>> master
	}
}