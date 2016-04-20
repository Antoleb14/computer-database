package com.excilys.computerdatabase.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.persistence.ComputerDB;

/**
 * Pagination class for computers
 * @author excilys
 *
 */
public class Paginator{

	private int page;
	private int maxPages;
	private int limitPerPage;
	
	/**
	 * Constructeur de classe
	 * @param limitPerPage Number of results by page
	 */
	public Paginator(int limitPerPage){
		page = 1;
		this.limitPerPage = limitPerPage;
		this.maxPages = numberOfPages();
	}
	
	/**
	 * Method to return the total number of pages needed
	 * @return number of pages
	 */
	public int numberOfPages(){
		ComputerDB cdb = new ComputerDB();
		int count = cdb.count();
		return (count/limitPerPage) + (count%limitPerPage>0?1:0);
	}
	
	/**
	 * Get result for a given page number
	 * @param page number of the page
	 * @return list of computers
	 */
	public ArrayList<Computer> pager(int page){
		System.err.println(this.page);
		int current=(page-1)*limitPerPage;
		ComputerDB cdb = new ComputerDB();
		ArrayList<Computer> computers = cdb.findAll(current, limitPerPage);
		return computers;
		
	}
	
	/**
	 * Setter of page
	 * @param page number of the page
	 */
	public void setPage(int page){
		this.page = page;
	}
	
	/**
	 * Method for UI
	 * @return the page selected
	 */
	public int hud(){
		System.out.print("Page: "+page+"/"+maxPages+"	Go to page (0 to quit) : ");
		Scanner sc = new Scanner(System.in);
		String value = sc.nextLine();
		try{
			page = Integer.valueOf(value);
		}catch(Exception e){
			e.printStackTrace();
		}
		return page;
	}
	
}
