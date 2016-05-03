package com.excilys.computerdatabase.service;

import com.excilys.computerdatabase.exception.ValidatorException;

public enum RequestAnalyzer {

    INSTANCE;

    public String analyzeString(String parameter, String defaultValue) {
        if (parameter == null) {
            return defaultValue;
        }
        return parameter;
    }

    /**
     * Methode to analyse if a parameter is an Integer.
     *
     * @param parameter
     *            string parameter
     * @return int parameter
     */
    public int analyzeInt(String parameter, int defaultValue) {
        int itemsPage = defaultValue;
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
