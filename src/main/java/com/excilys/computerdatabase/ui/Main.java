package com.excilys.computerdatabase.ui;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.service.ServiceCompany;
import com.excilys.computerdatabase.service.ServiceComputer;
import com.excilys.computerdatabase.service.ServicePage;

/**
 * Main class for user interface.
 *
 * @author excilys
 *
 */
public class Main {

    static final Logger LOG = LoggerFactory.getLogger(Main.class);
    private static final ServiceComputer COMPUTER = ServiceComputer.INSTANCE;
    private static final ServiceCompany COMPANY = ServiceCompany.INSTANCE;
    private static Scanner sc = null;

    /**
     * Entry point of java program.
     *
     * @param args
     *            arguments
     */
    public static void main(String[] args) {
        LOG.debug("Entering CLI mode");
        sc = new Scanner(System.in);
        menu(0);
        sc.close();

    }

    /**
     * Menu method.
     *
     * @param val
     *            Option selected
     */
    private static void menu(int val) {
        // CompanyDB companies = CompanyDB.getCompanyDb();
        // ComputerDB computer = ComputerDB.getComputerDb();
        while (val <= 0 || val > 5) {
            System.out.println("Tapez un numéro correspondant à votre requète : ");
            System.out.println("    1 - List computers");
            System.out.println("    2 - List companies");
            System.out.println("    3 - Computer details (view, edit, delete)");
            System.out.println("    4 - Create a computer");
            System.out.println("    5 - Delete a company");
            System.out.println("    6 - Quitter");
            System.out.print("Indiquez votre choix : ");
            val = enterInt();
        }
        switch (val) {
        case 1:
            int entry = 1;
            do {
                ServicePage p = new ServicePage(15);
                List<Computer> computers = p.pager(entry);
                for (Computer c : computers) {
                    System.out.println(c);
                }
                entry = p.hud();
                if (entry > 0) {
                    p.pager(entry);
                }
            } while (entry != 0);
            break;

        case 2:
            List<Company> res = COMPANY.findAll();
            for (Company c : res) {
                System.out.println(c);
            }
            break;
        case 3:
            System.out.println();
            System.out.print("Which computer would you like to view : ");
            Long id = new Long(enterInt());

            Computer cmp = COMPUTER.find(id);
            System.out.println(cmp);
            System.out.println();
            if (cmp != null) {
                System.out.print("Options: 1-Modifier, 2-Supprimer, 0-Retour => Votre choix :");
                int opt = enterInt();
                options(cmp, opt);
            }
            break;
        case 4:
            Computer cmp1 = new Computer();
            createComputer(cmp1);
            break;
        case 5:
            System.out.println();
            System.out.print("Which company would you like to delete : ");
            Long comp = new Long(enterInt());
            Company company = COMPANY.find(comp);
            LOG.debug(company.toString());
            try {
                COMPANY.delete(company);
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
            System.out.println(company.getName() + " & all computers have been deleted !");
            break;
        case 6:
            System.exit(0);
            break;
        }
        menu(0);
    }

    /**
     * UI method to create a computer.
     *
     * @param cmp
     *            object Computer in case of typing error
     */
    private static void createComputer(Computer cmp) {
        System.out.println("Entrer un nom (0 to cancel): ");
        String value = enterValue();
        if (value.length() > 0) {
            cmp.setName(value);
        } else {
            System.err.println("The name cannot be empty !");
            createComputer(cmp);
        }

        // INTRODUCED
        LocalDateTime ldt = null;
        LocalDateTime ldt2 = null;
        System.out.println("Date d'introduction (0 = no edit):");
        System.out.print("Jour d'introduction (1-31): ");
        int day = enterInt();
        if (day != 0) {
            System.out.print("Mois d'introduction (1-12): ");
            int month = enterInt();
            System.out.print("Année d'introduction (1900-2016): ");
            int year = enterInt();
            ldt = LocalDateTime.of(year, month, day, 0, 0, 0);
            cmp.setIntroduced(ldt);
        }
        // DISCONTINUED
        System.out.println("Date d'arrêt (0 = no edit):");
        System.out.print("Jour de mise en arrêt (1-31): ");
        int day2 = enterInt();
        if (day2 != 0) {
            System.out.print("Mois de mise en arrêt (1-12): ");
            int month2 = enterInt();
            System.out.print("Année de mise en arrêt (1900-2016): ");
            int year2 = enterInt();
            ldt2 = LocalDateTime.of(year2, month2, day2, 0, 0, 0);
        }

        if (ldt != null && ldt2 != null) {
            if (ldt.isBefore(ldt2)) {
                cmp.setDiscontinued(ldt2);
            } else {
                System.err.println("The discontinued date must be after the introduced date ! Please try again !");
                createComputer(cmp);
            }
        }

        // COMPANY INSERT
        System.out.println("Liste des compagnies: ");
        List<Company> res = COMPANY.findAll();

        for (Company c : res) {
            System.out.println(c);
        }

        System.out.print("Computer's Company (0 = no company, empty = no change): ");
        Long company = new Long(enterInt());
        Company comp = COMPANY.find(company);

        if (comp != null) {
            cmp.setCompany(comp);
        }

        System.out.println(cmp);
        COMPUTER.create(cmp);
        System.out.println("Computer has been created!");
    }

    /**
     * UI method for the different options of a computer.
     *
     * @param cmp
     *            Computer
     * @param opt
     *            option selected (edit, remove or go back)
     */
    private static void options(Computer cmp, int opt) {
        if (opt == 2) {
            COMPUTER.delete(cmp);
            System.out.println("The computer \"" + cmp.getName() + "\" has been removed from the database!");

        }
        if (opt == 1) {
            System.out.println("Modifier le nom (vide = no edit): ");
            String value = enterValue();
            if (value.length() > 0) {
                cmp.setName(value);
            }

            // INTRODUCED
            LocalDateTime ldt = null;
            LocalDateTime ldt2 = null;
            System.out.println("Date d'introduction (0 = no edit):");
            System.out.print("Jour d'introduction (1-31): ");
            int day = enterInt();
            if (day != 0) {
                System.out.print("Mois d'introduction (1-12): ");
                int month = enterInt();
                System.out.print("Année d'introduction (1900-2016): ");
                int year = enterInt();
                ldt = LocalDateTime.of(year, month, day, 0, 0, 0);
                cmp.setIntroduced(ldt);
            }
            // DISCONTINUED
            System.out.println("Date d'arrêt (0 = no edit):");
            System.out.print("Jour de mise en arrêt (1-31): ");
            int day2 = enterInt();
            if (day2 != 0) {
                System.out.print("Mois de mise en arrêt (1-12): ");
                int month2 = enterInt();
                System.out.print("Année de mise en arrêt (1900-2016): ");
                int year2 = enterInt();
                ldt2 = LocalDateTime.of(year2, month2, day2, 0, 0, 0);
            }

            if (ldt != null && ldt2 != null) {
                if (ldt.isBefore(ldt2)) {
                    cmp.setDiscontinued(ldt2);
                } else {
                    System.err.println("The discontinued date must be after the introduced date ! Please try again !");
                    options(cmp, opt);
                }
            }

            // COMPANY INSERT
            System.out.println("Liste des compagnies: ");
            List<Company> res = COMPANY.findAll();

            for (Company c : res) {
                System.out.println(c);
            }

            System.out.print("Compagnie du Computer (0 = no company: ");
            Long company = new Long(enterInt());
            Company comp = COMPANY.find(company);

            if (comp != null) {
                cmp.setCompany(comp);
            }

            System.out.println(cmp);
            COMPUTER.update(cmp);

            System.out.println("Computer has been successfully edited!");
        }

    }

    /**
     * Method to verify the input.
     *
     * @return the value type, or 0 if not an integer
     */
    public static int enterInt() {
        String value = sc.nextLine();
        int res = 0;
        try {
            res = Integer.valueOf(value);
        } catch (Exception e) {
            res = 0;
        }
        return res;
    }

    /**
     * Method to type a text.
     *
     * @return the value type, or 0 if not an integer
     */
    public static String enterValue() {
        String value = sc.nextLine();
        return value;
    }

}
