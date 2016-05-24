package com.excilys.computerdatabase.service;

import com.excilys.computerdatabase.exception.ValidatorException;

public class RequestAnalyzer {

    private static RequestAnalyzer INSTANCE = null;

    /**
     * Constructor of the class.
     */
    public RequestAnalyzer() {
    }

    /**
     * Method to get instance of ComputerDb or create one if null.
     *
     * @return ComputerDB
     */
    public static synchronized RequestAnalyzer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RequestAnalyzer();
        }
        return INSTANCE;
    }

    /**
     * Methode to analyse if a parameter is a String.
     *
     * @param parameter
     *            string parameter
     * @return int parameter
     */
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
