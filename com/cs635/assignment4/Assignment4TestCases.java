package com.cs635.assignment4;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Assignment4TestCases {
	public Inventory objInventory = new Inventory();
	@Before
	public void setUp() throws Exception {
		objInventory.addNewMovie("Recent releases","Avatar","Hetang","05/08/2010",9);
		objInventory.addNewMovie("Recent releases","Star track","Hetang","05/08/2010",9);
		objInventory.addNewMovie("Normal","Star Wars","Hetang","05/08/2009",9);
		objInventory.addNewMovie("Normal","Die Hard4","Hetang","05/08/2009",9);
		objInventory.addNewMovie("Oldies","Troy","Hetang","05/08/2002",9);
		objInventory.addNewMovie("Oldies","Terminator","Hetang","05/08/2000",9);
		objInventory.addDVDCopies(0,"Avatar",10);
		objInventory.addDVDCopies(0,"Star Wars",10);
		objInventory.addDVDCopies(0,"Die Hard4",10);
		objInventory.addVideotapesCopies(0,"Star Wars",10);
	}

	@Test
	public void testAdd() throws Exception {
		assertNotSame(0,objInventory.addNewMovie("Recent releases","Iron Man2","Hetang","05/08/2010",9));		
	}
	
	@Test
	public void testAddDVDCopies() throws Exception {
		assertTrue(objInventory.addDVDCopies(0,"Avatar",10));		
		assertTrue(objInventory.addDVDCopies(0,"Star Wars",10));
	}
	
	@Test
	public void testAddVideotapesCopies() throws Exception {
		assertTrue(objInventory.addVideotapesCopies(0,"Star Wars",10));		
	}
	
	@Test
	public void testfindQunatitiesofMovie() throws Exception {
		assertEquals(20,objInventory.findQunatitiesofMovie(0,"Star Wars"));		
	}
	
	@Test
	public void testfindMovieByCategory() throws Exception {
		assertEquals(2,(objInventory.findMovieByCategory("Recent releases")).size());		
	}
	
	@Test
	public void testfindMovieByDirector() throws Exception {
		assertEquals(6,(objInventory.findMovieByDirector("Hetang")).size());		
	}
	
	@Test
	public void testfindDVDsForCategory() throws Exception {
		assertEquals(2,(objInventory.findDVDsForCategory("Normal")).size());		
	}
	
	@Test
	public void testfindPriceofMovie() throws Exception {
		assertEquals(3.00,objInventory.findPriceofMovie(0,"Star Wars"),0);		
	}
	
	@Test
	public void testrentDVDMovie() throws Exception {
		assertTrue(objInventory.rentDVDMovie(0,"Star Wars","Jhon"));		
	}
	
	@Test
	public void testrentVideotapesMovie() throws Exception {
		assertFalse(objInventory.rentVideotapesMovie(0,"Avatar","Jhon"));		
	}
	
	@Test
	public void testcomputeLateFees() throws Exception{
		assertEquals(2.25,objInventory.computeLateFees(0,"Star Wars",10),0);
	}
	
	@Test
	public void testsetCategoryForMovie() throws Exception{
		assertTrue(objInventory.setCategoryForMovie(0,"Star Wars","Oldies"));
	}
	
	@Test
	public void testsetManagerSpecial() throws Exception{
		assertTrue(objInventory.setCategoryDetails("Manager Special",10.00,5,1.00));
	}
}
