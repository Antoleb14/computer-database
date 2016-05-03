package com.excilys.computerdatabase.entity;

import java.util.ArrayList;

/**
 * Class Page.
 *
 * @author alebel
 *
 * @param <T>
 *            Object to paginate
 */
public class Page<T> {

    private ArrayList<T> elements;
    private int currentPage = 1;
    private int maxPages = 0;
    private int elementsByPage = 0;
    private long total = 0;
    private String search = "";

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
    public Page(ArrayList<T> elements, int maxPages, int currentPage, int elementsByPage, long totalElem,
            String search) {
        this.currentPage = currentPage;
        this.elements = elements;
        this.maxPages = maxPages;
        this.elementsByPage = elementsByPage;
        this.total = totalElem;
        this.setSearch(search);
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

}
