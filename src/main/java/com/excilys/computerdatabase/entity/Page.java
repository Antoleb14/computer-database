package com.excilys.computerdatabase.entity;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.service.Order;

/**
 * Class Page.
 *
 * @author alebel
 *
 * @param <T>
 *            Object to paginate
 */
@Component("page")
public class Page<T> {

    private ArrayList<T> elements;
    private int currentPage = 1;
    private int maxPages = 0;
    private int elementsByPage = 0;
    private long total = 0;
    private String search = "";
    private Order order;

    public Page() {
    }

    /**
     * Class constructor.
     *
     * @param elements
     *            elements to display
     * @param maxPages
     *            number of pages
     * @param currentPage
     *            current page
     * @param elementsByPage
     *            number of elements by page
     */
    public Page(ArrayList<T> elements, int maxPages, int currentPage, int elementsByPage, long totalElem, String search,
            Order order) {
        this.currentPage = currentPage;
        this.elements = elements;
        this.maxPages = maxPages;
        this.elementsByPage = elementsByPage;
        this.total = totalElem;
        this.search = search;
        this.order = order;
    }

    public ArrayList<T> getElements() {
        return elements;
    }

    public void setElements(ArrayList<T> elements) {
        this.elements = elements;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getMaxPages() {
        return maxPages;
    }

    public void setMaxPages(int maxPages) {
        this.maxPages = maxPages;
    }

    public int getElementsByPage() {
        return elementsByPage;
    }

    public void setElementsByPage(int elementsByPage) {
        this.elementsByPage = elementsByPage;
    }

    public long getTotal() {
        return this.total;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
