package com.excilys.computerdatabase.entity;

/**
 * Computer Entity.
 *
 * @author excilys
 *
 */
public class ComputerDTO {

    private String id;
    private String name;
    private String introduced;
    private String discontinued;
    private String companyName;

    /**
     * Constructor of the class.
     */
    public ComputerDTO() {
        id = null;
        name = null;
        introduced = null;
        discontinued = null;
        companyName = null;
    }

    /**
     * Constructor of the class.
     *
     * @param id
     *            id of the computer
     * @param name
     *            name of the computer
     * @param introduced
     *            time of introduction of the computer
     * @param discontinued
     *            time of stopped computer
     * @param company
     *            company of the computer
     */
    public ComputerDTO(String id, String name, String introduced, String discontinued, String company) {
        this.id = id;
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.companyName = company;
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

    public String getIntroduced() {
        return introduced;
    }

    public void setIntroduced(String introduced) {
        this.introduced = introduced;
    }

    public String getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(String discontinued) {
        this.discontinued = discontinued;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompany(String company) {
        this.companyName = company;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(id).append(" | ");
        res.append(name).append(" | ");
        res.append(introduced).append(" | ");
        res.append(discontinued).append(" | ");
        res.append(companyName);
        return res.toString();
    }

    /**
     * Builder for Computer.
     *
     * @author excilys
     *
     */
    public static class ComputerDTOBuilder {
        private String id;
        private String name;
        private String introduced;
        private String discontinued;
        private String company;

        /**
         * Builder constructor.
         */
        public ComputerDTOBuilder() {
        }

        /**
         * ID setter for builder.
         *
         * @param name2
         *            id
         * @return ComputerBuilder
         */
        public ComputerDTOBuilder id(String name2) {
            this.id = name2;
            return this;
        }

        /**
         * name setter for builder.
         *
         * @param name
         *            name of the computer
         * @return ComputerBuilder
         */
        public ComputerDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Introduced time setter for builder.
         *
         * @param introduced
         *            LocalDateTime
         * @return ComputerBuilder
         */
        public ComputerDTOBuilder introduced(String introduced) {
            this.introduced = introduced;
            return this;
        }

        /**
         * discontinued setter for builder.
         *
         * @param discontinued
         *            LocalDateTime
         * @return ComputerBuilder
         */
        public ComputerDTOBuilder discontinued(String discontinued) {
            this.discontinued = discontinued;
            return this;
        }

        /**
         * company setter for builder.
         *
         * @param company
         *            Company of the computer
         * @return ComputerBuilder
         */
        public ComputerDTOBuilder company(String company) {
            this.company = company;
            return this;
        }

        /**
         * Build method of the builder.
         *
         * @return Computer
         */
        public ComputerDTO build() {
            return new ComputerDTO(id, name, introduced, discontinued, company);
        }

    }

}
