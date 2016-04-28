package com.excilys.computerdatabase.entity;

/**
 * Company Entity.
 *
 * @author excilys
 *
 */
public class CompanyDTO {

    private String id;
    private String name;

    /**
     * Constructor of the class.
     *
     * @param l
     *            Id
     * @param name
     *            Name of the company
     */
    public CompanyDTO(String l, String name) {
        this.id = l;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String res = id + " - ";
        res += name;
        return res;
    }
}
