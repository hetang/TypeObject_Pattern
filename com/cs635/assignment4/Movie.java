package com.cs635.assignment4;

import java.util.ArrayList;

public class Movie {
	private double m_intId = 0;
	private String m_strTitle = null;
	private String m_strDirector = null;
	private String m_releaseDate = null;
	private int m_intRating = 0;
	private MovieCategory m_objCategory = null;
	private MovieGenre m_objGenre = new MovieGenre();
	private ArrayList<RentablesItem> m_objRentableList = new ArrayList<RentablesItem>();	
	
	public Movie(double pId, String pTitle, String pDirector, String pReleaseDate, int pRating){
		this.m_intId = pId;
		this.m_strTitle = pTitle;
		this.m_strDirector = pDirector;
		this.m_releaseDate = pReleaseDate;
		this.m_intRating = pRating;
	}
	
	public String getCategoryName(){
		return m_objCategory.getName();
	}
	
	public boolean setCategory(MovieCategory pCategoryName){
		m_objCategory = pCategoryName;		
		return true;		
	}	
	
	public boolean addGenre(String pGenreName){
		return m_objGenre.addGenre(pGenreName);
	}
	
	public boolean addDVDCopies(int pQuantites){
		for(int i=0;i<pQuantites;i++){
			RentablesItem objRentableItem = new DVDRentables(this);
			this.m_objRentableList.add(objRentableItem);
		}
		return true;
	}
	
	public boolean addVideotapeCopies(int pQuantites){
		for(int i=0;i<pQuantites;i++){
			RentablesItem objRentableItem = new VideotapesRentable(this);
			this.m_objRentableList.add(objRentableItem);
		}
		return true;
	}

	public double getId() {
		return m_intId;
	}
	
	public String getTitle() {
		return m_strTitle;
	}
	
	public String getDirector() {
		return m_strDirector;
	}
	
	public double getPrice(){
		if(null != m_objCategory){
			return m_objCategory.m_dblRentalPrice;
		}
		
		return 0;
	}
	
	public void setPrice(double pPrice){
		if(null != m_objCategory){
			m_objCategory.m_dblRentalPrice = pPrice;
		}
	}
	
	public int getQuantities(){
		return m_objRentableList.size();
	}
	
	public ArrayList<RentablesItem> getDVDRentables(){
		ArrayList<RentablesItem> objDVDRentables = new ArrayList<RentablesItem>();
		for(int i=0;i<m_objRentableList.size();i++){
			if(m_objRentableList.get(i).isDVD()){
				objDVDRentables.add(m_objRentableList.get(i));
			}
		}
		
		return objDVDRentables;
	}
	
	public boolean isDVDCopiesofMovie(){
		for(int i=0;i<m_objRentableList.size();i++){
			if(m_objRentableList.get(i).isDVD()){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<RentablesItem> getVideotapesRentables(){
		ArrayList<RentablesItem> objVideotapesRentables = new ArrayList<RentablesItem>();
		for(int i=0;i<m_objRentableList.size();i++){
			if(m_objRentableList.get(i).isVideoTapes()){
				objVideotapesRentables.add(m_objRentableList.get(i));
			}
		}
		
		return objVideotapesRentables;
	}
	
	public double computeLateFees(int pNoofDaysRented){
		if(null != m_objCategory){
			return m_objCategory.computeLateFees(pNoofDaysRented);
		}
		return 0;
	}

	public String getReleaseDate() {
		return m_releaseDate;
	}	

	public int getRating() {
		return m_intRating;
	} 
}