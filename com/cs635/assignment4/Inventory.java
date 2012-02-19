package com.cs635.assignment4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Inventory {
	private HashMap<String,Movie> m_hashNameMap = new HashMap<String,Movie>();
	private TreeMap<Double,Movie> m_hashIdSortedMap = new TreeMap<Double,Movie>();
	private static HashMap<String,MovieCategory> m_objCategoryList = new HashMap<String,MovieCategory>();
	
	static{
		m_objCategoryList.put("Recent releases", new MovieCategory("Recent releases",5.00,3,1.00));
		m_objCategoryList.put("Normal", new MovieCategory("Normal",3.00,7,0.75));
		m_objCategoryList.put("Oldies", new MovieCategory("Oldies",2.00,7,0.50));
		m_objCategoryList.put("Manager Special", new MovieCategory("Manager Special"));
	}
	
	public double addNewMovie(String pCategoryName,String pTitle, String pDirector, String pReleaseDate, int pRating){
		double l_dblLastKey = 0;
		if(m_hashIdSortedMap != null && m_hashIdSortedMap.size() > 0){
			l_dblLastKey = m_hashIdSortedMap.lastKey();
		}
		Movie objNewMovie = new Movie(++l_dblLastKey,pTitle,pDirector,pReleaseDate,pRating);
		objNewMovie.setCategory(m_objCategoryList.get(pCategoryName));
		m_hashIdSortedMap.put(objNewMovie.getId(), objNewMovie);
		m_hashNameMap.put(pTitle, objNewMovie);
		return objNewMovie.getId();
	}
	
	public Movie findMovie(double p_dblMovieId,String p_strMovieName){
		Movie objMovie = null;
		if(p_dblMovieId != 0){
			objMovie = m_hashIdSortedMap.get(p_dblMovieId);
		}else if(p_strMovieName != null){
			objMovie = m_hashNameMap.get(p_strMovieName);
		}
		
		return objMovie;
	}
	
	public ArrayList<Movie> findMovieByCategory(String pCategoryName){
		ArrayList<Movie> lobjCategoryMovieList = new ArrayList<Movie>();
		for ( String strKey : m_hashNameMap.keySet() ){
			Movie lobjMovie = m_hashNameMap.get(strKey);
			if(pCategoryName.equals(lobjMovie.getCategoryName())){
				lobjCategoryMovieList.add(lobjMovie);
			}
        }
		return lobjCategoryMovieList;

	}
	
	public ArrayList<Movie> findMovieByDirector(String pDirector){
		ArrayList<Movie> lobjDirectorMovieList = new ArrayList<Movie>();
		for ( String strKey : m_hashNameMap.keySet() ){
			Movie lobjMovie = m_hashNameMap.get(strKey);
			if(pDirector.equals(lobjMovie.getDirector())){
				lobjDirectorMovieList.add(lobjMovie);
			}
        }
		return lobjDirectorMovieList;

	}
	
	public ArrayList<Movie> findDVDsForCategory(String pCategoryName){
		ArrayList<Movie> objDVDRentables = new ArrayList<Movie>();
		ArrayList<Movie> objMovieList = findMovieByCategory(pCategoryName);
		for(int i=0;i<objMovieList.size();i++){
			if(objMovieList.get(i).isDVDCopiesofMovie()){
				objDVDRentables.add(objMovieList.get(i));
			}
		}
		
		return objDVDRentables;
	}
	
	public double findPriceofMovie(double p_dblMovieId,String p_strMovieName){
		Movie objMovie = findMovie(p_dblMovieId,p_strMovieName);
		if(null != objMovie){
			return objMovie.getPrice();
		}
		
		return 0;
	}
	
	public int findQunatitiesofMovie(double p_dblMovieId,String p_strMovieName){
		Movie objMovie = findMovie(p_dblMovieId,p_strMovieName);
		if(null != objMovie){
			return objMovie.getQuantities();
		}
		
		return 0;
	}
	
	public boolean addDVDCopies(double p_dblMovieId,String p_strMovieName,int pMovieQuantities){
		Movie objMovie = findMovie(p_dblMovieId,p_strMovieName);
		if(null != objMovie){
			objMovie.addDVDCopies(pMovieQuantities);
			m_hashIdSortedMap.put(objMovie.getId(), objMovie);
			m_hashNameMap.put(objMovie.getTitle(), objMovie);
			return true;
		}
		
		return false;
	}
	
	public boolean addVideotapesCopies(double p_dblMovieId,String p_strMovieName,int pMovieQuantities){
		Movie objMovie = findMovie(p_dblMovieId,p_strMovieName);
		if(null != objMovie){
			objMovie.addVideotapeCopies(pMovieQuantities);
			m_hashIdSortedMap.put(objMovie.getId(), objMovie);
			m_hashNameMap.put(objMovie.getTitle(), objMovie);
			return true;
		}
		
		return false;
	}
	
	public boolean rentDVDMovie(double p_dblMovieId,String p_strMovieName,String pRenterName){
		Movie objMovie = findMovie(p_dblMovieId,p_strMovieName);
		if(null != objMovie){
			return rentMovie(objMovie.getDVDRentables(),pRenterName);			
		}
		
		return false;
	}
	
	public boolean rentVideotapesMovie(double p_dblMovieId,String p_strMovieName,String pRenterName){
		Movie objMovie = findMovie(p_dblMovieId,p_strMovieName);
		if(null != objMovie){
			return rentMovie(objMovie.getVideotapesRentables(),pRenterName);			
		}
		
		return false;
	}
	
	private boolean rentMovie(ArrayList<RentablesItem> objDVDRentables, String pRenterName){
		for(int i=0;i<objDVDRentables.size();i++){
			RentablesItem objRentableItem = objDVDRentables.get(i);
			if(!objRentableItem.m_blnRented){
				objRentableItem.m_blnRented = true;
				objRentableItem.m_strRenterName = pRenterName;
				return true;
			}
		}
		
		return false;
	}
	
	public double computeLateFees(double p_dblMovieId,String p_strMovieName,int pRentalDays){
		Movie objMovie = findMovie(p_dblMovieId,p_strMovieName);
		if(null != objMovie){
			return objMovie.computeLateFees(pRentalDays);			
		}
		return -1;
	}
	
	public boolean setCategoryForMovie(double p_dblMovieId,String p_strMovieName,String pNewCategoryName){
		Movie objMovie = findMovie(p_dblMovieId,p_strMovieName);
		if(null != objMovie){
			return objMovie.setCategory(m_objCategoryList.get(pNewCategoryName));			
		}
		return false;
	}
	
	public boolean addGenre(double p_dblMovieId,String p_strMovieName,String pGenreName){
		Movie objMovie = findMovie(p_dblMovieId,p_strMovieName);
		if(null != objMovie){
			return objMovie.addGenre(pGenreName);			
		}
		return false;
	}
	
	public boolean setCategoryDetails(String pCategoryName, double pRentalPrice, int pRetalPeriod, double pLateFees){
		MovieCategory objCategory = m_objCategoryList.get(pCategoryName);
		objCategory.m_dblRentalPrice = pRentalPrice;
		objCategory.m_intRetalPeriod = pRetalPeriod;
		objCategory.m_dblLateFees = pLateFees;
		return true;
	}
	
	public boolean setPriceofCategory(String pCategoryName, double pRentalPrice){
		MovieCategory objCategory = m_objCategoryList.get(pCategoryName);
		objCategory.m_dblRentalPrice = pRentalPrice;
		return true;
	}
}