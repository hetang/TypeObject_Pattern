package com.cs635.assignment4;

import java.util.ArrayList;

public class MovieGenre {
	private ArrayList<String> m_objGenreName = new ArrayList<String>();
	
	public boolean addGenre(String pGenre){
		m_objGenreName.add(pGenre);
		return true;
	}
}
