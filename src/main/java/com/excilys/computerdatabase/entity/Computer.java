package com.excilys.computerdatabase.entity;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Computer Entity.
 *
 * @author excilys
 *
 */
public class Computer {

    private Long id;
    private String name;
    private LocalDateTime introduced;
    private LocalDateTime discontinued;

    @Autowired
    @Qualifier("Company")
    private Company company;

    /**
     * Constructor of the class.
     */
    public Computer() {
        id = null;
        name = "";
        introduced = null;
        discontinued = null;
        company = null;
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
    public Computer(Long id, String name, LocalDateTime introduced, LocalDateTime discontinued, Company company) {
        this.id = id;
        this.name = name;
        this.introduced = introduced;
        this.discontinued = discontinued;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getIntroduced() {
        return introduced;
    }

    public void setIntroduced(LocalDateTime introduced) {
        this.introduced = introduced;
    }

    public LocalDateTime getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(LocalDateTime discontinued) {
        this.discontinued = discontinued;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(id).append(" | ");
        res.append(name).append(" | ");
        res.append(introduced).append(" | ");
        res.append(discontinued).append(" | ");
        res.append((company != null ? company.getName() : "No company"));
        return res.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((company == null) ? 0 : company.hashCode());
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
        Computer other = (Computer) obj;
        if (company == null) {
            if (other.company != null) {
                return false;
            }
        } else if (!company.equals(other.company)) {
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

    /**
     * Builder for Computer.
     *
     * @author excilys
     *
     */
    public static class ComputerBuilder {
        private Long id;
        private String name;
        private LocalDateTime introduced;
        private LocalDateTime discontinued;
        private Company company;

        /**
         * Builder constructor.
         */
        public ComputerBuilder() {
        }

        /**
         * ID setter for builder.
         *
         * @param id
         *            id
         * @return ComputerBuilder
         */
        public ComputerBuilder id(Long id) {
            this.id = id;
            return this;
        }

        /**
         * name setter for builder.
         *
         * @param name
         *            name of the computer
         * @return ComputerBuilder
         */
        public ComputerBuilder name(String name) {
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
        public ComputerBuilder introduced(LocalDateTime introduced) {
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
        public ComputerBuilder discontinued(LocalDateTime discontinued) {
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
        public ComputerBuilder company(Company company) {
            this.company = company;
            return this;
        }

        /**
         * Build method of the builder.
         *
         * @return Computer
         */
        public Computer build() {
            return new Computer(id, name, introduced, discontinued, company);
        }

    }

}
