package com.cs635.assignment4;

public abstract class RentablesItem {
	public boolean m_blnRented = false;
	public String m_strRenterName = null;
	private Movie m_objMovie = null;
	
	public RentablesItem(Movie pMovie){
		this.m_objMovie = pMovie;
	}
	
	public boolean isDVD(){
		return false;
	}
	
	public boolean isVideoTapes(){
		return false;
	}	

	public Movie getMovie() {
		return m_objMovie;
	}
}