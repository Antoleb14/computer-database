package com.excilys.computerdatabase.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.entity.Page;

/**
 * Pagination class for computers.
 *
 * @author excilys
 *
 */
public class ServicePage {

    private static ServiceComputer cs = ServiceComputer.getInstance();

    private int page;
    private int maxPages;
    private int limitPerPage;
    private Scanner sc = new Scanner(System.in);

    /**
     * Constructeur de classe.
     *
     * @param limitPerPage
     *            Number of results by page
     */
    public ServicePage(int limitPerPage) {
        page = 1;
        this.limitPerPage = limitPerPage;
        this.maxPages = numberOfPages();
    }

    public ServicePage() {
        page = 1;
    }

    /**
     * Method to return the total number of pages needed.
     *
     * @return number of pages
     */
    public int numberOfPages() {
        int count = cs.count();
        return (count / limitPerPage) + (count % limitPerPage > 0 ? 1 : 0);
    }

    /**
     * Get result for a given page number.
     *
     * @param page
     *            number of the page
     * @return list of computers
     */
    public List<Computer> pager(int page) {
        int current = (page - 1) * limitPerPage;
        List<Computer> computers = cs.findAll(current, limitPerPage);
        this.page = page;
        return computers;

    }

    /**
     * Setter of page.
     *
     * @param page
     *            number of the page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Method for UI.
     *
     * @return the page selected
     */
    public int hud() {
        System.out.print("Page: " + page + "/" + maxPages + "    Go to page (0 to quit) : ");
        String value = sc.nextLine();
        try {
            this.page = Integer.valueOf(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
        return page;
    }

    /**
     * Method to create a Page for a view.
     * 
     * @param currentPage
     *            page number to show
     * @param elementsByPage
     *            number of elements by page
     * @return Page
     */
    public Page<Computer> createPage(int currentPage, int elementsByPage) {
        this.limitPerPage = elementsByPage;
        this.page = currentPage;
        int current = (currentPage - 1) * limitPerPage;
        int maxPages = numberOfPages();
        ArrayList<Computer> elements = (ArrayList<Computer>) cs.findAll(current, elementsByPage);
        return new Page<Computer>(elements, maxPages, currentPage, elementsByPage);
    }

}
