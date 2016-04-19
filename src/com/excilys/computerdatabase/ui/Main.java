package com.excilys.computerdatabase.ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.CompanyDB;
import com.excilys.computerdatabase.persistence.ComputerDB;

public class Main {

	public static void main(String[] args) {
		menu(0);	
	}

	private static void menu(int val) {
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
		if(val == 1){
			ComputerDB computer = new ComputerDB();
			ArrayList<Computer> list = computer.findAll();
			computer.closeConnection();
			
			for(Computer c : list){
				System.out.println(c);
			}
			
			
			
		}else if(val == 2){
			CompanyDB companies = new CompanyDB();
			ArrayList<Company> res = companies.findAll();
			for(Company c : res){
				System.out.println(c);
			}
			companies.closeConnection();
			
		}else if(val == 3){
			System.out.println();
			System.out.print("Which computer would you like to view : ");
			int id = enterInt(sc);
			ComputerDB computer = new ComputerDB();
			Computer cmp = computer.find(id);
			System.out.println(cmp);
			System.out.println();
			if(cmp != null){
				System.out.print("Options: 1-Modifier, 2-Supprimer, 0-Retour => Votre choix :");
				int opt = enterInt(sc);
				options(cmp, opt);
			}
		}else if(val == 4){
			Computer cmp = new Computer();
			createComputer(cmp);
		}else if(val == 5){
			System.exit(0);
		}
		menu(0);

	}

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
		companies.closeConnection();
		
		if(comp != null){
			cmp.setCompany(comp);
		}
		
		System.out.println(cmp);
		ComputerDB computer = new ComputerDB();
		computer.create(cmp);
		computer.closeConnection();
		System.out.println("Computer has been created!");
	}

	private static void options(Computer cmp, int opt) {
		Scanner sc = new Scanner(System.in);
		if(opt == 2){
			ComputerDB compDB = new ComputerDB();
			compDB.delete(cmp);
			compDB.closeConnection();
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
			CompanyDB companies = new CompanyDB();
			ArrayList<Company> res = companies.findAll();
			
			for(Company c : res){
				System.out.println(c);
			}
			
			System.out.print("Compagnie du Computer (0 = no company: ");
			int company = enterInt(sc);
			Company comp = companies.find(company);
			companies.closeConnection();
			
			if(comp != null){
				cmp.setCompany(comp);
			}
			
			System.out.println(cmp);
			ComputerDB computer = new ComputerDB();
			computer.update(cmp);
			computer.closeConnection();
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
