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
    private String companyId;
    private String companyName;

    /**
     * Constructor of the class.
     */
    public ComputerDTO() {
        id = null;
        name = null;
        introduced = null;
        discontinued = null;
        companyId = null;
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
    public ComputerDTO(String id, String name, String introduced, String discontinued, String companyId,
            String company) {
        this.id = id;
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.companyId = companyId;
        this.companyName = company;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
        result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
        result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ComputerDTO other = (ComputerDTO) obj;
        if (companyId == null) {
            if (other.companyId != null) {
                return false;
            }
        } else if (!companyId.equals(other.companyId)) {
            return false;
        }
        if (companyName == null) {
            if (other.companyName != null) {
                return false;
            }
        } else if (!companyName.equals(other.companyName)) {
            return false;
        }
        if (discontinued == null) {
            if (other.discontinued != null) {
                return false;
            }
        } else if (!discontinued.equals(other.discontinued)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (introduced == null) {
            if (other.introduced != null) {
                return false;
            }
        } else if (!introduced.equals(other.introduced)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
        private String companyId;
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
         * companyId setter for builder.
         *
         * @param company
         *            id Company ID of the computer
         * @return ComputerBuilder
         */
        public ComputerDTOBuilder companyid(String companyid) {
            this.companyId = companyid;
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
            return new ComputerDTO(id, name, introduced, discontinued, companyId, company);
        }

    }

}
