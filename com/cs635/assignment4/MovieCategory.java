package com.cs635.assignment4;

public class MovieCategory {
	
	private String m_strCategoryName = null;
	public double m_dblRentalPrice = 0;
	public int m_intRetalPeriod = 0;
	public double m_dblLateFees = 0;
	
	public MovieCategory(String pCategoryName){
		this.m_strCategoryName = pCategoryName;
	}
	
	public MovieCategory(String pCategoryName, double pRentalPrice, int pRetalPeriod, double pLateFees){
		this.m_strCategoryName = pCategoryName;
		this.m_dblRentalPrice = pRentalPrice;
		this.m_intRetalPeriod = pRetalPeriod;
		this.m_dblLateFees = pLateFees;
	}
	
	public void setPrice(double pRentalPrice){
		this.m_dblRentalPrice = pRentalPrice;
	}
	
	public double computeLateFees(int pNoofDaysRented){
		return (pNoofDaysRented - m_intRetalPeriod)> 0 ? (pNoofDaysRented - m_intRetalPeriod)*m_dblLateFees : 0;
	}
	
	public String getName(){
		return this.m_strCategoryName;
	}
}