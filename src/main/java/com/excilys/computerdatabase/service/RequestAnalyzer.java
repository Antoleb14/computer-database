package com.excilys.computerdatabase.service;

import com.excilys.computerdatabase.exception.ValidatorException;

public enum RequestAnalyzer {

    INSTANCE;

    public String analyzeString(String parameter) {
        return "";
    }

    /**
     * Methode to analyse if a parameter is an Integer.
     *
     * @param parameter
     *            string parameter
     * @return int parameter
     */
    public int analyzeInt(String parameter) {
        int itemsPage = 0;
        try {
            if (parameter != null) {
                itemsPage = Integer.parseInt(parameter);
            }
        } catch (Exception e) {
            throw new ValidatorException("Incorrect Parameter");
        }
        return itemsPage;
    }

}
