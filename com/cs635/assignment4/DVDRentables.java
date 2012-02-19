package com.cs635.assignment4;

public class DVDRentables extends RentablesItem {
	
	public DVDRentables(Movie pMovie) {
		super(pMovie);
	}

	public boolean isDVD(){
		return true;
	}
}