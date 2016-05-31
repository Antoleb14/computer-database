package com.excilys.computerdatabase.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

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
@Service("servicePage")
@Scope("singleton")
public class ServicePage {

    @Autowired
    private ServiceComputer cs;

    private int page = 1;
    private long maxPages;
    private int limitPerPage = 10;
    private Scanner sc = new Scanner(System.in);

    @Autowired
    private ComputerDTOMapper computerDTOMapper;

    /**
     * Constructor of the class.
     */
    public ServicePage() {
    }

    /**
     * Method to return the total number of pages needed.
     *
     * @return number of pages
     */
    public long numberOfPages() {
        long count = cs.count();
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
        System.out.println(current);
        maxPages = numberOfPages();
        List<Computer> computers = cs.findBySearch(current, limitPerPage, "", null);
        this.page = page;
        System.out.println(computers);
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
    public Page<ComputerDTO> createPage(int currentPage, int elementsByPage, String search, Order order) {
        this.limitPerPage = elementsByPage;
        this.page = currentPage;
        int current = (currentPage - 1) * limitPerPage;
        ArrayList<Computer> elements = cs.findBySearch(current, elementsByPage, search, order);
        ArrayList<ComputerDTO> elements2 = computerDTOMapper.listObjetToDTO(elements);
        long totalNumber = cs.countBySearch(search);
        int totalPages = (int) ((totalNumber / limitPerPage) + (totalNumber % limitPerPage > 0 ? 1 : 0));
        return new Page<ComputerDTO>(elements2, totalPages, currentPage, elementsByPage, totalNumber, search, order);
    }

}
