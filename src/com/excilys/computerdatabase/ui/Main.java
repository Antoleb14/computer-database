package com.excilys.computerdatabase.ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.persistence.CompanyDB;
import com.excilys.computerdatabase.persistence.ComputerDB;
import com.excilys.computerdatabase.service.Paginator;

/**
 * Main class for user interface
 * @author excilys
 *
 */
public class Main {

	/**
	 * Entry point of java program
	 * @param args
	 */
	public static void main(String[] args) {
		menu(0);	
	}

	/**
	 * Menu method
	 * @param val Option selected
	 */
	private static void menu(int val) {
		CompanyDB companies = new CompanyDB();
		ComputerDB computer = new ComputerDB();
		Scanner sc = new Scanner(System.in);
		while(val<=0 || val>5){
			System.out.println("Tapez un numéro correspondant à votre requète : ");
			System.out.println("	1 - List computers");
			System.out.println("	2 - List companies");
			System.out.println("	3 - Computer details (view, edit, delete)");
			System.out.println("	4 - Create a computer");
			System.out.println("	5 - Quitter");
			System.out.print("Indiquez votre choix : ");
			val = enterInt(sc);
		}
		switch(val){
			case 1:
				int entry = 1;
				do{
					Paginator p = new Paginator(15);
					ArrayList<Computer> computers = p.pager(entry);
					int occ = 0;
					for(Computer c : computers){
						occ++;
						System.out.println(occ +" - "+c);
					}
					entry = p.hud();
					if(entry >0){
						p.pager(entry);
					}
				}while(entry != 0);
				break;
			
			case 2:
				ArrayList<Company> res = companies.findAll();
				for(Company c : res){
					System.out.println(c);
				}
				break;
			case 3:
				System.out.println();
				System.out.print("Which computer would you like to view : ");
				int id = enterInt(sc);
				
				Computer cmp = computer.find(id);
				System.out.println(cmp);
				System.out.println();
				if(cmp != null){
					System.out.print("Options: 1-Modifier, 2-Supprimer, 0-Retour => Votre choix :");
					int opt = enterInt(sc);
					options(cmp, opt);
				}
				break;
			case 4:
				Computer cmp1 = new Computer();
				createComputer(cmp1);
				break;
			case 5:
				System.exit(0);
				break;
		}
		computer.closeConnection();
		companies.closeConnection();
		menu(0);

	}

	/**
	 * UI method to create a computer
	 * @param cmp object Computer in case of typing error
	 */
	private static void createComputer(Computer cmp) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Entrer un nom: ");
		String value = sc.nextLine();
		if(value.length()>0){
			cmp.setName(value);
		}else{
			System.err.println("The name cannot be empty !");
			createComputer(cmp);
		}
		
		//INTRODUCED
		LocalDateTime ldt = null;
		LocalDateTime ldt2 = null;
		System.out.println("Date d'introduction (0 = no edit):");
		System.out.print("Jour d'introduction (1-31): ");
		int day = enterInt(sc);
		if(day != 0){
			System.out.print("Mois d'introduction (1-12): ");
			int month = enterInt(sc);
			System.out.print("Année d'introduction (1900-2016): ");
			int year = enterInt(sc);
			ldt = LocalDateTime.of(year, month, day, 0, 0, 0);
			cmp.setIntroduced(ldt);
		}
		//DISCONTINUED
		System.out.println("Date d'arrêt (0 = no edit):");
		System.out.print("Jour de mise en arrêt (1-31): ");
		int day2 = enterInt(sc);
		if(day2 != 0){
			System.out.print("Mois de mise en arrêt (1-12): ");
			int month2 = enterInt(sc);
			System.out.print("Année de mise en arrêt (1900-2016): ");
			int year2 = enterInt(sc);
			ldt2 = LocalDateTime.of(year2, month2, day2, 0, 0, 0);
		}
		
		if(ldt != null && ldt2 != null){
			if(ldt.isBefore(ldt2)){
				cmp.setDiscontinued(ldt2);
			}else{
				System.err.println("The discontinued date must be after the introduced date ! Please try again !");
				createComputer(cmp);
			}
		}
		
		// COMPANY INSERT
		System.out.println("Liste des compagnies: ");
		CompanyDB companies = new CompanyDB();
		ArrayList<Company> res = companies.findAll();
		
		for(Company c : res){
			System.out.println(c);
		}
		
		System.out.print("Compagnie du Computer (0 = no company: ");
		int company = enterInt(sc);
		Company comp = companies.find(company);
		
		if(comp != null){
			cmp.setCompany(comp);
		}
		
		System.out.println(cmp);
		ComputerDB computer = new ComputerDB();
		computer.create(cmp);
		System.out.println("Computer has been created!");
	}

	
	/**
	 * UI method for the different options of a computer
	 * @param cmp Computer
	 * @param opt option selected (edit, remove or go back)
	 */
	private static void options(Computer cmp, int opt) {
		Scanner sc = new Scanner(System.in);
		ComputerDB computer = new ComputerDB();
		CompanyDB companies = new CompanyDB();
		if(opt == 2){
			computer.delete(cmp);
			computer.closeConnection();
			System.out.println("The computer \""+ cmp.getName() +"\" has been removed from the database!");
			
		}
		if(opt == 1){
			System.out.println("Modifier le nom (vide = no edit): ");
			String value = sc.nextLine();
			if(value.length()>0){
				cmp.setName(value);
			}
			
			//INTRODUCED
			LocalDateTime ldt = null;
			LocalDateTime ldt2 = null;
			System.out.println("Date d'introduction (0 = no edit):");
			System.out.print("Jour d'introduction (1-31): ");
			int day = enterInt(sc);
			if(day != 0){
				System.out.print("Mois d'introduction (1-12): ");
				int month = enterInt(sc);
				System.out.print("Année d'introduction (1900-2016): ");
				int year = enterInt(sc);
				ldt = LocalDateTime.of(year, month, day, 0, 0, 0);
				cmp.setIntroduced(ldt);
			}
			//DISCONTINUED
			System.out.println("Date d'arrêt (0 = no edit):");
			System.out.print("Jour de mise en arrêt (1-31): ");
			int day2 = enterInt(sc);
			if(day2 != 0){
				System.out.print("Mois de mise en arrêt (1-12): ");
				int month2 = enterInt(sc);
				System.out.print("Année de mise en arrêt (1900-2016): ");
				int year2 = enterInt(sc);
				ldt2 = LocalDateTime.of(year2, month2, day2, 0, 0, 0);
			}
			
			if(ldt != null && ldt2 != null){
				if(ldt.isBefore(ldt2)){
					cmp.setDiscontinued(ldt2);
				}else{
					System.err.println("The discontinued date must be after the introduced date ! Please try again !");
					options(cmp, opt);
				}
			}
			
			// COMPANY INSERT
			System.out.println("Liste des compagnies: ");
			ArrayList<Company> res = companies.findAll();
			
			for(Company c : res){
				System.out.println(c);
			}
			
			System.out.print("Compagnie du Computer (0 = no company: ");
			int company = enterInt(sc);
			Company comp = companies.find(company);

			if(comp != null){
				cmp.setCompany(comp);
			}
			
			System.out.println(cmp);
			computer.update(cmp);

			System.out.println("Computer has been successfully edited!");
		}
		
	}
	
	/**
	 * Method to verify the input
	 * @param sc Scanner
	 * @return the value type, or 0 if not an integer
	 */
	public static int enterInt(Scanner sc){
		String value = sc.nextLine();
		int res = 0;
		try{
			res = Integer.valueOf(value);
		}catch(Exception e){
			res = 0;
		}
		return res;
	}

}
