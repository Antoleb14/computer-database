package com.excilys.computerdatabase.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.entity.ComputerDTO;
import com.excilys.computerdatabase.entity.Page;
import com.excilys.computerdatabase.mapper.ComputerDTOMapper;

/**
 * Pagination class for computers.
 *
 * @author excilys
 *
 */
public class ServicePage {

    private static ServiceComputer cs = ServiceComputer.INSTANCE;

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

    /**
     * Constructor of the class.
     */
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
        List<Computer> computers = cs.findBySearch(current, limitPerPage, "", "");
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
    public Page<ComputerDTO> createPage(int currentPage, int elementsByPage, String search, String order) {
        this.limitPerPage = elementsByPage;
        this.page = currentPage;
        int current = (currentPage - 1) * limitPerPage;
        ArrayList<Computer> elements = cs.findBySearch(current, elementsByPage, search, order);
        ArrayList<ComputerDTO> elements2 = ComputerDTOMapper.INSTANCE.listObjetToDTO(elements);
        long totalNumber = cs.countBySearch(search);
        System.out.println("COMPUTERS : " + totalNumber);
        int totalPages = (int) ((totalNumber / limitPerPage) + (totalNumber % limitPerPage > 0 ? 1 : 0));
        System.out.println("TOTAL PAGES : " + totalPages);
        return new Page<ComputerDTO>(elements2, totalPages, currentPage, elementsByPage, totalNumber, search);
    }

}
