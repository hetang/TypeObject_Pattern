package com.cs635.assignment4;

public class VideotapesRentable extends RentablesItem {
	
	public VideotapesRentable(Movie pMovie) {
		super(pMovie);
	}

	public boolean isVideoTapes(){
		return true;
	}
}